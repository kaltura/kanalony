package com.kaltura.enrichment

import com.kaltura.core.logging.{BaseLog, MetaLog}
import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.{EnrichedPlayerEvent, PlayerEventParser}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}


object DimensionsEnrichment extends App with MetaLog[BaseLog] {

  override def main(args: Array[String]) {

    setStreamingLogLevels
    val applicationName = ConfigurationManager.get("kanalony.dimensions_enrichment.application_name")
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
      .setAppName(ConfigurationManager.get("kanalony.dimensions_enrichment.application_name"))
      .setMaster(ConfigurationManager.getOrElse("kanalony.dimensions_enrichment.master","local[2]"))
      .set("spark.cassandra.connection.host", ConfigurationManager.getOrElse("kanalony.dimensions_enrichment.cassandra_host","127.0.0.1"))
      .set("spark.cassandra.connection.keep_alive_ms","30000")
    val sparkContext = new SparkContext(sparkConf)
    val ssc = new StreamingContext(sparkContext, Seconds(ConfigurationManager.getOrElse("kanalony.dimensions_enrichment.batch_duration","5").toInt))
    ssc.checkpoint(checkpointDirectory)

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_enrichment.kafka_brokers","127.0.0.1:9092")
    val topics = Set("enriched-player-events")


    StreamManager.createStream(ssc, kafkaBrokers, topics)
      .flatMap(event => PlayerEventParser.parseEnhancedPlayerEvent(event._2))
      .foreachRDD(enrichDbWithDimenaions _)

    ssc
  }

  def enrichDbWithDimenaions(playerEvents:RDD[EnrichedPlayerEvent]):Unit = {
    playerEvents
      .foreachPartition( eventsPart => {
        val enrichEntry = new EnricheEntryMetadata
        val enrichCategory = new EnrichCategoryMetadata
        eventsPart.foreach(playerEvent => {
          enrichEntry.enrich(playerEvent)
          enrichCategory.enrich(playerEvent)
        })
      })
  }

  def setStreamingLogLevels {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logger.info("Setting log level to [WARN] for streaming example." +
        " To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }
}
