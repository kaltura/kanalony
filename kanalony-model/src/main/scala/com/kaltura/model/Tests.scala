package com.kaltura.model

import com.kaltura.model.dao.PartnerDAO

/**
 * Created by ofirk on 18/01/2016.
 */
object Tests {
  def getPartner = {
    println(PartnerDAO.findById(1234))
  }
}
