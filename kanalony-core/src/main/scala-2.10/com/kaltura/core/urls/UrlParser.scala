package com.kaltura.core.urls

import scala.collection.mutable.ArrayBuffer

/**
 * Parses URLs
 */
object UrlParser {

  case class QueryStringKeyValuePair(key: String, value: String)

  def parseUrl(url: String) : Array[QueryStringKeyValuePair] = {
    url.split("\\?", 2) match {
      case Array(_, qs: String) => parseQueryString(qs)
      case _ => null
    }
  }

  def parseQueryString(queryString: String) : Array[QueryStringKeyValuePair] = {
    val pairs = new ArrayBuffer[QueryStringKeyValuePair]
    val pairSplits = queryString.split("&")
    for (pairSplit <- pairSplits) {
      val keyValue = pairSplit.split("=", 2)
      pairs += QueryStringKeyValuePair(decodeUrl(keyValue(0)), if(keyValue.length >1) decodeUrl(keyValue(1)) else null)
    }
    pairs.toArray
  }

  def getUrlParts(url: String) : UrlParts = {
    val uri = new java.net.URI(url)
    UrlParts(uri.getHost, uri.getAuthority + uri.getPath, url)
  }

  def decodeUrl(implicit url: String) = java.net.URLDecoder.decode(url, "UTF-8")
}
