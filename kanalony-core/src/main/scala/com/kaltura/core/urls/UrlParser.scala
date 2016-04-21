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
      UrlParts( defaultIfEmpty(uri.getHost),
                defaultIfEmpty(uri.getAuthority,uri.getPath),
                defaultIfEmpty(url))
    } catch {
      case e: Exception => println(s"Error parsing url: $url, error: ${e.toString}"); UrlParts("N/A","N/A","N/A")
    }
  }

  def decodeUrl(url: String) = java.net.URLDecoder.decode(url, "UTF-8")

  def defaultIfEmpty(urlPart:String) = if(urlPart == null || urlPart.isEmpty) "N/A" else urlPart
  def defaultIfEmpty(urlPart1:String, urlPart2:String) = if(urlPart1 == null || urlPart1.isEmpty) "N/A" else if (urlPart2 == null) urlPart1 else urlPart1 + urlPart2
}
