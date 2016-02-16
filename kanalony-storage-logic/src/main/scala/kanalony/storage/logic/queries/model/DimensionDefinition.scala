package kanalony.storage.logic.queries.model

import kanalony.storage.logic.Dimensions

/**
 * Created by elad.benedict on 2/16/2016.
 */
//TODO: use a class hierarchy instead?
case class DimensionDefinition(dimension : Dimensions.Value, constraint : IDimensionConstraint)
