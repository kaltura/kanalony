package com.kaltura.model.dao

import com.kaltura.core.logging.{BaseLog, MetaLog}
import com.kaltura.model.entities.Partner
/**
 * Created by ofirk on 18/01/2016.
 */
object PartnerDAO extends DAOBase[Partner, Int] with MetaLog[BaseLog] {

  def getById(id: Int): Option[Partner] = {
    withPartnerImpersonation(id) {
      val partner = kalturaAPI.getPartnerService.get(id)
      Some(Partner(id, Some(partner.adminSecret)))
    }
  }
}