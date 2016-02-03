package com.kaltura.model.events

import org.joda.time.DateTime

case class RawPlayerEvent(eventTime: DateTime, remoteAddr: String = "", proxyRemoteAddr: String = "", userAgent: String = "", params: Map[String,String])
