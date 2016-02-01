package com.kaltura.core.userAgent

import org.scalatest.{BeforeAndAfterAll, Matchers, FlatSpec}
import com.kaltura.core.userAgent.enums.{Browser,Device,OperatingSystem}

/**
 * Created by ofirk on 20/10/2015.
 */
class UserAgentSpec extends FlatSpec with Matchers with BeforeAndAfterAll  {

  "Chrome 41 on Windwos 7" should "be resolved successfully" in {
    val ua:String = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36"
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browser should be (Browser.CHROME41)
    userAgent.device should be (Device.COMPUTER)
    userAgent.operatingSystem should be (OperatingSystem.WINDOWS_7)
  }

  "Firefox 3 on OS X" should "be resolved successfully" in {
    val ua:String = "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10.5; en-US; rv:1.9.0.3) Gecko/2008092414 Firefox/3.0.3"
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browser should be (Browser.FIREFOX3)
    userAgent.device should be (Device.COMPUTER)
    userAgent.operatingSystem should be (OperatingSystem.MAC_OS_X)
  }

  "Curl" should "be resolved successfully" in {
    val ua:String = "curl/7.19.5"
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browser should be (Browser.DOWNLOAD)
    userAgent.device should be (Device.UNKNOWN)
    userAgent.operatingSystem should be (OperatingSystem.UNKNOWN)
  }

  "Garbage" should "be resolved successfully" in {
    val ua:String = "sdiajfoiadsjfoi"
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browser should be (Browser.UNKNOWN)
    userAgent.device should be (Device.UNKNOWN)
    userAgent.operatingSystem should be (OperatingSystem.UNKNOWN)
  }

  "Empty" should "be resolved successfully" in {
    val ua:String = ""
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browser should be (Browser.UNKNOWN)
    userAgent.device should be (Device.UNKNOWN)
    userAgent.operatingSystem should be (OperatingSystem.UNKNOWN)
  }

  "Null" should "be resolved successfully" in {
    val ua:String = null
    val userAgent = UserAgentResolver.resolve(ua)
    userAgent.browser should be (Browser.UNKNOWN)
    userAgent.device should be (Device.UNKNOWN)
    userAgent.operatingSystem should be (OperatingSystem.UNKNOWN)
  }

}
