package com.kaltura.core.utils

import com.kaltura.client.enums.KalturaSessionType
import com.kaltura.client.{KalturaClient, KalturaConfiguration}
import com.kaltura.core.logging.{BaseLog, MetaLog}
import scala.util.control.NonFatal


class KalturaImpersonationClient(config:KalturaConfiguration) extends KalturaClient(config) {
  override def resetRequest = {
    this.requestConfiguration.remove("partnerId")
  }
}

object KalturaAPI extends MetaLog[BaseLog] {
  logger.info("Initializing Kaltura API")
  val config = new KalturaConfiguration()
  config.setEndpoint(ConfigurationManager.getOrElse("kanalony.core.kaltura_api.endpoint","https://www.kaltura.com/"))
  config.setTimeout(ConfigurationManager.getOrElse("kanalony.core.kaltura_api.timeout","1000").toInt)
  val client = new KalturaImpersonationClient(config)
  setKS
  logger.info("Kaltura API Initialized!")

  def setKS = {
    logger.info("Creating Kaltura Session...")
    try {
      val ks = client.getSessionService.start(ConfigurationManager.getOrElse("kanalony.events_enrichment.admin_partner_secret",""), "batchUser", KalturaSessionType.ADMIN, -1, 86400, "disableentitlement")
      client.setKs(ks)
      logger.info("KS was updated on API client")
    } catch {
      case NonFatal(e) => logger.error("Error creating KS!", e)
    }
  }
}