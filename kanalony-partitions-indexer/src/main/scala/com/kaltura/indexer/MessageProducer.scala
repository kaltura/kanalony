package com.kaltura.indexer

import com.kaltura.core.streaming.StreamManager
import com.kaltura.model.events.{AccessLogRow, PlayerEventParser, RawPlayerEvent}
import org.apache.kafka.clients.producer.ProducerRecord
import org.joda.time.DateTime

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

    var playerEvent1: AccessLogRow = null
    var playerEvent2: RawPlayerEvent = null

    val startDate = new DateTime("2016-06-10T10:58:00+0000")
    for( i <- 1 to 500) {
      playerEvent1 = AccessLogRow(host = "", request = "", eventTime = startDate.withDurationAdded(10000*i, 1), remoteAddr = "62.0.105.133", proxyRemoteAddr = "", userAgent = "mmm", params = Map(("service", "stats"), ("action", "collect"), ("kalsig", "e42d08dff4f5cdf458a97170b9fd55d4"), ("apiVersion", "3.1.5"), ("referrer", "http%3A//vid.modamob.com/lips-1.html"), ("entryId", "1_ab2cd3ef"), ("clientTag", "kdp:v3.9.9"), ("clientVer", "3.0:v3.9.9"), ("objectType", "KalturaStatsEvent"), ("sessionId", "729D113A-C3DA-B68D-7D7F-2124BBA20F30"), ("eventType", "2"), ("partnerId", "654321"), ("ks", "djJ8MTQ1OTU3MXwTZWnrTbeRHjfPGoGSWctH92eX4wGDnY1n_s8UwAntc1sf7nn-AJZgvOyR9gf8xlhOAg6MKTDHy3-ZW3Q0YuoL2jcCxSdhLZtede42dUkSmA=="), ("uiconfId", "21042952")))
      //playerEvent2 = RawPlayerEvent(eventTime = DateTime.now(), remoteAddr = "62.0.105.133", userAgent = s"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36", params = Map(("service", "stats"), ("action", "collect"), ("kalsig", "e42d08dff4f5cdf458a97170b9fd55d4"), ("apiVersion", "3.1.5"), ("referrer", "http%3A//vid.modamob.com/lips-1.html"), ("entryId", "1_pkp8i7ux"), ("clientTag", "kdp:v3.9.9"), ("clientVer", "3.0:v3.9.9"), ("objectType", "KalturaStatsEvent"), ("sessionId", "729D113A-C3DA-B68D-7D7F-2124BBA20F30"), ("eventType", "2"), ("partnerId", "1459571"), ("ks", "djJ8MTQ1OTU3MXwTZWnrTbeRHjfPGoGSWctH92eX4wGDnY1n_s8UwAntc1sf7nn-AJZgvOyR9gf8xlhOAg6MKTDHy3-ZW3Q0YuoL2jcCxSdhLZtede42dUkSmA=="), ("partnerId", "1459571"), ("uiconfId", "21042952")))
      producer.send(new ProducerRecord[String, String]("player-events", i.toString, PlayerEventParser.asJson(playerEvent1)))
      Thread.sleep(1000)
    }

    producer.close()
  }

}
