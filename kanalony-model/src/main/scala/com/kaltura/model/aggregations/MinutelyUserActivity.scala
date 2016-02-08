package com.kaltura.model.aggregations

import com.kaltura.core.userAgent.enums.{Browser, OperatingSystem, Device}
import org.joda.time.DateTime

case class MinutelyPartner(partnerId: Int, eventType: Int, minute: DateTime, count: Long)
case class MinutelyPartnerCountry(partnerId: Int, eventType: Int, minute: DateTime, country: String, count: Long)
case class MinutelyPartnerCountryCity(partnerId: Int, eventType: Int, minute: DateTime, country: String, city: String, count: Long)
case class MinutelyPartnerDomain(partnerId: Int, eventType: Int, minute: DateTime, domain: String, count: Long)
case class MinutelyPartnerDomainReferrer(partnerId: Int, eventType: Int, minute: DateTime, referrer: String, count: Long)
case class MinutelyPartnerReferrer(partnerId: Int, eventType: Int, minute: DateTime, domain: String, referrer: String, count: Long)
case class MinutelyPartnerDevice(partnerId: Int, eventType: Int, minute: DateTime, device: Device.Value, count: Long)
case class MinutelyPartnerOperatingSystem(partnerId: Int, eventType: Int, minute: DateTime, os: OperatingSystem.Value, count: Long)
case class MinutelyPartnerBrowser(partnerId: Int, eventType: Int, minute: DateTime, browser: Browser.Value, count: Long)
case class MinutelyPartnerDeviceOperatingSystem(partnerId: Int, eventType: Int, minute: DateTime, device: Device.Value, os: OperatingSystem.Value, count: Long)
case class MinutelyPartnerOperationSystemBrowser(partnerId: Int, eventType: Int, minute: DateTime, os: OperatingSystem.Value, browser: Browser.Value)
case class MinutelyPartnerApplication(partnerId: Int, eventType: Int, minute: DateTime, application: String)
case class MinutelyPartnerPlaybackContext(partnerId: Int, eventType: Int, minute: DateTime, playbackContext: Int)
case class MinutelyPartnerApplicationPlaybackContext(partnerId: Int, eventType: Int, minute: DateTime, application: String, playbackContext: Int)

case class MinutelyEntry(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, count: Long)
case class MinutelyEntryCountry(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, country: String, count: Long)
case class MinutelyEntryCountryCity(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, country: String, city: String, count: Long)
case class MinutelyEntryDomain(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, domain: String, count: Long)
case class MinutelyEntryDomainReferrer(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, referrer: String, count: Long)
case class MinutelyEntryReferrer(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, domain: String, referrer: String, count: Long)
case class MinutelyEntryDevice(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, device: Device.Value, count: Long)
case class MinutelyEntryOperatingSystem(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, os: OperatingSystem.Value, count: Long)
case class MinutelyEntryBrowser(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, browser: Browser.Value, count: Long)
case class MinutelyEntryDeviceOperatingSystem(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, device: Device.Value, os: OperatingSystem.Value, count: Long)
case class MinutelyEntryOperationSystemBrowser(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, os: OperatingSystem.Value, browser: Browser.Value)
case class MinutelyEntryApplication(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, application: String)
case class MinutelyEntryPlaybackContext(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, playbackContext: Int)
case class MinutelyEntryApplicationPlaybackContext(partnerId: Int, entryId: String, eventType: Int, minute: DateTime, application: String, playbackContext: Int)
