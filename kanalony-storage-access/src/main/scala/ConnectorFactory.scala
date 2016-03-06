package kanalony.storage

import com.typesafe.config.ConfigFactory
import com.websudos.phantom.connectors.{ContactPoints, KeySpaceDef}
import collection.JavaConversions._

/**
 * Created by elad.benedict on 2/7/2016.
 */

object ConnectorFactory extends IConnectorFactory {
  val cassandraHosts : Seq[String] = ConfigFactory.load().getStringList("config.cassandra.hosts")
  val cassandraPort = ConfigFactory.load().getInt("config.cassandra.port")
  val keyspace = ConfigFactory.load().getString("config.cassandra.keyspace")
  override val connector : KeySpaceDef = ContactPoints(cassandraHosts, cassandraPort).keySpace(keyspace)
}
