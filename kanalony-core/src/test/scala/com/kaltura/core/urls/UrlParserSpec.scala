package com.kaltura.core.urls

import org.scalatest.{Matchers, FlatSpec}

class UrlParserSpec extends FlatSpec with Matchers {

  "Complex Url" should "be resolved successfully" in {
    val url = "https://kaltura.atlassian.net:8080/secure/RapidBoard.jspa?rapidView=181&view=planning&selectedIssue=PLAT-3791"
    val urlParts = UrlParser.getUrlParts(url)
    urlParts.domain should be ("kaltura.atlassian.net")
    urlParts.canonicalUrl should be ("kaltura.atlassian.net:8080/secure/RapidBoard.jspa")
    urlParts.originalUrl should be (url)
  }

  "Simple Url" should "be resolved successfully" in {
    val url = "http://kaltura.atlassian.net"
    val urlParts = UrlParser.getUrlParts(url)
    urlParts.domain should be ("kaltura.atlassian.net")
    urlParts.canonicalUrl should be ("kaltura.atlassian.net")
    urlParts.originalUrl should be (url)
  }

}
