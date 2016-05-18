package com.kaltura.core.cassandra

import com.datastax.driver.core.{Cluster, Session}
import com.kaltura.core.utils.ConfigurationManager

/**
 * Created by ofirk on 26/01/2016.
 */
object ClusterManager {
  private val cluster = Cluster.builder()
            .addContactPoint(ConfigurationManager.getOrElse("kanalony.events_enrichment.cassandra_host","127.0.0.1"))
            .build()

  private val session = cluster.connect()

  def getSession = session

  def close() = if (cluster != null) cluster.close()
}
