package com.kaltura.model.events

import org.joda.time.DateTime

case class RawPlayerEvent(eventTime: DateTime, remoteIp: String = "", userAgent: String = "", params: Map[String,String])
