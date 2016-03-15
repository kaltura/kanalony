package kanalony.storage.logic

import com.kaltura.model.entities.InternalMetrics
import kanalony.storage.logic.queries.model._
import org.joda.time.DateTime

/**
 * Created by elad.benedict on 2/14/2016.
 */

case class QueryParams(dimensionDefinitions : List[IQueryDimensionDefinition], metrics : List[InternalMetrics.Value], start : DateTime, end : DateTime)
