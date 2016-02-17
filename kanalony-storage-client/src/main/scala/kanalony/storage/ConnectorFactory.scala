package kanalony.storage

import com.websudos.phantom.connectors.{ContactPoint, KeySpaceDef}

/**
 * Created by elad.benedict on 2/7/2016.
 */
object ConnectorFactory extends IConnectorFactory {
  override val connector : KeySpaceDef = ContactPoint.local.keySpace("my_keyspace")
}
