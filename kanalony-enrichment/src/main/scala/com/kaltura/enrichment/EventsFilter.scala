package com.kaltura.enrichment

import java.nio.file.{Files, Paths}

import com.kaltura.core.utils.ConfigurationManager
import com.kaltura.model.events.RawPlayerEvent
import com.kaltura.model.cache.PartnerCache
import com.kaltura.model.entities.Partner

/**
 * Created by ofirk on 29/05/2016.
 */
class EventsFilter {
  var includeAll = true
  var enabledPartners: Set[Int] = _
  val enabledPartnersFilePath = ConfigurationManager.get("kanalony.events_enrichment.enabled_partners_file_path")
  if(Files.exists(Paths.get(enabledPartnersFilePath))) {
    val source = scala.io.Source.fromFile(enabledPartnersFilePath)
    enabledPartners = try source.getLines.toList.map(_.toInt).toSet finally source.close()
    includeAll = false
  }
  val enabledPackages = ConfigurationManager.getOrElse("kanalony.events_enrichment.enabled_partners_package_ids", "*").split(",")

  def include(event: RawPlayerEvent): Boolean = includeAll || (event.partnerId.isDefined && enabledPackages.contains(PartnerCache.getById(event.partnerId.get).packageId.getOrElse(-1))) || (event.partnerId.isDefined && enabledPartners.contains(event.partnerId.get))

}

