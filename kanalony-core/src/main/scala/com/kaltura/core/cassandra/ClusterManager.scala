package com.kaltura.core.cassandra

import java.net.InetAddress

import com.datastax.driver.core.{Cluster, Session}
import com.kaltura.core.utils.ConfigurationManager

/**
 * Created by ofirk on 26/01/2016.
 */
object ClusterManager {
  private val clusterBuilder = Cluster.builder()
  ConfigurationManager.getOrElse("kanalony.events_enrichment.cassandra_host","127.0.0.1")
    .split(",").foreach(x => clusterBuilder.addContactPoints(x))
  private val cluster = clusterBuilder.build()

  private val session = cluster.connect()

  def getSession = session

  def close() = if (cluster != null) cluster.close()
}
