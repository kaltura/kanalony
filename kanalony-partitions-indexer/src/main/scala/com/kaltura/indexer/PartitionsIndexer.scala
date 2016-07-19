package com.kaltura.indexer

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.kaltura.core.cassandra.ClusterManager
import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.core.utils.ReadableTimeUnits._
import com.kaltura.model.events.TimeEventParser
import kafka.message.MessageAndMetadata
import org.apache.log4j.{Level, Logger}
import org.apache.spark._
import org.apache.spark.streaming.kafka.{HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object PartitionsIndexer extends App with Logging {

  override def main(args: Array[String]) {

    setStreamingLogLevels
    val applicationName = ConfigurationManager.get("kanalony.partitions_indexer.application_name")
    val checkpointRootPath = ConfigurationManager.getOrElse("kanalony.checkpoint_root_path","/tmp/checkpoint")
    val checkpointDirectory = s"$checkpointRootPath/$applicationName"
    // Get StreamingContext from checkpoint data or create a new one
    val ssc = StreamingContext.getOrCreate(checkpointDirectory,
      () => {
        createSparkStreamingContext(checkpointDirectory)
      })

    // Start the computation
    ssc.start()
    ssc.awaitTermination()

  }

  def createSparkStreamingContext(checkpointDirectory: String): StreamingContext = {
    val sparkConf = new SparkConf()
      .setAppName(ConfigurationManager.get("kanalony.partitions_indexer.application_name"))
      .setMaster(ConfigurationManager.getOrElse("kanalony.partitions_indexer.master","local[8]"))
      .set("spark.streaming.backpressure.enabled", ConfigurationManager.getOrElse("kanalony.partitions_indexer.backpressure","false"))
    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.partitions_indexer.batch_duration","5").toInt))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.partitions_indexer.kafka_brokers","127.0.0.1:9092")
    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaBrokers)

    val topicsList = ConfigurationManager.getOrElse("kanalony.partitions_indexer.topics_list", "player-events")
    val topics = topicsList.split(",").toSet

    val keySpace = "kanalony_mng"
    val tableName = "hourly_partitions"

    var offsetRanges = Array[OffsetRange]()

    val messageHandler = (mmd: MessageAndMetadata[String, String]) => mmd
    val stream = StreamManager.createStreamWithMetadata(ssc, topics, kafkaParams)
    stream.transform { rdd =>
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd
    }
      .flatMap {
        event =>
          val timeEvent = TimeEventParser.parseTimeEvent(event.message())
          if (!timeEvent.isEmpty)
            Some(event, timeEvent.get)
          else
            None
      }
      .foreachRDD { rdd =>
          rdd.foreachPartition(part => {
            val cassandraSession = ClusterManager.getSession
            if (part.hasNext) {
              val tmd = part.next()
              val firstEventHour = tmd._2.eventTime.hourOfDay().roundFloorCopy()
              cassandraSession.execute(QueryBuilder
                .insertInto(keySpace, tableName)
                .value("hour", firstEventHour.getMillis)
                .value("topic", tmd._1.topic)
                .value("partition", tmd._1.partition)
                .value("offset", tmd._1.offset)
                .using(QueryBuilder.ttl(2 day))
              )
            }
          })
      }

    ssc
  }

  def setStreamingLogLevels {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logInfo("Setting log level to [WARN] for streaming example." +
        " To override add a custom log4j.properties to the classpath.")

    }
    Logger.getRootLogger.setLevel(Level.WARN)
  }
}
