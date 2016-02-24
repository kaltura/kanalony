package com.kaltura.model.aggregations

import com.kaltura.core.userAgent.enums.{Browser, OperatingSystem, Device}
import org.joda.time.DateTime

case class MinutelyPartner(partnerId: Int, metric: Int, minute: DateTime, value: Long)
case class MinutelyPartnerCountry(partnerId: Int, metric: Int, minute: DateTime, country: String, value: Long)
case class MinutelyPartnerCountryCity(partnerId: Int, metric: Int, minute: DateTime, country: String, city: String, value: Long)
case class MinutelyPartnerDomain(partnerId: Int, metric: Int, minute: DateTime, domain: String, value: Long)
case class MinutelyPartnerDomainReferrer(partnerId: Int, metric: Int, minute: DateTime, referrer: String, value: Long)
case class MinutelyPartnerReferrer(partnerId: Int, metric: Int, minute: DateTime, domain: String, referrer: String, value: Long)
case class MinutelyPartnerDevice(partnerId: Int, metric: Int, minute: DateTime, device: Device.Value, value: Long)
case class MinutelyPartnerOperatingSystem(partnerId: Int, metric: Int, minute: DateTime, os: OperatingSystem.Value, value: Long)
case class MinutelyPartnerBrowser(partnerId: Int, metric: Int, minute: DateTime, browser: Browser.Value, value: Long)
case class MinutelyPartnerDeviceOperatingSystem(partnerId: Int, metric: Int, minute: DateTime, device: Device.Value, os: OperatingSystem.Value, value: Long)
case class MinutelyPartnerOperationSystemBrowser(partnerId: Int, metric: Int, minute: DateTime, os: OperatingSystem.Value, browser: Browser.Value)
case class MinutelyPartnerApplication(partnerId: Int, metric: Int, minute: DateTime, application: String)
case class MinutelyPartnerPlaybackContext(partnerId: Int, metric: Int, minute: DateTime, playbackContext: Int)
case class MinutelyPartnerApplicationPlaybackContext(partnerId: Int, metric: Int, minute: DateTime, application: String, playbackContext: Int)

case class MinutelyEntry(partnerId: Int, entryId: String, metric: Int, day: Int, minute: DateTime, value: Long)
case class MinutelyEntryCountry(partnerId: Int, entryId: String, metric: Int, minute: DateTime, country: String, value: Long)
case class MinutelyEntryCountryCity(partnerId: Int, entryId: String, metric: Int, minute: DateTime, country: String, city: String, value: Long)
case class MinutelyEntryDomain(partnerId: Int, entryId: String, metric: Int, minute: DateTime, domain: String, value: Long)
case class MinutelyEntryDomainReferrer(partnerId: Int, entryId: String, metric: Int, minute: DateTime, referrer: String, value: Long)
case class MinutelyEntryReferrer(partnerId: Int, entryId: String, metric: Int, minute: DateTime, domain: String, referrer: String, value: Long)
case class MinutelyEntryDevice(partnerId: Int, entryId: String, metric: Int, minute: DateTime, device: Device.Value, value: Long)
case class MinutelyEntryOperatingSystem(partnerId: Int, entryId: String, metric: Int, minute: DateTime, os: OperatingSystem.Value, value: Long)
case class MinutelyEntryBrowser(partnerId: Int, entryId: String, metric: Int, minute: DateTime, browser: Browser.Value, value: Long)
case class MinutelyEntryDeviceOperatingSystem(partnerId: Int, entryId: String, metric: Int, minute: DateTime, device: Device.Value, os: OperatingSystem.Value, value: Long)
case class MinutelyEntryOperationSystemBrowser(partnerId: Int, entryId: String, metric: Int, minute: DateTime, os: OperatingSystem.Value, browser: Browser.Value)
case class MinutelyEntryApplication(partnerId: Int, entryId: String, metric: Int, minute: DateTime, application: String)
case class MinutelyEntryPlaybackContext(partnerId: Int, entryId: String, metric: Int, minute: DateTime, playbackContext: Int)
case class MinutelyEntryApplicationPlaybackContext(partnerId: Int, entryId: String, metric: Int, minute: DateTime, application: String, playbackContext: Int)
