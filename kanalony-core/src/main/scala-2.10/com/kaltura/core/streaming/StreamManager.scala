package com.kaltura.core.streaming

import kafka.serializer.StringDecoder
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils

object StreamManager {

  def createStream(ssc: StreamingContext, kafkaBrokers: String, topics: Set[String]): InputDStream[(String, String)] = {
    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaBrokers)
    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topics)
  }
}
