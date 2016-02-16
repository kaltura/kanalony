package com.kaltura.enrichment

import java.io.File

import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.{EnrichedPlayerEvent, PlayerEventParser}
import org.apache.spark.streaming.kafka.KafkaTestingUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.util.SparkUtils
import org.apache.spark.{Logging, SparkConf, SparkContext, SparkFunSuite}
import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration._
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
    .setMaster("local[12]")
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
    val playerEvent =
    """{
      "@version":"1",
      "@timestamp":"2016-02-03T17:13:46.390Z",
      "host":"Ofirs-MacBook-Pro.local",
      "request":"/api_v3/index.php?service=stats&action=collect&kalsig=1ebb5aea0c5f253fd8c578febe6a752f&clientTag=kdp%3Av3%2E9%2E2&event%3AobjectType=KalturaStatsEvent&event%3AsessionId=C90BFCFC%2D2EBF%2D5893%2D892D%2D2121162F414A&apiVersion=3%2E1%2E5&event%3AisFirstInSession=false&event%3Aduration=194&ignoreNull=1&event%3AeventType=13&event%3Aseek=false&event%3Areferrer=http%253A%2F%2Fabc%2Ego%2Ecom%2Fshows%2Fthe%2Dbachelorette%2Fvideo%2Fmost%2Drecent%2FVDKA0%5Flawz79v7&event%3AentryId=1%5Frkxi9ngj&ks=djJ8MTg4MzUwMXx82V0BnDzlkrA9ppCMT_cD8qk2x_k_4z1E8v0bSuTGVGqBq6JPsBM9ipdalUsWdZntxSypKVgyXjk36bpQqWHA6VW_phuqy0snZPNdvZ11U5FUWAUKtX7aYxc7yXL6MK9bcXgnFrhUineEjgVvZ-UH&event%3AeventTimestamp=1435075182567&event%3AuiconfId=23521211&event%3ApartnerId=1883501&event%3AcurrentPoint=18&event%3AclientVer=3%2E0%3Av3%2E9%2E2-13",
      "proxyRemoteAddr":"8.8.8.8",
      "eventTime":"2016-02-03T17:13:46.000Z",
      "remoteAddr":"127.0.0.1",
      "userAgent":"\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:38.0) Gecko/20100101 Firefox/38.0\""
    }"""

    val playerEventsTopic = Set("player-events")
    val enrichedPlayerEventsTopic = Set("enriched-player-events")
    val data = Array.fill(1)(playerEvent)
    val allReceived = new ArrayBuffer[EnrichedPlayerEvent] with mutable.SynchronizedBuffer[EnrichedPlayerEvent]

    kafkaTestUtils.createTopic(enrichedPlayerEventsTopic.last)
    playerEventsTopic.foreach(t => {
      kafkaTestUtils.createTopic(t, 10)
      kafkaTestUtils.sendMessages(t, data)
    })


    val kafkaParams = Map(
      "metadata.broker.list" -> kafkaTestUtils.brokerAddress,
      "auto.offset.reset" -> "smallest"
    )

    ssc = new StreamingContext(sparkConf, Seconds(1))
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
    eventually(timeout(30.seconds), interval(200.milliseconds)) {
      assert(allReceived.size === data.length,
        "didn't get expected number of messages, messages:\n" + allReceived.mkString("\n"))
    }
    //ssc.awaitTerminationOrTimeout(100000)
    ssc.stop()

  }



}
