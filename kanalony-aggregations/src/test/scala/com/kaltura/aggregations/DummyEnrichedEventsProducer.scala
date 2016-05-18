package com.kaltura.aggregations

import com.kaltura.core.ip2location.Location
import com.kaltura.core.streaming.StreamManager
import com.kaltura.core.urls.UrlParts
import com.kaltura.core.userAgent.UserAgent
import com.kaltura.core.userAgent.enums.{Browser, Device, OperatingSystem}
import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.entities.PlayerEventTypes
import com.kaltura.model.events.{EnrichedPlayerEvent, PlayerEventParser}
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


    val kafkaBrokers = ConfigurationManager.getOrElse("kanalony.events_enrichment.kafka_brokers","127.0.0.1:9092")
    val producer = StreamManager.createProducer(kafkaBrokers)

    var i = 0
    for( i <- 1 to 1){
      playerEvent1 = EnrichedPlayerEvent(eventType = PlayerEventTypes.play.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry1", playbackType = "Live", location = Location(country = "US", city = "NewYork"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app1", customVar1 = "R&D", customVar2 = "Backend", playbackContext = "123456", customVar3 = "a", categories = "1,12349232,45304959,56789000,10983465738")
      playerEvent2 = EnrichedPlayerEvent(eventType = PlayerEventTypes.view.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry1", playbackType = "Live", location = Location(country = "US", city = "NewYork"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app1", customVar1 = "R&D", customVar2 = "Backend", playbackContext = "123456", customVar3 = "a", categories = "1,12349232,45304959,56789000,10983465738")

      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent1)))
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent2)))

      println(PlayerEventParser.asJson(playerEvent1))
      println(PlayerEventParser.asJson(playerEvent2))

      Thread.sleep(1000*2)


      playerEvent1 = EnrichedPlayerEvent(eventType = PlayerEventTypes.play.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry2", playbackType = "Live", location = Location(country = "US", city = "NewYork"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app2", customVar1 = "R&D", customVar2 = "Live", playbackContext = "98765", customVar3 = "b",categories = "1,3456123,44444444")
      playerEvent2 = EnrichedPlayerEvent(eventType = PlayerEventTypes.view.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry2", playbackType = "Live", location = Location(country = "US", city = "NewYork"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app2", customVar1 = "R&D", customVar2 = "Live", playbackContext = "98765", customVar3 = "b",categories = "1,3456123,44444444")

      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent1)))
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent2)))

      println(PlayerEventParser.asJson(playerEvent1))
      println(PlayerEventParser.asJson(playerEvent2))

      Thread.sleep(1000*8)

      playerEvent1 = EnrichedPlayerEvent(eventType = PlayerEventTypes.view.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry1", playbackType = "Live", location = Location(country = "US", city = "NewYork"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app1", customVar1 = "R&D", customVar2 = "Backend", playbackContext = "123456", customVar3 = "a",categories = "1,12349232,45304959,56789000,10983465738")
      playerEvent2 = EnrichedPlayerEvent(eventType = PlayerEventTypes.view.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry2", playbackType = "Live", location = Location(country = "US", city = "NewYork"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app2", customVar1 = "R&D", customVar2 = "Live", playbackContext = "98765", customVar3 = "b",categories = "1,3456123,44444444")
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent1)))
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent2)))

      println(PlayerEventParser.asJson(playerEvent1))
      println(PlayerEventParser.asJson(playerEvent2))

      Thread.sleep(1000*2)

      playerEvent1 = EnrichedPlayerEvent(eventType = PlayerEventTypes.play.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry3", playbackType = "Live", location = Location(country = "US", city = "Boston"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app1", customVar1 = "R&D", customVar2 = "Live", playbackContext = "98765", customVar3 = "b",categories = "1,450123,33")
      playerEvent2 = EnrichedPlayerEvent(eventType = PlayerEventTypes.view.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry3", playbackType = "Live", location = Location(country = "US", city = "Boston"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app1", customVar1 = "R&D", customVar2 = "Live", playbackContext = "98765", customVar3 = "b",categories = "1,450123,33")
      playerEvent3 = EnrichedPlayerEvent(eventType = PlayerEventTypes.view.toString, eventTime = DateTime.now(), partnerId = 1, entryId = "entry4", playbackType = "Live", location = Location(country = "US", city = "Boston"), userAgent = UserAgent(device = Device.COMPUTER, operatingSystem = OperatingSystem.WINDOWS_10, browser = Browser.CHROME46), urlParts = UrlParts(domain = "www.google.com", canonicalUrl = "www.google.com/a_123", originalUrl = "www.google.com/a_123"), application = "app1", customVar1 = "R&D", customVar2 = "Live", playbackContext = "98765", customVar3 = "b",categories = "1,450123,33")

      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent1)))
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent2)))
      producer.send(new ProducerRecord[String,String]("enriched-player-events", null, PlayerEventParser.asJson(playerEvent3)))

      println(PlayerEventParser.asJson(playerEvent1))
      println(PlayerEventParser.asJson(playerEvent2))
      println(PlayerEventParser.asJson(playerEvent3))


    }

    producer.close()
  }
}
