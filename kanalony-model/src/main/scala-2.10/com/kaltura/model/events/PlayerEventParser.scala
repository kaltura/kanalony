package com.kaltura.model.events

import java.text.SimpleDateFormat

import org.apache.spark.Logging
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization._

object PlayerEventParser extends Logging {
  implicit val formats = new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  } ++ org.json4s.ext.JodaTimeSerializers.all

  def parsePlayerEvent(playerEvent: String): Option[RawPlayerEvent] = {
    try {
      Some(parse(playerEvent).extract[RawPlayerEvent])
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

}


