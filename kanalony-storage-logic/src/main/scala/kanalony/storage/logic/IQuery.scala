package kanalony.storage.logic

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.model._

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/10/2016.
 */

trait IQuery {
  val supportedMetrics : Set[Metrics.Value]
  val dimensionInformation : List[IDimensionDefinition]
  def query(params : QueryParams) : Future[List[IQueryResult]] // IQueryResult per metric
}
