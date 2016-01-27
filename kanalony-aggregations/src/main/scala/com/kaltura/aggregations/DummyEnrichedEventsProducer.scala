package com.kaltura.aggregations

import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.{PlayerEventParser, EnrichedPlayerEvent}
import org.apache.kafka.clients.producer.ProducerRecord
import org.joda.time.DateTime

object DummyEnrichedEventsProducer extends App{

  override def main(args: Array[String]): Unit = {
    pushTestEvents()
  }

  def pushTestEvents() = {

    var playerEvent1: EnrichedPlayerEvent = null
    var playerEvent2: EnrichedPlayerEvent = null
    var playerEvent3: EnrichedPlayerEvent = null
    var playerEvent4: EnrichedPlayerEvent = null

    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_enhancer.kafka_brokers","127.0.0.1:9092")
    val producer = StreamManager.createProducer(kafkaBrokers)

    var i = 0
    for( i <- 1 to 3){
      playerEvent1 = EnrichedPlayerEvent(eventType = 3, eventTime = DateTime.now(), partnerId = 1234, entryId = "1_ydsauhks")
      playerEvent2 = EnrichedPlayerEvent(eventType = 4, eventTime = DateTime.now(), partnerId = 1234, entryId = "1_ydsauhks")
      playerEvent3 = EnrichedPlayerEvent(eventType = 3, eventTime = DateTime.now(), partnerId = 1234, entryId = "1_test")
      playerEvent4 = EnrichedPlayerEvent(eventType = 4, eventTime = DateTime.now(), partnerId = 1234, entryId = "1_ydsauhks")

      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent1)))
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent2)))
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent3)))
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent4)))


      Thread.sleep(1000 * 25)
    }

    producer.close()
  }
}
