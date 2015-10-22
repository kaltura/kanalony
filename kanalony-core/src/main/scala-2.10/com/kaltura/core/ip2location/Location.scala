package com.kaltura.core.ip2location

case class Location(country: String = "N/A", city: String = "N/A") {

  override def toString(): String = {
    s"Country: $country, City: $city"
  }
}
