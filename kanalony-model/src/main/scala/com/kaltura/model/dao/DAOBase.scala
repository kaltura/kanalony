package com.kaltura.model.dao

import com.kaltura.client.KalturaApiException
import com.kaltura.core.utils.{KalturaImpersonationClient, KalturaAPIFactory}

trait DAOBase[T, IDType]{

  def withPartnerImpersonation[A](partnerId:Int, action:String, naVal:A)(execution: (KalturaImpersonationClient) => A): A = {
    val kalturaAPI = KalturaAPIFactory.getClient
    kalturaAPI.setPartnerId(partnerId)
    try {
      execution(kalturaAPI)
    } catch {
      case kae: KalturaApiException => {
        if (kae.code == "INVALID_KS") {
          kalturaAPI.setKs(KalturaAPIFactory.resetKS)
          execution(kalturaAPI)
        }
        else System.err.println(s"Unable to $action for partner $partnerId):");kae.printStackTrace();naVal
      }
      case e: Exception => System.err.println(s"Unable to $action for partner $partnerId):");e.printStackTrace();naVal
    } finally {
      kalturaAPI.removePartnerId()
    }
  }

}