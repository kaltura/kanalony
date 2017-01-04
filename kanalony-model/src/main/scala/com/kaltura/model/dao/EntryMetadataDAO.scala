package com.kaltura.model.dao

import com.kaltura.core.utils.KalturaImpersonationClient
import com.kaltura.model.entities.EntryMetadata

/**
 * Created by orlylampert on 12/27/16.
 */
object EntryMetadataDAO extends DAOBase[EntryMetadata, String] {
  def getById(partnerId: Int, entryId:String): Option[EntryMetadata] = {
    withPartnerImpersonation(partnerId, s"fetch entry data for entry $entryId", Some(EntryMetadata(entryId, "Missing Entry Name"))) { kalturaAPI =>
      val entryName = getEntryName(kalturaAPI, entryId)
      Some(EntryMetadata(entryId, entryName))
    }
  }

  def getEntryName(kalturaAPI: KalturaImpersonationClient ,entryId: String): String = {
    val kEntry = kalturaAPI.getBaseEntryService.get(entryId)
    kEntry.name
  }

}