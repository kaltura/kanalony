package kanalony.storage.logic.queries.model

/**
 * Created by elad.benedict on 3/14/2016.
 */

trait IQueryDimensionDefinition extends IDimensionDefinition {
  val includeInResult : Boolean
}
