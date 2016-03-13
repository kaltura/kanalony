package com.kaltura.aggregations.keys

import org.joda.time.DateTime

case class UserActivityEntryKey (partnerId: Int, entryId: String, metric: Int, time: DateTime) extends Serializable
case class UserActivityKey(partnerId: Int, metric: Int, time: DateTime) extends Serializable
case class UserActivityCountryKey(partnerId: Int, metric: Int, time: DateTime, country: String) extends Serializable
case class UserActivityCountryCityKey(partnerId: Int, metric: Int, time: DateTime, country: String, city: String) extends Serializable
case class UserActivityDomainKey(partnerId: Int, metric: Int, time: DateTime, domain: String) extends Serializable
case class UserActivityDomainReferrerKey(partnerId: Int, metric: Int, time: DateTime, domain: String, referrer: String) extends Serializable
case class UserActivityDeviceKey(partnerId: Int, metric: Int, time: DateTime, device: Int) extends Serializable
case class UserActivityOperatingSystemKey(partnerId: Int, metric: Int, time: DateTime, operatingSystem: Int) extends Serializable
case class UserActivityBrowserKey(partnerId: Int, metric: Int, time: DateTime, browser: Int) extends Serializable
case class UserActivityDeviceOperatingSystemKey(partnerId: Int, metric: Int, time: DateTime, device: Int, operatingSystem: Int) extends Serializable
case class UserActivityOperatingSystemBrowserKey(partnerId: Int, metric: Int, time: DateTime, operatingSystem: Int, browser: Int) extends Serializable
case class UserActivityCountryOperatingSystemBrowserKey(partnerId: Int, metric: Int, time: DateTime, country: String, operatingSystem: Int, browser: Int) extends Serializable
case class UserActivityCountryOperatingSystemKey(partnerId: Int, metric: Int, time: DateTime, country: String, operatingSystem: Int) extends Serializable
case class UserActivityCountryBrowserKey(partnerId: Int, metric: Int, time: DateTime, country: String, browser: Int) extends Serializable
case class UserActivityApplicationKey(partnerId: Int, metric: Int, time: DateTime, application: String) extends Serializable
case class UserActivityPlaybackContextKey(partnerId: Int, metric: Int, time: DateTime, playbackContext: String) extends Serializable
case class UserActivityApplicationPlaybackContextKey(partnerId: Int, metric: Int, time: DateTime, application: String, playbackContext: String) extends Serializable
case class UserActivityCustomVarKey(partnerId: Int, metric: Int, time: DateTime, cv: String) extends Serializable
case class UserActivityCustomVar1CustomVar2Key(partnerId: Int, metric: Int, time: DateTime, cv1: String, cv2: String) extends Serializable
case class UserActivityCustomVar1CustomVar2CustomVar3Key(partnerId: Int, metric: Int, time: DateTime, cv1: String, cv2: String, cv3: String) extends Serializable


case class UserActivityEntryApplicationKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, application: String) extends Serializable
case class UserActivityEntryApplicationPlaybackContextKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, application: String, playbackContext: String) extends Serializable
case class UserActivityEntryCountryKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, country: String) extends Serializable
case class UserActivityEntryCountryCityKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, country: String, city: String) extends Serializable
case class UserActivityEntryDomainKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, domain: String) extends Serializable
case class UserActivityEntryDomainReferrerKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, domain: String, referrer: String) extends Serializable
case class UserActivityEntryDeviceKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, device: Int) extends Serializable
case class UserActivityEntryOperatingSystemKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, operatingSystem: Int) extends Serializable
case class UserActivityEntryBrowserKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, browser: Int) extends Serializable
case class UserActivityEntryDeviceOperatingSystemKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, device: Int, operatingSystem: Int) extends Serializable
case class UserActivityEntryOperatingSystemBrowserKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, operatingSystem: Int, browser: Int) extends Serializable
case class UserActivityEntryPlaybackContextKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, playbackContext: String) extends Serializable
case class UserActivityEntryCFKey(partnerId: Int, entryId: String, metric: Int, time: DateTime, cf: String) extends Serializable
case class UserActivityEntryCF1CF2Key(partnerId: Int, entryId: String, metric: Int, time: DateTime, cf1: String, cf2: String) extends Serializable
case class UserActivityEntryCF1CF2CF3Key(partnerId: Int, entryId: String, metric: Int, time: DateTime, cf1: String, cf2: String, cf3: String) extends Serializable
