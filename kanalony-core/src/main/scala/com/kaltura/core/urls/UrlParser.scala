package com.kaltura.core.urls

import scala.collection.mutable.ArrayBuffer

/**
 * Parses URLs
 */
object UrlParser {

  case class QueryStringKeyValuePair(key: String, value: String)

  def parseUrl(url: String) : Array[QueryStringKeyValuePair] = {
    url.split("\\?", 2) match {
      case Array(_, qs: String) => parseQueryString(decodeUrl(qs))
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
    try {
      val uri = new java.net.URI(url.replaceAll(" ","%20"))
      UrlParts(uri.getHost, uri.getAuthority + uri.getPath, url)
    } catch {
      case e: Exception => UrlParts("N/A","N/A","N/A")
    }
  }

  def decodeUrl(url: String) = java.net.URLDecoder.decode(url, "UTF-8")
}
