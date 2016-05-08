package com.kaltura.model.events

import org.joda.time.DateTime

case class RawPlayerEvent(eventTime: DateTime,
                          remoteAddr: String = "",
                          proxyRemoteAddr: String = "",
                          userAgent: String = "",
                          params: Map[String,String]) {
  lazy val partnerId: Option[Int] = {
    try {
      Some(params.get("partnerId").get.toInt)
    } catch {
      case e: Exception => None
    }
  }

  lazy val eventType: Option[String] = params.get("eventType")
  lazy val entryId: Option[String] = params.get("entryId")

  def isValid: Boolean = eventType.isDefined && eventType.get.nonEmpty && partnerId.isDefined && entryId.isDefined && entryId.get.nonEmpty
}
