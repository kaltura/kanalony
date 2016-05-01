package com.kaltura.core.utils

import com.kaltura.client.enums.KalturaSessionType
import com.kaltura.client.{KalturaClient, KalturaConfiguration}
import com.kaltura.core.logging.{BaseLog, MetaLog}
import scala.util.control.NonFatal


class KalturaImpersonationClient(config:KalturaConfiguration) extends KalturaClient(config) {

  def removePartnerId() = {
    this.requestConfiguration.remove("partnerId")
  }
}

object KalturaAPIFactory extends MetaLog[BaseLog] {
  logger.info("Initializing Kaltura API")
  val config = new KalturaConfiguration()
  config.setEndpoint(ConfigurationManager.getOrElse("kanalony.core.kaltura_api.endpoint","https://www.kaltura.com/"))
  config.setTimeout(ConfigurationManager.getOrElse("kanalony.core.kaltura_api.timeout","2000").toInt)
  val internalClient = new KalturaClient(config)
  var ks: String = _
  resetKS
  logger.info("Kaltura API Initialized!")

  def getClient: KalturaImpersonationClient = {
    val client = new KalturaImpersonationClient(config)
    client.setKs(ks)
    client
  }

  def resetKS:String = {
    synchronized {
      try {
        ks = internalClient.getSessionService.start(ConfigurationManager.getOrElse("kanalony.events_enrichment.admin_partner_secret", ""), "batchUser", KalturaSessionType.ADMIN, -1, 86400, "disableentitlement")
      } catch {
        case NonFatal(e) => logger.error("Error creating KS!", e)
      }
    }
    ks
  }


}