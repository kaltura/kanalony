package com.kaltura.core.userAgent

import java.util.concurrent.TimeUnit

import com.google.common.cache.{CacheLoader, CacheBuilder}
import com.kaltura.core.userAgent.enums.{Device, OperatingSystem, Browser}


/**
 * Resolves browser and operating system from user agent string
 */
object UserAgentResolver extends Serializable {

  private val maxCacheSize = 100000
  private val expireAfterAccess: (Long, TimeUnit) = (1L, TimeUnit.HOURS)
  private val cache = buildCache

  /**
   * Returns and UserAgent class describing the browser and operating system corresponding to the provided user-agent string
   * @param ua
   * @return
   */
  def resolve(ua:String): UserAgent = {
    val userAgent = cache.get(ua)
    val operatingSystem = userAgent.getOperatingSystem

    UserAgent(Browser.values.find(_.toString == userAgent.getBrowser.getName).getOrElse(Browser.UNKNOWN),
              OperatingSystem.values.find(_.toString == operatingSystem.getName).getOrElse(OperatingSystem.UNKNOWN),
              Device.values.find(_.toString == operatingSystem.getDeviceType.getName).getOrElse(Device.UNKNOWN))
  }

  private def buildCache = {
    CacheBuilder.newBuilder()
      .maximumSize(maxCacheSize)
      .expireAfterAccess(expireAfterAccess._1, expireAfterAccess._2)
      .build(
        new CacheLoader[String ,eu.bitwalker.useragentutils.UserAgent]() {
          override def load(ua: String) = eu.bitwalker.useragentutils.UserAgent.parseUserAgentString(ua)
        }
      )
  }
}
