package com.kaltura.core.logging

import org.slf4j.LoggerFactory

trait MetaLog[BaseLog] {
  val logger = LoggerFactory.getLogger(getClass)
}

trait BaseLog {
  def metaLog: MetaLog[BaseLog]
  def logger = metaLog.logger
}

