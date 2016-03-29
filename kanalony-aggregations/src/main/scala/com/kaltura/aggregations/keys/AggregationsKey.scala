package com.kaltura.aggregations.keys

import org.joda.time.DateTime

case class AggregationEntryKey (partnerId: Int, entryId: String, metric: String, time: DateTime) extends Serializable
case class AggregationKey(partnerId: Int, metric: String, time: DateTime) extends Serializable
case class AggregationCountryKey(partnerId: Int, metric: String, time: DateTime, country: String) extends Serializable
case class AggregationCountryCityKey(partnerId: Int, metric: String, time: DateTime, country: String, city: String) extends Serializable
case class AggregationDomainKey(partnerId: Int, metric: String, time: DateTime, domain: String) extends Serializable
case class AggregationDomainReferrerKey(partnerId: Int, metric: String, time: DateTime, domain: String, referrer: String) extends Serializable
case class AggregationDeviceKey(partnerId: Int, metric: String, time: DateTime, device: Int) extends Serializable
case class AggregationOperatingSystemKey(partnerId: Int, metric: String, time: DateTime, operatingSystem: Int) extends Serializable
case class AggregationBrowserKey(partnerId: Int, metric: String, time: DateTime, browser: Int) extends Serializable
case class AggregationDeviceOperatingSystemKey(partnerId: Int, metric: String, time: DateTime, device: Int, operatingSystem: Int) extends Serializable
case class AggregationOperatingSystemBrowserKey(partnerId: Int, metric: String, time: DateTime, operatingSystem: Int, browser: Int) extends Serializable
case class AggregationCountryOperatingSystemBrowserKey(partnerId: Int, metric: String, time: DateTime, country: String, operatingSystem: Int, browser: Int) extends Serializable
case class AggregationCountryOperatingSystemKey(partnerId: Int, metric: String, time: DateTime, country: String, operatingSystem: Int) extends Serializable
case class AggregationCountryBrowserKey(partnerId: Int, metric: String, time: DateTime, country: String, browser: Int) extends Serializable
case class AggregationApplicationKey(partnerId: Int, metric: String, time: DateTime, application: String) extends Serializable
case class AggregationPlaybackContextKey(partnerId: Int, metric: String, time: DateTime, playbackContext: String) extends Serializable
case class AggregationApplicationPlaybackContextKey(partnerId: Int, metric: String, time: DateTime, application: String, playbackContext: String) extends Serializable
case class AggregationCustomVarKey(partnerId: Int, metric: String, time: DateTime, cv: String) extends Serializable
case class AggregationCustomVar1CustomVar2Key(partnerId: Int, metric: String, time: DateTime, cv1: String, cv2: String) extends Serializable
case class AggregationCustomVar1CustomVar2CustomVar3Key(partnerId: Int, metric: String, time: DateTime, cv1: String, cv2: String, cv3: String) extends Serializable
case class AggregationCategoryKey(partnerId: Int, metric: String, time: DateTime, category: String) extends Serializable


case class AggregationEntryApplicationKey(partnerId: Int, entryId: String, metric: String, time: DateTime, application: String) extends Serializable
case class AggregationEntryApplicationPlaybackContextKey(partnerId: Int, entryId: String, metric: String, time: DateTime, application: String, playbackContext: String) extends Serializable
case class AggregationEntryCountryKey(partnerId: Int, entryId: String, metric: String, time: DateTime, country: String) extends Serializable
case class AggregationEntryCountryCityKey(partnerId: Int, entryId: String, metric: String, time: DateTime, country: String, city: String) extends Serializable
case class AggregationEntryDomainKey(partnerId: Int, entryId: String, metric: String, time: DateTime, domain: String) extends Serializable
case class AggregationEntryDomainReferrerKey(partnerId: Int, entryId: String, metric: String, time: DateTime, domain: String, referrer: String) extends Serializable
case class AggregationEntryDeviceKey(partnerId: Int, entryId: String, metric: String, time: DateTime, device: Int) extends Serializable
case class AggregationEntryOperatingSystemKey(partnerId: Int, entryId: String, metric: String, time: DateTime, operatingSystem: Int) extends Serializable
case class AggregationEntryBrowserKey(partnerId: Int, entryId: String, metric: String, time: DateTime, browser: Int) extends Serializable
case class AggregationEntryDeviceOperatingSystemKey(partnerId: Int, entryId: String, metric: String, time: DateTime, device: Int, operatingSystem: Int) extends Serializable
case class AggregationEntryOperatingSystemBrowserKey(partnerId: Int, entryId: String, metric: String, time: DateTime, operatingSystem: Int, browser: Int) extends Serializable
case class AggregationEntryPlaybackContextKey(partnerId: Int, entryId: String, metric: String, time: DateTime, playbackContext: String) extends Serializable
case class AggregationEntryCustomVarKey(partnerId: Int, entryId: String, cv: String, metric: String, time: DateTime) extends Serializable
case class AggregationEntryCustomVar1CustomVar2Key(partnerId: Int, entryId: String, metric: String, time: DateTime, cv1: String, cv2: String) extends Serializable
case class AggregationEntryCustomVar1CustomVar2CustomVar3Key(partnerId: Int, entryId: String, metric: String, time: DateTime, cv1: String, cv2: String, cv3: String) extends Serializable
case class AggregationEntryCategoryKey(partnerId: Int, entryId: String, metric: String, time: DateTime, category: String) extends Serializable
