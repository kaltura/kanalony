package kanalony.storage.logic

import com.kaltura.model.entities.Metric

/**
 * Created by elad.benedict on 2/16/2016.
 */

trait IQueryLocator {
  def locate(queryParams: QueryParams) : List[(IQuery, List[Metric])]
}
