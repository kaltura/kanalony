package com.kaltura.core.utils

import java.io.{FileInputStream, InputStream}
import java.util.Properties

import com.kaltura.core.logging.{MetaLog, BaseLog}

import scala.collection.JavaConverters._
import scala.collection.mutable.Map
import scala.util.Try

/**
 * A utility for reading configuration properties
 * Author: @kerkero
 */
object ConfigurationManager extends Serializable with MetaLog[BaseLog]{

  protected var configData: Map[String,String] = Map()

  loadConfiguration(configFilePath);

  protected def configFilePath: String = {
    var confPath: String = "/opt/kaltura/lib"
    if (System.getenv.containsKey("KALTURA_CONF_PATH")) {
      confPath = System.getenv("KALTURA_CONF_PATH")
    }
    confPath + "/config.properties"
  }

  /*
   * Load configuration properties from configuration files
   */
  def loadConfiguration(configFilePath: String): Unit = {
    val props: Properties = new Properties
    try {
      //logger.info(s"Loading configuration from path: $configFilePath")
      val file: InputStream = new FileInputStream(configFilePath)
      props.load(file)
      file.close()
    }
    catch {
      case _ : Throwable => throw new Exception(s"Unable to load configuration file [path: $configFilePath]")
    }

    loadConfiguration(props.asScala);
  }

  /*
   * Load configuration properties from a {key, value} map, might override configuration that's already set
   */
  def loadConfiguration(configProperties: Map[String,String]): Unit = {
    configProperties.foreach((pair: (String, String)) => configData(pair._1) = pair._2)
  }

  /*
 * Gets a configuration value by key
 */
  def get(configKey: String): String = {
    try{
      configData(configKey)
    }
    catch {
      case _: Exception => throw new Exception(s"Unable to find a configuration value for [key: $configKey]")
    }
  }

  /*
   * Gets a configuration value by key
   */
  def getOrElse(configKey: String, defaultValue: String): String = {
    Try(configData(configKey)).getOrElse(defaultValue)
  }
}
