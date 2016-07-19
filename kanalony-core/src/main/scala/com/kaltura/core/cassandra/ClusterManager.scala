package com.kaltura.core.cassandra

import com.datastax.driver.core.Cluster
import com.kaltura.core.utils.ConfigurationManager

/**
 * Created by ofirk on 26/01/2016.
 */
object ClusterManager {
  private val clusterBuilder = Cluster.builder()
  ConfigurationManager.getOrElse("kanalony.cassandra_host","127.0.0.1")
    .split(",").foreach(x => clusterBuilder.addContactPoints(x))
  private val cluster = clusterBuilder.build()

  private val session = cluster.connect()

  def getSession = session

  def close() = if (cluster != null) cluster.close()
}
