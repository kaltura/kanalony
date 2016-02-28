package com.kaltura.core.userAgent

import com.kaltura.core.userAgent.enums.{Device, OperatingSystem, Browser}


/**
 * Resolves browser and operating system from user agent string
 */
object UserAgentResolver {

  private val cache = scala.collection.mutable.HashMap.empty[String, UserAgent] // TODO - use Guava cache
  def resolve(ua:String): UserAgent = {
    cache.getOrElseUpdate(ua, parse(ua))
  }
  /**
   * Returns and UserAgent class describing the browser and operating system corresponding to the provided user-agent string
   * @param ua
   * @return
   */
  private def parse(ua:String): UserAgent = {
    val userAgent = eu.bitwalker.useragentutils.UserAgent.parseUserAgentString(ua)
    val operatingSystem = userAgent.getOperatingSystem
    UserAgent(Browser.withName(userAgent.getBrowser.getName),
              OperatingSystem.withName(operatingSystem.getName),
              Device.withName(operatingSystem.getDeviceType.getName))
  }

}
