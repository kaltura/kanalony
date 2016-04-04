package load

/**
 * Created by elad.benedict on 3/30/2016.
 */

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class EventParsingLoadTest extends Simulation {

  val webServerHostname = "il-bigdata-4.dev.kaltura.com"
  val queryUrl = s"http://${webServerHostname}/api_v3/index.php?service=stats&action=collect&kalsig=1ebb5aea0c5f253fd8c578febe6a752f&clientTag=kdp%3Av3%2E9%2E2&event%3AobjectType=KalturaStatsEvent&event%3AsessionId=C90BFCFC%2D2EBF%2D5893%2D892D%2D2121162F414A&apiVersion=3%2E1%2E5&event%3AisFirstInSession=false&event%3Aduration=194&ignoreNull=1&event%3AeventType=13&event%3Aseek=false&event%3Areferrer=http%253A%2F%2Fabc%2Ego%2Ecom%2Fshows%2Fthe%2Dbachelorette%2Fvideo%2Fmost%2Drecent%2FVDKA0%5Flawz79v7&event%3AentryId=0%5Fn5nbuy6i&ks=djJ8MTA5MXwjFUzIifsgEbq-qlc8DhLFAVhV-xecSlzm_smRiTZabayc9bKahRKZhSjuPhDTFc-mHKD1uUd6PhcowDdW6TtDw1ozzDuqD2GlWH-Y4XiQTQ%3D%3D&event%3AeventTimestamp=1435075182567&event%3AuiconfId=23521211&event%3ApartnerId=1091&event%3AcurrentPoint=18&event%3AclientVer=3%2E0%3Av3%2E9%2E2-13"

  val httpConf = http.doNotTrackHeader("1")

  val scn = scenario("EventParsingLoadTest").exec(
    http("BasicQuery")
      .get(queryUrl)
      //.check(bodyString.saveAs("responseData"))
  ).exec(session => {
    //val ResBody = session.get("responseData").asOption[String]
    //println(maybeId.getOrElse("ERROR GETTING RESPONSE DATA"))
    session
  })

    setUp(scn.inject(
      constantUsersPerSec(100) during (30 seconds),
      constantUsersPerSec(500) during (30 seconds),
      constantUsersPerSec(1000) during (30 seconds),
      constantUsersPerSec(1500) during (30 seconds),
      constantUsersPerSec(2000) during (30 seconds),
      constantUsersPerSec(3000) during (30 seconds),
      constantUsersPerSec(4000) during (30 seconds),
      constantUsersPerSec(4500) during (30 seconds),
      constantUsersPerSec(5500) during (30 seconds),
      constantUsersPerSec(6500) during (30 seconds)
    ).protocols(httpConf))
}