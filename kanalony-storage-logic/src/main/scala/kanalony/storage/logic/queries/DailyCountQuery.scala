package kanalony.storage.logic.queries

import com.kaltura.model.entities.Metrics
import kanalony.storage.logic._
import kanalony.storage.logic.queries.model._
import org.joda.time.DateTime
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future



class DailyCountQuery(queryParams: QueryParams) extends DailyQueryBase(queryParams) {
  override def computeGroupAggregatedValue: (List[List[String]]) => Double = _.foldLeft(0.0)(_ + countFieldExtractor(_))
}

