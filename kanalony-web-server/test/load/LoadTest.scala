/**
 * Created by elad.benedict on 3/30/2016.
 */

package computerdatabase // 1

import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation123 extends Simulation { // 3

  val httpConf = http // 4
    .contentTypeHeader("application/json") // 6
    .doNotTrackHeader("1")

  val scn = scenario("BasicSimulation").exec( // 7
    http("request_1")  // 8
    .post("http://il-bigdata-4.dev.kaltura.com:9000/query")
    .body(StringBody("""{"from":"1","to":"1487265988000","filters":[{"dimension":"partner","values":["1"]},{"dimension":"entry","values":["1_Entry_1","1_Entry_5"]}],"dimensions":["day","partner","country"],"metrics":["play"]}"""))
    .check(bodyString.saveAs("myresponse"))
  ).exec(session => {
    val maybeId = session.get("myresponse").asOption[String]
    println(maybeId.getOrElse("ERROR GETTING RESPONSE DATA"))
    session
  })


  setUp( // 11
    scn.inject(
      atOnceUsers(1),
      nothingFor(1 seconds),
      constantUsersPerSec(500) during(15 seconds) // 4) // 12
  ).protocols(httpConf)) // 13
}
