package com.kaltura.model.dao

import com.kaltura.client.KalturaApiException
import com.kaltura.core.logging.{BaseLog, MetaLog}
import com.kaltura.core.utils.KalturaAPI
import com.kaltura.model.entities.Partner
import scalikejdbc._
/**
 * Created by ofirk on 18/01/2016.
 */
object PartnerDAO extends DAOBase[Partner, Int] with MetaLog[BaseLog] {

  def getById(id: Int): Option[Partner] = {
    withPartnerImpersonation(id) { kalturaAPI =>
      val partner = kalturaAPI.getPartnerService.get(id)
      Some(Partner(id, Some(partner.secret)))
    }
  }
}