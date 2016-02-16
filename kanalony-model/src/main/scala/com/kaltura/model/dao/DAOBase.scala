package com.kaltura.model.dao

import com.kaltura.client.{KalturaApiException, KalturaClient}
import com.kaltura.core.utils.KalturaAPI

trait DAOBase[T, IDType]{
  val kalturaAPI = KalturaAPI.client

  def withPartnerImpersonation[A](partnerId:Int)(execution: KalturaClient => A): A = {
    try {
      kalturaAPI.setPartnerId(partnerId)
      execution(kalturaAPI)
    } catch {
      case kae: KalturaApiException => {
        if (kae.code == "INVALID_KS") {
          KalturaAPI.setKS
          execution(kalturaAPI)
        }
        else throw kae
      }
    } finally {
      kalturaAPI.resetRequest
    }
  }

}