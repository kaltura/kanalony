package com.kaltura.model.aggregations

import com.kaltura.core.userAgent.enums.{Browser, OperatingSystem, Device}
import org.joda.time.DateTime

case class TesSecsPartnerUserActivity(partnerId: Int, eventType: Int, time: DateTime, count: Long)
case class TenSecsPartnerEntry(partnerId: Int, entryId: String, eventType: Int, time: DateTime, count: Long)
case class TenSecsPartnerCountry(partnerId: Int, eventType: Int, time: DateTime, country: String, count: Long)
case class TenSecsPartnerCountryCity(partnerId: Int, eventType: Int, time: DateTime, country: String, city: String, count: Long)
case class TenSecsPartnerDomain(partnerId: Int, eventType: Int, time: DateTime, domain: String, count: Long)
case class TenSecsPartnerDomainReferrer(partnerId: Int, eventType: Int, time: DateTime, referrer: String, count: Long)
case class TenSecsPartnerReferrer(partnerId: Int, eventType: Int, time: DateTime, domain: String, referrer: String, count: Long)
case class TenSecsPartnerDevice(partnerId: Int, eventType: Int, time: DateTime, device: Device.Value, count: Long)
case class TenSecsPartnerOperatingSystem(partnerId: Int, eventType: Int, time: DateTime, os: OperatingSystem.Value, count: Long)
case class TenSecsPartnerBrowser(partnerId: Int, eventType: Int, time: DateTime, browser: Browser.Value, count: Long)
case class TenSecsPartnerDeviceOperatingSystem(partnerId: Int, eventType: Int, time: DateTime, device: Device.Value, os: OperatingSystem.Value, count: Long)
case class TenSecsPartnerOperationSystemBrowser(partnerId: Int, eventType: Int, time: DateTime, os: OperatingSystem.Value, browser: Browser.Value)
case class TenSecsPartnerApplication(partnerId: Int, eventType: Int, time: DateTime, application: String)
case class TenSecsPartnerPlaybackContext(partnerId: Int, eventType: Int, time: DateTime, playbackContext: Int)
case class TenSecsPartnerApplicationPlaybackContext(partnerId: Int, eventType: Int, time: DateTime, application: String, playbackContext: Int)

