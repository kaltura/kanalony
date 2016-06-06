package com.kaltura.core.streaming

/**
 * Created by orlylampert on 5/30/16.
 */

import java.util.Properties

import kafka.api._
import kafka.common.{ErrorMapping, OffsetAndMetadata, OffsetMetadataAndError, TopicAndPartition}
import kafka.consumer.{ConsumerConfig, SimpleConsumer}
import org.apache.spark.SparkException

import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import scala.util.control.NonFatal

/**
 * Convenience methods for interacting with a Kafka cluster.
 * Kafka
 * @param kafkaParams Kafka <a href="http://kafka.apache.org/documentation.html#configuration">
 * configuration parameters</a>.
 *   Requires "metadata.broker.list" or "bootstrap.servers" to be set with Kafka broker(s),
 *   NOT zookeeper servers, specified in host1:port1,host2:port2 form
 *   这里提供了和Kafka集群进行交互时方便的一些方法.
 */

class KafkaCluster(val kafkaParams: Map[String, String]) extends Serializable {
  import KafkaCluster.{Err, LeaderOffset, SimpleConsumerConfig}

  // ConsumerConfig isn't serializable
  @transient private var _config: SimpleConsumerConfig = null

  def config: SimpleConsumerConfig = this.synchronized {
    if (_config == null) {
      _config = SimpleConsumerConfig(kafkaParams)
    }
    _config
  }
  //连接到Kafka集群,这里是创建了一个SimpleConsumer.进行Kafka数据的读取工作.
  def connect(host: String, port: Int): SimpleConsumer =
    new SimpleConsumer(host, port, config.socketTimeoutMs,
      config.socketReceiveBufferBytes, config.clientId)
  //连接到Leader.是针对某个topic的某个partition的.这里要么是一个成功,要么是一个SimpleConsumer.
  def connectLeader(topic: String, partition: Int): Either[Err, SimpleConsumer] =
    findLeader(topic, partition).right.map(hp => connect(hp._1, hp._2))

  // Metadata api
  // scalastyle:off
  // https://cwiki.apache.org/confluence/display/KAFKA/A+Guide+To+The+Kafka+Protocol#AGuideToTheKafkaProtocol-MetadataAPI
  // scalastyle:on

  def findLeader(topic: String, partition: Int): Either[Err, (String, Int)] = {
    //生产一个请求,
    val req = TopicMetadataRequest(TopicMetadataRequest.CurrentVersion,
      0, config.clientId, Seq(topic))
    val errs = new Err
    withBrokers(Random.shuffle(config.seedBrokers), errs) { consumer =>
      //消费者发生一个请求 req,并返回一个响应 resp
      val resp: TopicMetadataResponse = consumer.send(req)
      //响应中包含 topicd的那些元数据中,是否还包括partition.采用的方式是返回随机的一个,
      //否则的话,返回错误.
      resp.topicsMetadata.find(_.topic == topic).flatMap { tm: TopicMetadata =>
        tm.partitionsMetadata.find(_.partitionId == partition)
      }.foreach { pm: PartitionMetadata =>
        pm.leader.foreach { leader =>
          return Right((leader.host, leader.port))
        }
      }
    }
    Left(errs)
  }

  def findLeaders(
                   topicAndPartitions: Set[TopicAndPartition]
                   ): Either[Err, Map[TopicAndPartition, (String, Int)]] = {
    val topics = topicAndPartitions.map(_.topic)
    val response = getPartitionMetadata(topics).right
    val answer = response.flatMap { tms: Set[TopicMetadata] =>
      val leaderMap = tms.flatMap { tm: TopicMetadata =>
        tm.partitionsMetadata.flatMap { pm: PartitionMetadata =>
          val tp = TopicAndPartition(tm.topic, pm.partitionId)
          if (topicAndPartitions(tp)) {
            pm.leader.map { l =>
              tp -> (l.host -> l.port)
            }
          } else {
            None
          }
        }
      }.toMap

      if (leaderMap.keys.size == topicAndPartitions.size) {
        Right(leaderMap)
      } else {
        val missing = topicAndPartitions.diff(leaderMap.keySet)
        val err = new Err
        err.append(new SparkException(s"Couldn't find leaders for ${missing}"))
        Left(err)
      }
    }
    answer
  }
  //获取组topic所在的partition.
  def getPartitions(topics: Set[String]): Either[Err, Set[TopicAndPartition]] = {
    getPartitionMetadata(topics).right.map { r =>
      r.flatMap { tm: TopicMetadata =>
        tm.partitionsMetadata.map { pm: PartitionMetadata =>
          TopicAndPartition(tm.topic, pm.partitionId)
        }
      }
    }
  }
  //获取分区的元数据.
  def getPartitionMetadata(topics: Set[String]): Either[Err, Set[TopicMetadata]] = {
    val req = TopicMetadataRequest(
      TopicMetadataRequest.CurrentVersion, 0, config.clientId, topics.toSeq)
    val errs = new Err
    withBrokers(Random.shuffle(config.seedBrokers), errs) { consumer =>
      val resp: TopicMetadataResponse = consumer.send(req)
      val respErrs = resp.topicsMetadata.filter(m => m.errorCode != ErrorMapping.NoError)

      if (respErrs.isEmpty) {
        return Right(resp.topicsMetadata.toSet)
      } else {
        respErrs.foreach { m =>
          val cause = ErrorMapping.exceptionFor(m.errorCode)
          val msg = s"Error getting partition metadata for '${m.topic}'. Does the topic exist?"
          errs.append(new SparkException(msg, cause))
        }
      }
    }
    Left(errs)
  }

