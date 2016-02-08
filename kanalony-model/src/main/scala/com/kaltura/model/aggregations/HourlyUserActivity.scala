package com.kaltura.model.aggregations

import org.joda.time.DateTime
import com.kaltura.core.userAgent.enums.{Browser, OperatingSystem, Device}

case class HourlyPartnerUserActivity(partnerId: Int, eventType: Int, year: Int, hour: DateTime, count: Long)
case class HourlyPartnerEntry(partnerId: Int, entryId: String, eventType: Int, year: Int, hour: DateTime, count: Long)
case class HourlyPartnerCountry(partnerId: Int, eventType: Int, year: Int, hour: DateTime, country: String, count: Long)
case class HourlyPartnerCountryCity(partnerId: Int, eventType: Int, year: Int, hour: DateTime, country: String, city: String, count: Long)
case class HourlyPartnerDomain(partnerId: Int, eventType: Int, year: Int, hour: DateTime, domain: String, count: Long)
case class HourlyPartnerDomainReferrer(partnerId: Int, eventType: Int, year: Int, hour: DateTime, referrer: String, count: Long)
case class HourlyPartnerReferrer(partnerId: Int, eventType: Int, year: Int, hour: DateTime, domain: String, referrer: String, count: Long)
case class HourlyPartnerDevice(partnerId: Int, eventType: Int, year: Int, hour: DateTime, device: Device.Value, count: Long)
case class HourlyPartnerOperatingSystem(partnerId: Int, eventType: Int, year: Int, hour: DateTime, os: OperatingSystem.Value, count: Long)
case class HourlyPartnerBrowser(partnerId: Int, eventType: Int, year: Int, hour: DateTime, browser: Browser.Value, count: Long)
case class HourlyPartnerDeviceOperatingSystem(partnerId: Int, eventType: Int, year: Int, hour: DateTime, device: Device.Value, os: OperatingSystem.Value, count: Long)
case class HourlyPartnerOperationSystemBrowser(partnerId: Int, eventType: Int, year: Int, hour: DateTime, os: OperatingSystem.Value, browser: Browser.Value)
case class HourlyPartnerApplication(partnerId: Int, eventType: Int, year: Int, hour: DateTime, application: String)
case class HourlyPartnerPlaybackContext(partnerId: Int, eventType: Int, year: Int, hour: DateTime, playbackContext: Int)
case class HourlyPartnerApplicationPlaybackContext(partnerId: Int, eventType: Int, year: Int, hour: DateTime, application: String, playbackContext: Int)
case class HourlyPartnerCountryOperatingSystemBrowser(partnerId: Int, eventType: Int, year: Int, hour: DateTime, country: String, os: OperatingSystem.Value, browser: Browser.Value)
