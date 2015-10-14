package com.kaltura.core.sessions

trait IPartnerSecretStore {
  /**
   * returns the partner secret corresponding to the partner Id provided
   * @param partnerId
   * @return
   */
  def getPartnerSecret(partnerId: Int): String
}
