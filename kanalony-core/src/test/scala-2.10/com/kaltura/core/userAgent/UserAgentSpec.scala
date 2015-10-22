package com.kaltura.core.userAgent

import org.scalatest.{BeforeAndAfterAll, Matchers, FlatSpec}
import eu.bitwalker.useragentutils._
import java.io.InputStream

/**
 * Created by ofirk on 20/10/2015.
 */
class UserAgentSpec extends FlatSpec with Matchers with BeforeAndAfterAll  {

  "Chrome 41 on Windwos 7" should "be resolved successfully" in {
    val ua:String = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36"
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browserId should be (Browser.CHROME41.getId)
    userAgent.browserVersion should be ("41.0.2272.89")
    userAgent.operatingSystemId should be (OperatingSystem.WINDOWS_7.getId)
  }

  "Firefox 3 on OS X" should "be resolved successfully" in {
    val ua:String = "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10.5; en-US; rv:1.9.0.3) Gecko/2008092414 Firefox/3.0.3"
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browserId should be (Browser.FIREFOX3.getId)
    userAgent.browserVersion should be ("3.0.3")
    userAgent.operatingSystemId should be (OperatingSystem.MAC_OS_X.getId)
  }

  "Curl" should "be resolved successfully" in {
    val ua:String = "curl/7.19.5"
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browserId should be (Browser.DOWNLOAD.getId)
    userAgent.browserVersion should be ("N/A")
    userAgent.operatingSystemId should be (OperatingSystem.UNKNOWN.getId)
  }

  "Garbage" should "be resolved successfully" in {
    val ua:String = "sdiajfoiadsjfoi"
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browserId should be (Browser.UNKNOWN.getId)
    userAgent.browserVersion should be ("N/A")
    userAgent.operatingSystemId should be (OperatingSystem.UNKNOWN.getId)
  }

  "Empty" should "be resolved successfully" in {
    val ua:String = ""
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browserId should be (Browser.UNKNOWN.getId)
    userAgent.browserVersion should be ("N/A")
    userAgent.operatingSystemId should be (OperatingSystem.UNKNOWN.getId)
  }

  "Null" should "be resolved successfully" in {
    val ua:String = null
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browserId should be (Browser.UNKNOWN.getId)
    userAgent.browserVersion should be ("N/A")
    userAgent.operatingSystemId should be (OperatingSystem.UNKNOWN.getId)
  }

}
