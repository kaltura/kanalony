package com.kaltura.enrichment

import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.enrichment.EventsEnrichment._
import com.kaltura.model.events.PlayerEventParser
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Logging, SparkConf}

import scala.collection.mutable
import scala.io.Source
import scala.language.postfixOps




/**
 * Created by orlylampert on 4/4/16.
 */
object EnrichmentTest extends App with Logging {
  override def main(args: Array[String]) {

  setStreamingLogLevels

  val applicationName = "EnrichmentLoadTest"
  val checkpointRootPath = ConfigurationManager.getOrElse("kanalony.checkpoint_root_path","/tmp/checkpoint")
  val checkpointDirectory = s"$checkpointRootPath/$applicationName"

    val sparkConf = new SparkConf()
      .setAppName(applicationName)
      .setMaster(ConfigurationManager.getOrElse("kanalony.events_enhancer.master","local[8]"))
      .set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.events_enhancer.cassandra_host","127.0.0.1"))
      .set("spark.cassandra.connection.keep_alive_ms","30000")
      .set("spark.streaming.backpressure.enabled", ConfigurationManager.getOrElse("kanalony.events_enhancer.backpressure","false"))
    //val sparkContext = new SparkContext(sparkConf)
    //val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.events_enhancer.batch_duration","1").toInt))
    //

    val eventsFileName = ConfigurationManager.getOrElse("kanalony.events_enhancer.events_file","/tmp/events.log")
    val events = Source.fromFile(eventsFileName).getLines.toList

    val ssc = new StreamingContext(sparkConf, Seconds(ConfigurationManager.getOrElse("kanalony.events_enhancer.batch_duration","5").toInt))
    ssc.checkpoint(checkpointDirectory)

    val rddQueue = new mutable.Queue[RDD[String]]()
    val batchSize = ConfigurationManager.getOrElse("kanalony.events_enhancer.batch_size","10000").toInt
    val partitions = ConfigurationManager.getOrElse("kanalony.events_enhancer.partitions","40").toInt

    for (c <- 0 to (events.size/batchSize)) {
      rddQueue += ssc.sparkContext.parallelize(events.slice(c*batchSize, (c+1)*batchSize),partitions)
    }


    ssc.queueStream(rddQueue).
      flatMap(PlayerEventParser.parsePlayerEvent).
      foreachRDD { rdd =>
        enrichEvents(rdd)

      }




    // Start the computation
  ssc.start()


  ssc.awaitTerminationOrTimeout(1200000)
  ssc.stop()


  }


  def setStreamingLogLevels {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logInfo("Setting log level to [WARN] for streaming example." +
        " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }

}
