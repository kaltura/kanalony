package kanalony.storage.logic

import com.kaltura.model.entities.{Metric, Metrics}
import kanalony.storage.logic.queries.model._

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/10/2016.
 */

trait IQuery {
  def isMetricSupported(metric: Metric) : Boolean
  val supportedWellKnownMetrics : Set[Metric]
  val dimensionInformation : List[IDimensionDefinition]
  def query(params : QueryParams) : Future[List[IQueryResult]] // IQueryResult per metric
}
