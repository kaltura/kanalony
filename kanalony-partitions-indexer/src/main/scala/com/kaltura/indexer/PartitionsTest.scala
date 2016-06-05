package com.kaltura.indexer

import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import org.apache.log4j.{Level, Logger}
import org.apache.spark.streaming.kafka.{HasOffsetRanges, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Logging, SparkConf, SparkContext, TaskContext}

/**
 * Created by orlylampert on 5/31/16.
 */
object PartitionsTest extends App with Logging {
  override def main(args: Array[String]) {

    setStreamingLogLevels
    val applicationName = "PartitionsTest"
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
      .setAppName("PartitionsTest")
      .setMaster(ConfigurationManager.getOrElse("kanalony.partitions_indexer.master","local[8]"))
    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(5))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.partitions_indexer.kafka_brokers","127.0.0.1:9092")
    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaBrokers, "auto.offset.reset" -> "smallest")

    val topics = Set("topic1", "topic2")

    val keySpace = "kanalony_mng"
    val tableName = "hourly_partitions"

    var offsetRanges = Array[OffsetRange]()

    val stream = StreamManager.createStreamWithMetadata(ssc, topics, kafkaParams)
    stream.transform { rdd =>
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd
    }
      .foreachRDD { rdd =>
          rdd.foreachPartition(part => {
            while (part.hasNext) {
              val m = part.next()
              println(s"${m.message()} --> ${m.topic} ${m.partition} ${m.offset} ${TaskContext.getPartitionId()}")
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