  // Leader offset api
  // scalastyle:off
  // https://cwiki.apache.org/confluence/display/KAFKA/A+Guide+To+The+Kafka+Protocol#AGuideToTheKafkaProtocol-OffsetAPI
  // scalastyle:on
  //获取leader上的偏移量.
  def getLatestLeaderOffsets(
                              topicAndPartitions: Set[TopicAndPartition]
                              ): Either[Err, Map[TopicAndPartition, LeaderOffset]] =
    getLeaderOffsets(topicAndPartitions, OffsetRequest.LatestTime)
  //获取最早的偏移量.
  def getEarliestLeaderOffsets(
                                topicAndPartitions: Set[TopicAndPartition]
                                ): Either[Err, Map[TopicAndPartition, LeaderOffset]] =
    getLeaderOffsets(topicAndPartitions, OffsetRequest.EarliestTime)
  //
  def getLeaderOffsets(
                        topicAndPartitions: Set[TopicAndPartition],
                        before: Long
                        ): Either[Err, Map[TopicAndPartition, LeaderOffset]] = {
    getLeaderOffsets(topicAndPartitions, before, 1).right.map { r =>
      r.map { kv =>
        // mapValues isnt serializable, see SI-7005
        kv._1 -> kv._2.head
      }
    }
  }

  private def flip[K, V](m: Map[K, V]): Map[V, Seq[K]] =
    m.groupBy(_._2).map { kv =>
      kv._1 -> kv._2.keys.toSeq
    }

  def getLeaderOffsets(
                        topicAndPartitions: Set[TopicAndPartition],
                        before: Long,
                        maxNumOffsets: Int
                        ): Either[Err, Map[TopicAndPartition, Seq[LeaderOffset]]] = {
    findLeaders(topicAndPartitions).right.flatMap { tpToLeader =>
      val leaderToTp: Map[(String, Int), Seq[TopicAndPartition]] = flip(tpToLeader)
      val leaders = leaderToTp.keys
      var result = Map[TopicAndPartition, Seq[LeaderOffset]]()
      val errs = new Err
      withBrokers(leaders, errs) { consumer =>
        val partitionsToGetOffsets: Seq[TopicAndPartition] =
          leaderToTp((consumer.host, consumer.port))
        val reqMap = partitionsToGetOffsets.map { tp: TopicAndPartition =>
          tp -> PartitionOffsetRequestInfo(before, maxNumOffsets)
        }.toMap
        val req = OffsetRequest(reqMap)
        val resp = consumer.getOffsetsBefore(req)
        val respMap = resp.partitionErrorAndOffsets
        partitionsToGetOffsets.foreach { tp: TopicAndPartition =>
          respMap.get(tp).foreach { por: PartitionOffsetsResponse =>
            if (por.error == ErrorMapping.NoError) {
              if (por.offsets.nonEmpty) {
                result += tp -> por.offsets.map { off =>
                  LeaderOffset(consumer.host, consumer.port, off)
                }
              } else {
                errs.append(new SparkException(
                  s"Empty offsets for ${tp}, is ${before} before log beginning?"))
              }
            } else {
              errs.append(ErrorMapping.exceptionFor(por.error))
            }
          }
        }
        if (result.keys.size == topicAndPartitions.size) {
          return Right(result)
        }
      }
      val missing = topicAndPartitions.diff(result.keySet)
      errs.append(new SparkException(s"Couldn't find leader offsets for ${missing}"))
      Left(errs)
    }
  }

