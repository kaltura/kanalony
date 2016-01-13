package com.kaltura.model.events

import java.text.SimpleDateFormat

import com.kaltura.core.urls.UrlParser
import org.apache.spark.Logging
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization._
import scala.collection.mutable

object PlayerEventParser extends Logging {
  implicit val formats = new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  } ++ org.json4s.ext.JodaTimeSerializers.all

  def parsePlayerEvent(playerEvent: String): Option[RawPlayerEvent] = {
    try {
      val row:AccessLogRow = parse(playerEvent).extract[AccessLogRow]
      val params = new mutable.ListMap[String,String]
      UrlParser.parseUrl(row.request).foreach(pair =>
        params(pair.key) = pair.value
      )
      Some(RawPlayerEvent(row.eventTime,
                          row.remoteIp,
                          row.userAgent,
                          params.toMap))
    }
    catch {
      case e: Exception => {
        logError(s"Unable to parse log row: $playerEvent, error: $e")
        None
      }
    }
  }

  def parseEnhancedPlayerEvent(playerEvent: String): Option[EnrichedPlayerEvent] = {
    try {
      Some(parse(playerEvent).extract[EnrichedPlayerEvent])
    }
    catch {
      case e: Exception => {
        None
      }
    }
  }


  def asJson(playerEvent: RawPlayerEvent): String = {
    write(playerEvent)
  }

  def asJson(enrichedPlayerEvent: EnrichedPlayerEvent) : String = {
    write(enrichedPlayerEvent);
  }

}


