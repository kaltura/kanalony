package kanalony.storage

import com.websudos.phantom.connectors.KeySpaceDef

/**
 * Created by elad.benedict on 2/7/2016.
 */
trait IConnectorFactory {
  val connector : KeySpaceDef
  val dimConnector: KeySpaceDef
}
