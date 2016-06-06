package com.kaltura.core.streaming

import java.util

import com.datastax.driver.core.Statement
import com.datastax.driver.core.querybuilder.QueryBuilder
import QueryBuilder.{eq => eql}
import com.kaltura.core.cassandra.ClusterManager
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.producer.{KafkaProducer, Producer, ProducerConfig}
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaUtils
import org.joda.time.DateTime
import scala.collection.JavaConversions._

object StreamManager {

  def createStream(ssc: StreamingContext, kafkaBrokers: String, topics: Set[String]): InputDStream[(String, String)] = {
    val kafkaParams = Map[String, String]("metadata.broker.list" -> kafkaBrokers)
    createStream(ssc, topics, kafkaParams)
  }

  def createStream(ssc: StreamingContext, topics: Set[String], kafkaParams: Map[String, String]): InputDStream[(String, String)] = {
    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topics)
  }

  def createStreamWithMetadata(ssc: StreamingContext, topics: Set[String], kafkaParams: Map[String, String]) : InputDStream[(MessageAndMetadata[String, String])] = {
    val messageHandler = (mmd: MessageAndMetadata[String, String]) => mmd
    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, MessageAndMetadata[String, String]](
      ssc, kafkaParams, getFromOffsets(kafkaParams, topics), messageHandler)
  }

  def createStream(ssc: StreamingContext, kafkaParams: Map[String, String], fromOffsets: Map[TopicAndPartition, Long]) : InputDStream[(String, String)] = {
    val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.key, mmd.message)
    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, (String, String)](
      ssc, kafkaParams, fromOffsets, messageHandler)
  }

  def getPartitionsOffsets(topic: String, hour: DateTime): Map[TopicAndPartition, Long] = {
    val cassandraSession = ClusterManager.getSession
    val s:Statement = QueryBuilder
      .select()
      .all()
      .from("kanalony_mng", "hourly_partitions")
      .where(eql("topic",topic))
      .and(eql("hour", hour.getMillis))

    val rows = cassandraSession.execute(s).all().toList
    rows.map(row => (TopicAndPartition(row.getString("topic"), row.getInt("partition")), row.getLong("offset"))).groupBy(_._1).map { case (k,v) => (k,v.head._2)}

  }

  private def getFromOffsets(kafkaParams: Map[String, String],
                             topics: Set[String]): Map[TopicAndPartition, Long] = {
    val kc = new KafkaCluster(kafkaParams)
    val reset = kafkaParams.get("auto.offset.reset").map(_.toLowerCase)
    val result = for {
      topicPartitions <- kc.getPartitions(topics).right
      leaderOffsets <- (if (reset == Some("smallest")) {
        kc.getEarliestLeaderOffsets(topicPartitions)
      } else {
        kc.getLatestLeaderOffsets(topicPartitions)
      }).right
    } yield {
        leaderOffsets.map { case (tp, lo) =>
          (tp, lo.offset)
        }
      }
    KafkaCluster.checkErrors(result)
  }

  def createProducer(kafkaBrokers:String): Producer[String, String] = {
    val props = new util.HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")

    new KafkaProducer[String, String](props)
  }
}
