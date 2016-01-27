package com.kaltura.model.events

import org.joda.time.DateTime

case class AccessLogRow(
                        host:String,
                        request:String,
                        eventTime:DateTime,
                        remoteIp:String,
                        userAgent:String
                        )