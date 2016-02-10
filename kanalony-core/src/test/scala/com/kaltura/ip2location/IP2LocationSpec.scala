package com.kaltura.ip2location

import com.kaltura.core.ip2location.LocationResolver
import org.scalatest._

class IP2LocationSpec extends FlatSpec with Matchers with BeforeAndAfterAll  {

  val locationResolver = new LocationResolver()

  "Google's DNS server IP" should "be in Mountain View, USA" in {
    val location = locationResolver.parse("8.8.8.8")
    location.country should equal ("United States")
    location.region should equal ("California")
    location.city should equal ("Mountain View")
  }


  "A local IP" should "not be resolved successfully" in {
    val location = locationResolver.parse("127.0.0.1")
    location.country should equal ("N/A")
    location.region should equal ("N/A")
    location.city should equal ("N/A")
  }

  override def afterAll = locationResolver.close

}
