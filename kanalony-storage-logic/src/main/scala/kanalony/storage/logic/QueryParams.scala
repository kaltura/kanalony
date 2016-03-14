package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.model.{IQueryDimensionDefinition, QueryDimensionDefinition}
import org.joda.time.DateTime

/**
 * Created by elad.benedict on 2/14/2016.
 */

case class QueryParams(dimensionDefinitions : List[IQueryDimensionDefinition], metrics : List[Metrics.Value], start : DateTime, end : DateTime)
