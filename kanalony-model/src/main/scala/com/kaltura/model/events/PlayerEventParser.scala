package com.kaltura.model.events

import java.text.SimpleDateFormat

import com.kaltura.core.userAgent.enums.{Browser, Device, OperatingSystem}
import org.apache.spark.Logging
import org.json4s.DefaultFormats
import org.json4s.ext.EnumNameSerializer
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization._

object PlayerEventParser extends Logging {
  implicit val formats = new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  } ++ org.json4s.ext.JodaTimeSerializers.all +
    new EnumNameSerializer(Browser) +
    new EnumNameSerializer(OperatingSystem) +
    new EnumNameSerializer(Device)

  def parsePlayerEvent(playerEvent: String): Option[RawPlayerEvent] = {
    try {
      val row:AccessLogRow = parse(playerEvent).extract[AccessLogRow]
      Some(RawPlayerEvent(row.eventTime,
                          row.remoteAddr,
                          row.proxyRemoteAddr,
                          row.userAgent,
                          row.params))
    }
    catch {
      case e: Exception => {
        logWarning(s"Unable to parse log row: $playerEvent, error: $e")
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
        logWarning(s"Unable to enriched player event: $playerEvent, error: $e")
        None
      }
    }
  }


  def asJson(playerEvent: RawPlayerEvent): String = {
    write(playerEvent)
  }

  def asJson(enrichedPlayerEvent: EnrichedPlayerEvent) : String = {
    write(enrichedPlayerEvent)
  }

  def asJson(accessLogRow: AccessLogRow) :String = {
    write(accessLogRow)
  }

}


