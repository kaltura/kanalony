package com.kaltura.model.events

import java.text.SimpleDateFormat

import org.apache.spark.Logging
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._


/**
 * Created by orlylampert on 5/30/16.
 */
object TimeEventParser extends Logging {
  implicit val formats = new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  } ++ org.json4s.ext.JodaTimeSerializers.all

  def parseTimeEvent(timeEvent: String): Option[TimeEvent] = {
    try {
      Some(parse(timeEvent).extract[TimeEvent])
    }
    catch {
      case e: Exception => {
        logWarning(s"Unable to parse log row: $timeEvent, error: $e")
        None
      }
    }
  }
}