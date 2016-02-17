package com.kaltura.core.sessions

import java.security.MessageDigest
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.{IvParameterSpec, SecretKeySpec}
import com.kaltura.core.utils.CollectionOps._
import com.kaltura.core.urls.UrlParser

trait KSParserBase extends IPartnerSecretStore {

  case class KSData(partnerId: Int, userId:String = "Unknown")

  val IV = new IvParameterSpec(Array.fill[Byte](16)(0x0))
  val HASH_SIZE = 20
  val SHA1_SIZE = 16
  val KS_USER_KEY = "_u"

  /**
   * Parses all types of KS (Kaltura Session) and returns an instance of KSData containing the user and partner IDs
   * @param ks
   * @return returns an instance of KSData containing the user and partner IDs
   */
  def parse(ks:String): Option[KSData] = {
    if (isEmpty(ks))
      None
    else {
      val ksBytes = decode(replaceSpecialChars(ks))
      val ksString = new String(ksBytes)
      if (ksString.startsWith("v2|"))
        parseV2(ksString, ksBytes)
      else
        parseV1(ksString)
    }
  }

  /**
   * Parses a V1 KS which is not encrypted and returns an instance of KSData containing the user and partner IDs
   * @param ks
   * @return an instance of KSData containing the user and partner IDs
   */
  def parseV1(ks:String): Option[KSData] = {
    val ksParts = ks.split('|')
    if (ksParts.length == 2) {
      ksParts(1).split(';') match {
        case Array(partnerId:String,_,_,_,_,userId:String,_*) => Some(KSData(partnerId.toInt, userId))
        case Array(partnerId:String,_,_*) => Some(KSData(partnerId.toInt))
        case _ => None
      }
    }
    else
      None
  }

  /**
   * Parses a V2 KS which is encrypted and returns an instance of KSData containing the user and partner IDs
   * @param ksStr
   * @param ksBytes
   * @return
   */
  def parseV2(ksStr: String, ksBytes:Array[Byte]): Option[KSData] = {
    val ksData = ksBytes.slice(ksBytes.indexOf('|'.toByte, 3)+1 , ksBytes.length)
    val partnerId = getPartnerId(ksStr)
    if (partnerId.isDefined) parseV2(ksData, partnerId.get) else None
  }

  /**
   * Parses a V2 KS which is encrypted and returns an instance of KSData containing the user and partner IDs
   * @param encData
   * @param partnerId
   * @return
   */
  def parseV2(encData:Array[Byte], partnerId: Int): Option[KSData] = {
    parseV2(encData, partnerId, getPartnerSecret(partnerId))
  }

  /**
   * Parses a V2 KS which is encrypted and returns an instance of KSData containing the user and partner IDs
   * @param encData
   * @param partnerId
   * @param partnerSecret
   * @return
   */
  def parseV2(encData:Array[Byte], partnerId: Int, partnerSecret: String): Option[KSData] = {
    val decryptedKS = decryptKS(encData, partnerSecret)
    val ksPairs = UrlParser.parseQueryString(decryptedKS)
    val userPair = ksPairs.find(pair => pair.key.equals(KS_USER_KEY))
    if (userPair.isDefined) Some(KSData(partnerId, userPair.get.value)) else Some(KSData(partnerId))
  }

  private def decode(ks:String): Array[Byte] = {
    try {
      Base64.getDecoder.decode(ks)
    }
    catch {
      case _:Throwable => Array.emptyByteArray
    }
  }

  private def replaceSpecialChars(ks: String): String = {
    ks.map {
      case '-' => '+'
      case '_' => '/'
      case c => c
    }
  }

  private def decryptKS(encData:Array[Byte], secret: String): String = {
    if (isEmpty(encData) || encData.length % 16 > 0) {
      return EMPTY_STRING
    }
    val decryptedData = aesDecrypt(getDecryptionKey(secret), encData)
    new String(decryptedData.drop(HASH_SIZE + SHA1_SIZE), "UTF-8").trim
  }

  private def getPartnerId(ksStr: String): Option[Int] = {
    val ksSections = ksStr.split('|')
    if (ksSections.length >= 3) Some(ksSections(1).toInt) else None
  }

  private def aesDecrypt(key:SecretKeySpec, message:Array[Byte]): Array[Byte] = {
    val cipher = Cipher.getInstance("AES/CBC/NoPadding");
    cipher.init(Cipher.DECRYPT_MODE, key, IV)
    cipher.doFinal(message)
  }

  private def getDecryptionKey(partnerSecret: String): SecretKeySpec = {
    val keyBytes = partnerSecret.getBytes
    val sha: MessageDigest = MessageDigest.getInstance("SHA-1");
    new SecretKeySpec(sha.digest(keyBytes).take(SHA1_SIZE), "AES")
  }

}
