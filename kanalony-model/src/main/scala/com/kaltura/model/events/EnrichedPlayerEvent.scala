package com.kaltura.model.events

import com.kaltura.core.ip2location.Location
import com.kaltura.core.urls.UrlParts
import com.kaltura.core.userAgent.UserAgent
import org.joda.time.DateTime

case class EnrichedPlayerEvent (eventType:  Int,
                                eventTime:  DateTime,
                                partnerId:  Int,
                                entryId:    String,
                                flavourId:  String = "",
                                userId:     String = "",
                                location:   Location = null,
                                userAgent:  UserAgent = null,
                                urlParts:   UrlParts = null,
                                kalsig:     String = "")
