package kanalony.storage.logic

import kanalony.storage.logic.queries.model.QueryDimensionDefinition
import org.joda.time.DateTime

/**
 * Created by elad.benedict on 2/14/2016.
 */

case class QueryParams(dimensionDefinitions : List[QueryDimensionDefinition], metric : Metrics.Value, start : DateTime, end : DateTime)
