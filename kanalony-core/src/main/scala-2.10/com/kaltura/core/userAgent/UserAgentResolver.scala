package com.kaltura.core.userAgent

/**
 * Resolves browser and operating system from user agent string
 */
object UserAgentResolver {

  private val cache = scala.collection.mutable.HashMap.empty[String, UserAgent]
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
    val browserVersion = userAgent.getBrowserVersion
    UserAgent(userAgent.getBrowser.getId,
              if (browserVersion == null) "N/A" else browserVersion.getVersion,
              userAgent.getOperatingSystem.getId)
  }

}
