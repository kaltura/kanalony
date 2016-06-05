package com.kaltura.indexer

import com.kaltura.core.streaming.StreamManager
import org.apache.kafka.clients.producer.ProducerRecord

/**
 * Created by orlylampert on 5/31/16.
 */
object MessageProducer extends App {
  override def main(args: Array[String]): Unit = {
    pushTestEvents()
  }

  def pushTestEvents() = {

    val kafkaBrokers = "127.0.0.1:9092"
    val producer = StreamManager.createProducer(kafkaBrokers)

    producer.send(new ProducerRecord[String, String]("topic1", "1", "t1m1"))
    producer.send(new ProducerRecord[String, String]("topic1", "2", "t1m2"))
    producer.send(new ProducerRecord[String, String]("topic1", "3", "t1m3"))
    producer.send(new ProducerRecord[String, String]("topic1", "4", "t1m4"))
    producer.send(new ProducerRecord[String, String]("topic1", "5", "t1m5"))
    producer.send(new ProducerRecord[String, String]("topic1", "6", "t1m6"))
    producer.send(new ProducerRecord[String, String]("topic1", "7", "t1m7"))

    producer.send(new ProducerRecord[String, String]("topic2", "1", "t2m1"))
    producer.send(new ProducerRecord[String, String]("topic2", "2", "t2m2"))
    producer.send(new ProducerRecord[String, String]("topic2", "3", "t2m3"))
    producer.send(new ProducerRecord[String, String]("topic2", "4", "t2m4"))
    producer.send(new ProducerRecord[String, String]("topic2", "5", "t2m5"))
    producer.send(new ProducerRecord[String, String]("topic2", "6", "t2m6"))
    producer.send(new ProducerRecord[String, String]("topic2", "7", "t2m7"))
    producer.send(new ProducerRecord[String, String]("topic2", "8", "t2m8"))
    producer.send(new ProducerRecord[String, String]("topic2", "9", "t2m9"))



    producer.close()
  }

}
