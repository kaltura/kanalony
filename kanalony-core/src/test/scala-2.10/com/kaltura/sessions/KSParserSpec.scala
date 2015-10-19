package com.kaltura.sessions

import com.kaltura.core.sessions._
import org.scalatest._

import scala.collection.immutable.Map

class KSParserSpec extends FlatSpec with Matchers with BeforeAndAfterAll  {

  class MockKSParser extends KSParserBase {
    val partnerSecrets = Map[Int,String](1091 -> "05fe84786de70742f007178c9a533ab8")
    override def getPartnerSecret(partnerId: Int): String = partnerSecrets(partnerId)
  }

  val ksParser = new MockKSParser

  "a valid KS V2" should "be resolved" in {
    val ks = "djJ8MTA5MXwjFUzIifsgEbq-qlc8DhLFAVhV-xecSlzm_smRiTZabayc9bKahRKZhSjuPhDTFc-mHKD1uUd6PhcowDdW6TtDw1ozzDuqD2GlWH-Y4XiQTQ=="
    val resolvedData = ksParser.parse(ks)
    resolvedData.isEmpty should be (false)
    resolvedData.get.partnerId should be (1091)
    resolvedData.get.userId should be ("user@kaltura.com")
  }

  "a valid KS V1" should "be resolved" in {
    val ks = "YzUzZmMxNGM3Y2NhYjk3Mzc5MTJlYTEwMWM5NWY5M2YxYThlNWNmOXwxMDkxOzEwOTE7MTQ0NDkxNDc4OTsyOzE0NDQ4MjgzODkuMDYxOTt1c2VyQGthbHR1cmEuY29tOzs"
    val resolvedData = ksParser.parse(ks)
    resolvedData.isEmpty should be (false)
    resolvedData.get.partnerId should be (1091)
    resolvedData.get.userId should be ("user@kaltura.com")
  }

  "invalid KS" should "be resolved to empty data" in {
    val ks = "invalid-ks"
    val resolvedData = ksParser.parse(ks)
    resolvedData.isEmpty should be (true)
  }

  "invalid KS V1" should "be resolved to partial data" in {
    val ks = "MTIzNHxpbnZhbGlkX2tz" // <== new String(Base64.getEncoder.encode("1234|invalid_ks".getBytes))
    val resolvedData = ksParser.parse(ks)
    resolvedData.isEmpty should be (true)
  }

  "partial KS V1" should "be resolved to partial data" in {
    val ks = "MTIzNHwxMDkxOzEwOTE7aW52YWxpZF9rcw==" // new String(Base64.getEncoder.encode("1234|1091;1091;invalid_ks".getBytes))
    val resolvedData = ksParser.parse(ks)
    resolvedData.isEmpty should be (false)
    resolvedData.get.partnerId should be (1091)
    resolvedData.get.userId should be ("Unknown")
  }

  "partial KS V2" should "be resolved to partial data" in {
    val ks = "djJ8MTA5MXxpbnZhbGlkX2tz" // <== new String(Base64.getEncoder.encode("v2|1091|invalid_ks".getBytes))
    val resolvedData = ksParser.parse(ks)
    resolvedData.isEmpty should be (false)
    resolvedData.get.partnerId should be (1091)
    resolvedData.get.userId should be ("Unknown")
  }

  "an empty KS" should "be resolved to empty data" in {
    val resolvedData = ksParser.parse("")
    resolvedData.isEmpty should be (true)
  }

  "a null KS" should "be resolved to empty data" in {
    val resolvedData = ksParser.parse(null)
    resolvedData.isEmpty should be (true)
  }

}