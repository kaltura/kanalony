package com.kaltura.enrichment

import java.io.File

import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.{EnrichedPlayerEvent, PlayerEventParser}
import kafka.message.MessageAndMetadata
import org.apache.spark._
import org.apache.spark.streaming.kafka.KafkaTestingUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.util.SparkUtils
import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration._
import scala.io.Source
import scala.language.postfixOps

/**
 * Created by ofirk on 03/02/2016.
 */
class EnrichmentSpec extends SparkFunSuite
                      with BeforeAndAfter
                      with BeforeAndAfterAll
                      with Eventually
                      with Logging {

  val sparkConf = new SparkConf()
    .setMaster("local[24]")
    .setAppName(this.getClass.getSimpleName)
    .set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_enhancer.cassandra_host","127.0.0.1"))
    .set("spark.cassandra.connection.keep_alive_ms","30000")

  private var sc: SparkContext = _
  private var ssc: StreamingContext = _
  private var testDir: File = _

  private var kafkaTestUtils: KafkaTestingUtils = _

  override def beforeAll {
    kafkaTestUtils = new KafkaTestingUtils
    kafkaTestUtils.setup()
  }

  override def afterAll {
    if (kafkaTestUtils != null) {
      kafkaTestUtils.teardown()
      kafkaTestUtils = null
    }
  }

  after {
    if (ssc != null) {
      ssc.stop()
      sc = null
    }
    if (sc != null) {
      sc.stop()
    }
    if (testDir != null) {
      SparkUtils.deleteRecursively(testDir)
    }
  }

  test("RawPlayerEvents enrichment and re-produce them back to kafka") {
    val eventsFileName = ConfigurationManager.getOrElse("kanalony.events_enhancer.events_file",s"kanalony-enrichment/src/test/resources/events.txt")
    val events = Source.fromFile(eventsFileName).getLines.toList

    val expectedEvents = Source.fromFile(s"kanalony-enrichment/src/test/resources/enrichedEvents.txt").getLines()

    val playerEventsTopic = Set("player-events")
    val enrichedPlayerEventsTopic = Set("enriched-player-events")

    val allReceived = new ArrayBuffer[EnrichedPlayerEvent] with mutable.SynchronizedBuffer[EnrichedPlayerEvent]

    kafkaTestUtils.createTopic(enrichedPlayerEventsTopic.last)
    playerEventsTopic.foreach(t => {
      kafkaTestUtils.createTopic(t, 10)
      kafkaTestUtils.sendMessages(t, events.toArray)
    })


    val kafkaParams = Map(
      "metadata.broker.list" -> kafkaTestUtils.brokerAddress,
      "auto.offset.reset" -> "smallest"
    )

    ssc = new StreamingContext(sparkConf, Seconds(60))
    val messageHandler = (m: MessageAndMetadata[String, String]) => (m.topic, m.partition, m.offset, m.message)
    val stream = withClue("Error creating direct stream") {
      StreamManager.createStream(ssc, playerEventsTopic, kafkaParams)
    }

    val enrichedStream = withClue("Error creating direct stream") {
      StreamManager.createStream(ssc, kafkaTestUtils.brokerAddress, enrichedPlayerEventsTopic)
    }


    stream.map(_._2).
      flatMap(PlayerEventParser.parsePlayerEvent).
      foreachRDD { rdd =>
        EventsEnrichment.enrichEvents(rdd)
      }

    enrichedStream.map(_._2).
      flatMap(PlayerEventParser.parseEnhancedPlayerEvent).foreachRDD(rdd => allReceived ++= rdd.collect())

    ssc.start()
    eventually(timeout(300 seconds), interval(5 seconds)) {
      assert(allReceived.size === events.length - 1,
        "didn't get expected number of messages, messages:\n" + allReceived.mkString("\n"))
    }
    ssc.awaitTerminationOrTimeout(10000)
    ssc.stop()


    val expectedEnrichedEvents = expectedEvents.flatMap(PlayerEventParser.parseEnhancedPlayerEvent)
    while (expectedEnrichedEvents.hasNext) {
      var event = expectedEnrichedEvents.next
      assert(allReceived.contains(event) === true, event + " is missing ")
    }

  }



}