  // Consumer offset api
  // scalastyle:off
  // https://cwiki.apache.org/confluence/display/KAFKA/A+Guide+To+The+Kafka+Protocol#AGuideToTheKafkaProtocol-OffsetCommit/FetchAPI
  // scalastyle:on

  // this 0 here indicates api version, in this case the original ZK backed api.
  private def defaultConsumerApiVersion: Short = 0

  /** Requires Kafka >= 0.8.1.1 */
  def getConsumerOffsets(
                          groupId: String,
                          topicAndPartitions: Set[TopicAndPartition]
                          ): Either[Err, Map[TopicAndPartition, Long]] =
    getConsumerOffsets(groupId, topicAndPartitions, defaultConsumerApiVersion)

  def getConsumerOffsets(
                          groupId: String,
                          topicAndPartitions: Set[TopicAndPartition],
                          consumerApiVersion: Short
                          ): Either[Err, Map[TopicAndPartition, Long]] = {
    getConsumerOffsetMetadata(groupId, topicAndPartitions, consumerApiVersion).right.map { r =>
      r.map { kv =>
        kv._1 -> kv._2.offset
      }
    }
  }

  /** Requires Kafka >= 0.8.1.1 */
  def getConsumerOffsetMetadata(
                                 groupId: String,
                                 topicAndPartitions: Set[TopicAndPartition]
                                 ): Either[Err, Map[TopicAndPartition, OffsetMetadataAndError]] =
    getConsumerOffsetMetadata(groupId, topicAndPartitions, defaultConsumerApiVersion)

  def getConsumerOffsetMetadata(
                                 groupId: String,
                                 topicAndPartitions: Set[TopicAndPartition],
                                 consumerApiVersion: Short
                                 ): Either[Err, Map[TopicAndPartition, OffsetMetadataAndError]] = {
    var result = Map[TopicAndPartition, OffsetMetadataAndError]()
    val req = OffsetFetchRequest(groupId, topicAndPartitions.toSeq, consumerApiVersion)
    val errs = new Err
    withBrokers(Random.shuffle(config.seedBrokers), errs) { consumer =>
      val resp = consumer.fetchOffsets(req)
      val respMap = resp.requestInfo
      val needed = topicAndPartitions.diff(result.keySet)
      needed.foreach { tp: TopicAndPartition =>
        respMap.get(tp).foreach { ome: OffsetMetadataAndError =>
          if (ome.error == ErrorMapping.NoError) {
            result += tp -> ome
          } else {
            errs.append(ErrorMapping.exceptionFor(ome.error))
          }
        }
      }
      if (result.keys.size == topicAndPartitions.size) {
        return Right(result)
      }
    }
    val missing = topicAndPartitions.diff(result.keySet)
    errs.append(new SparkException(s"Couldn't find consumer offsets for ${missing}"))
    Left(errs)
  }

  /** Requires Kafka >= 0.8.1.1 */
  def setConsumerOffsets(
                          groupId: String,
                          offsets: Map[TopicAndPartition, Long]
                          ): Either[Err, Map[TopicAndPartition, Short]] =
    setConsumerOffsets(groupId, offsets, defaultConsumerApiVersion)

  def setConsumerOffsets(
                          groupId: String,
                          offsets: Map[TopicAndPartition, Long],
                          consumerApiVersion: Short
                          ): Either[Err, Map[TopicAndPartition, Short]] = {
    val meta = offsets.map { kv =>
      kv._1 -> OffsetAndMetadata(kv._2)
    }
    setConsumerOffsetMetadata(groupId, meta, consumerApiVersion)
  }

  /** Requires Kafka >= 0.8.1.1 */
  def setConsumerOffsetMetadata(
                                 groupId: String,
                                 metadata: Map[TopicAndPartition, OffsetAndMetadata]
                                 ): Either[Err, Map[TopicAndPartition, Short]] =
    setConsumerOffsetMetadata(groupId, metadata, defaultConsumerApiVersion)

