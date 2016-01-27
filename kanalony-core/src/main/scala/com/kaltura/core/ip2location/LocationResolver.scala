package com.kaltura.core.ip2location

import java.io.Serializable

import com.kaltura.core.logging.{BaseLog, MetaLog}
import com.kaltura.core.utils.ConfigurationManager

/**
 * Created by ofirk on 06/10/2015.
 */
class LocationResolver extends Serializable with MetaLog[BaseLog] {
  val reader = new SerializableIP2LocationReader(ConfigurationManager.get("kanalony.core.ip2location.data_filepath"))

  def parse(ipCode: String): Location = {

    try {
      val ipRecord: Ip2LocationRecord = reader.getAll(ipCode)

      if (ipRecord.getCountryLong.length > 35 || ipRecord.getCity.length > 35)
        logger.warn("ip: '" + ipCode + "' with Long Country: " + ipRecord.getCountryLong + " Long City: " + ipRecord.getCity)

      new Location(repair(ipRecord.getCountryLong), repair(ipRecord.getCity))
    }
    catch {
      case e: Exception => {
        //println("Failed to parse IP " + ipCode) // maybe add exception description
        logger.warn("Failed to parse IP '" + ipCode + "' exp: " + e.printStackTrace()) // maybe add exception description
      }
      new Location
    }

  }

  private def repair(locationValue: String) = if (locationValue == "-") "N/A" else locationValue

  def close = reader.close()


}
