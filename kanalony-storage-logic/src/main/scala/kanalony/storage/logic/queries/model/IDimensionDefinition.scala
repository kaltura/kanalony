package kanalony.storage.logic.queries.model

import kanalony.storage.logic.Dimensions

/**
 * Created by elad.benedict on 3/14/2016.
 */

trait IDimensionDefinition {
  val dimension : Dimensions.Value
  val constraint : IDimensionConstraint
}