  def setConsumerOffsetMetadata(
                                 groupId: String,
                                 metadata: Map[TopicAndPartition, OffsetAndMetadata],
                                 consumerApiVersion: Short
                                 ): Either[Err, Map[TopicAndPartition, Short]] = {
    var result = Map[TopicAndPartition, Short]()
    val req = OffsetCommitRequest(groupId, metadata, consumerApiVersion)
    val errs = new Err
    val topicAndPartitions = metadata.keySet
    withBrokers(Random.shuffle(config.seedBrokers), errs) { consumer =>
      val resp = consumer.commitOffsets(req)
      val respMap = resp.commitStatus
      val needed = topicAndPartitions.diff(result.keySet)
      needed.foreach { tp: TopicAndPartition =>
        respMap.get(tp).foreach { err: Short =>
          if (err == ErrorMapping.NoError) {
            result += tp -> err
          } else {
            errs.append(ErrorMapping.exceptionFor(err))
          }
        }
      }
      if (result.keys.size == topicAndPartitions.size) {
        return Right(result)
      }
    }
    val missing = topicAndPartitions.diff(result.keySet)
    errs.append(new SparkException(s"Couldn't set offsets for ${missing}"))
    Left(errs)
  }

  // Try a call against potentially multiple brokers, accumulating errors
  private def withBrokers(brokers: Iterable[(String, Int)], errs: Err)
                         (fn: SimpleConsumer => Any): Unit = {
    //brokers进行遍历,对于每个broker port,都进行一次打开,如果能够正常打开,就关闭.
    brokers.foreach { hp =>
      var consumer: SimpleConsumer = null
      try {
        consumer = connect(hp._1, hp._2)
        fn(consumer)
      } catch {
        case NonFatal(e) =>
          errs.append(e)
      } finally {
        if (consumer != null) {
          consumer.close()
        }
      }
    }
  }
}


object KafkaCluster {
  type Err = ArrayBuffer[Throwable]

  /** If the result is right, return it, otherwise throw SparkException */
  // 这里主要是针对Either的fold方法,该方法接收返回相同类型的变换函数,当Either为left时
  // 第一个函数被调用,否则第二个函数被调用.
  def checkErrors[T](result: Either[Err, T]): T = {
    result.fold(
      errs => throw new SparkException(errs.mkString("\n")),
      ok => ok
    )
  }
  //这里LeaderOffset是指的leader的host和port,offset,没有指明一个topic,那么这里的偏移又是指什么呢?

  case class LeaderOffset(host: String, port: Int, offset: Long)

  /**
   * High-level kafka consumers connect to ZK.  ConsumerConfig assumes this use case.
   * Simple consumers connect directly to brokers, but need many of the same configs.
   * This subclass won't warn about missing ZK params, or presence of broker params.
   * 高层次连接到ZK的Kafka消费者,ConsumerConfig假定该用例.Simple consumer直连到brokers,但是需要很多相同的配置
   * 该子类将不警告关于丢失的ZK参数和broker参数的出现
   */

  class SimpleConsumerConfig private(brokers: String, originalProps: Properties)
    extends ConsumerConfig(originalProps) {
    //该函数将 server1:port1,server2:port2转变成Array((server1,port1),(server2,port2))形式
    val seedBrokers: Array[(String, Int)] = brokers.split(",").map { hp =>
      val hpa = hp.split(":")
      if (hpa.size == 1) {
        throw new SparkException(s"Broker not the in correct format of <host>:<port> [$brokers]")
      }
      (hpa(0), hpa(1).toInt)
    }
  }


  object SimpleConsumerConfig {
    /**
     * Make a consumer config without requiring group.id or zookeeper.connect,
     * since communicating with brokers also needs common settings such as timeout
     * 构造一个consumer配置,不需要group.id和zookeeper.connect
     */
    def apply(kafkaParams: Map[String, String]): SimpleConsumerConfig = {
      // These keys are from other pre-existing kafka configs for specifying brokers, accept either
      // 判断是否指定了metadata.broker.list或者bootstrap.servers,如果两个都不存在,则抛出异常.
      val brokers = kafkaParams.get("metadata.broker.list")
        .orElse(kafkaParams.get("bootstrap.servers"))
        .getOrElse(throw new SparkException(
          "Must specify metadata.broker.list or bootstrap.servers"))

      val props = new Properties()
      kafkaParams.foreach { case (key, value) =>
        // prevent warnings on parameters ConsumerConfig doesn't know about
        if (key != "metadata.broker.list" && key != "bootstrap.servers") {
          props.put(key, value)
        }
      }
      //如果zookeeper.connect和group.id不存在,则默认为空.存在了,就用原来的值
      //这里的问题是,这两个参数存在的情况下,会否采用呢
      Seq("zookeeper.connect", "group.id").foreach { s =>
        if (!props.containsKey(s)) {
          props.setProperty(s, "")
        }
      }

      new SimpleConsumerConfig(brokers, props)
    }
  }
}
