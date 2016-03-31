package kanalony.tests.load

/**
 * Created by elad.benedict on 3/30/2016.
 */

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class DataApiLoadTest extends Simulation {

  val webServerHostname = "il-bigdata-4.dev.kaltura.com"
  val queryUrl = s"http://${webServerHostname}:9000/query"
  val queryData = """{"from":"1","to":"1487265988000","filters":[{"dimension":"partner","values":["1"]},{"dimension":"entry","values":["1_Entry_1","1_Entry_5"]}],"dimensions":["day","partner","country"],"metrics":["play"]}"""

  val httpConf = http
    .contentTypeHeader("application/json")
    .doNotTrackHeader("1")

  val scn = scenario("DataApiLoadTest").exec(
    http("BasicQuery")
      .post(queryUrl)
      .body(StringBody(queryData))
      //.check(bodyString.saveAs("responseData"))
  ).exec(session => {
    //val ResBody = session.get("responseData").asOption[String]
    //println(maybeId.getOrElse("ERROR GETTING RESPONSE DATA"))
    session
  })

  setUp(
    scn.inject(
      constantUsersPerSec(100) during (30 seconds),
      constantUsersPerSec(200) during (30 seconds),
      constantUsersPerSec(300) during (30 seconds),
      constantUsersPerSec(400) during (30 seconds),
      constantUsersPerSec(500) during (30 seconds),
      constantUsersPerSec(600) during (30 seconds),
      constantUsersPerSec(700) during (30 seconds),
      constantUsersPerSec(800) during (30 seconds),
      constantUsersPerSec(900) during (30 seconds),
      constantUsersPerSec(1000) during (30 seconds)
    ).protocols(httpConf))
}