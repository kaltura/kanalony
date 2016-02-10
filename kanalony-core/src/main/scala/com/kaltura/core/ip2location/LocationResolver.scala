package com.kaltura.core.ip2location

import java.io.Serializable

import com.kaltura.core.logging.{BaseLog, MetaLog}
import com.kaltura.core.utils.ConfigurationManager
import org.apache.commons.validator.Validator
import org.apache.commons.validator.routines.InetAddressValidator

/**
 * Created by ofirk on 06/10/2015.
 */
class LocationResolver extends Serializable with MetaLog[BaseLog] {
  val reader = new SerializableIP2LocationReader(ConfigurationManager.get("kanalony.core.ip2location.data_filepath"))
  val inetAddressValidator = InetAddressValidator.getInstance()

  def parse(ipAddr: String): Location = {
    try {
      val ipRecord: Ip2LocationRecord = reader.getAll(ipAddr)
      Location(repair(ipRecord.getCountryLong), repair(ipRecord.getRegion), repair(ipRecord.getCity))
    }
    catch {
      case e: Exception => {
        logger.debug("Failed to parse IP '" + ipAddr + "' exp: " + e.printStackTrace()) // maybe add exception description
      }
      Location()
    }
  }

  def parseWithProxy(remoteAddr: String, proxyAddr: String): Location = {
    if (inetAddressValidator.isValid(proxyAddr))
      parse(proxyAddr)
    else if (inetAddressValidator.isValid(remoteAddr))
      parse(remoteAddr)
    else
      Location()
  }

  private def repair(locationValue: String) = if (locationValue == "-") "N/A" else locationValue

  def close() = reader.close()

}
