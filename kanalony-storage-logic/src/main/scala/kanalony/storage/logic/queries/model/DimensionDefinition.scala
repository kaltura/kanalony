package kanalony.storage.logic.queries.model

import kanalony.storage.logic.Dimensions

/**
 * Created by elad.benedict on 2/16/2016.
 */

case class DimensionDefinition(dimension : Dimensions.Value, constraint : IDimensionConstraint) extends IDimensionDefinition
