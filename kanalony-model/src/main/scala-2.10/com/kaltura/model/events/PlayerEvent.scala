package com.kaltura.model.events

import com.kaltura.core.ip2location.Location
import com.kaltura.core.urls.UrlParts
import com.kaltura.core.userAgent.UserAgent
import org.joda.time.DateTime

case class PlayerEvent (eventType: Int,
                        eventTime:  DateTime,
                        partnerId:  Int,
                        entryId:    String,
                        userId:     String,
                        location:   Location,
                        userAgent:  UserAgent,
                        urlParts:   UrlParts,
                        kalsig:     String)
