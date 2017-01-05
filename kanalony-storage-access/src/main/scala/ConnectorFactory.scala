package kanalony.storage

import com.typesafe.config.ConfigFactory
import com.websudos.phantom.connectors.{ContactPoints, KeySpaceDef}
import collection.JavaConversions._

/**
 * Created by elad.benedict on 2/7/2016.
 */

object ConnectorFactory extends IConnectorFactory {
  val config = ConfigFactory.load()
  val cassandraHosts : Seq[String] = config.getStringList("config.cassandra.hosts")
  val cassandraPort = config.getInt("config.cassandra.port")
  val keyspace = config.getString("config.cassandra.keyspace")
  override val connector : KeySpaceDef = ContactPoints(cassandraHosts, cassandraPort).keySpace(keyspace)
  override val dimConnector : KeySpaceDef = ContactPoints(cassandraHosts, cassandraPort).keySpace("enrichment_cache")
}
