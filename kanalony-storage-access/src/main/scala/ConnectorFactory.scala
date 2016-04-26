package kanalony.storage

import com.typesafe.config.ConfigFactory
import com.websudos.phantom.connectors.{ContactPoints, KeySpaceDef}
import collection.JavaConversions._

/**
 * Created by elad.benedict on 2/7/2016.
 */

object ConnectorFactory extends IConnectorFactory {
  val cassandraHosts : Seq[String] = List("il-bigdata-5","il-bigdata-6","il-bigdata-7","il-bigdata-8","il-bigdata-9","il-bigdata-10")//ConfigFactory.load().getStringList("config.cassandra.hosts")
  val cassandraPort = 9042//ConfigFactory.load().getInt("config.cassandra.port")
  val keyspace = "kanalony_agg" //ConfigFactory.load().getString("config.cassandra.keyspace")
  override val connector : KeySpaceDef = ContactPoints(cassandraHosts, cassandraPort).keySpace(keyspace)
}
