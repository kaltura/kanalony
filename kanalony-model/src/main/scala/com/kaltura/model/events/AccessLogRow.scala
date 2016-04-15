package com.kaltura.model.events

import org.joda.time.DateTime

case class AccessLogRow(
                        host:String,
                        request:String,
                        eventTime:DateTime,
                        remoteAddr:String,
                        proxyRemoteAddr:String,
                        userAgent:String,
                        params:Map[String,String]
                        )