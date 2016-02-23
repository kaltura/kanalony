package com.kaltura.model.dao

import com.kaltura.client.KalturaApiException
import com.kaltura.core.utils.KalturaAPI

trait DAOBase[T, IDType]{
  val kalturaAPI = KalturaAPI.client

  def withPartnerImpersonation[A](partnerId:Int)(execution: () => A): A = {
    try {
      kalturaAPI.setPartnerId(partnerId)
      execution()
    } catch {
      case kae: KalturaApiException => {
        if (kae.code == "INVALID_KS") {
          KalturaAPI.setKS
          execution()
        }
        else throw kae
      }
    } finally {
      kalturaAPI.resetRequest
    }
  }

}