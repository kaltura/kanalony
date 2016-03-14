package kanalony.storage.logic

import com.kaltura.model.entities.Metrics

/**
 * Created by elad.benedict on 3/7/2016.
 */

abstract class ComputedQueryFactory[T] {
  type QueryCreator = (QueryParams) => List[(IQuery, List[Metrics.Value])]
  protected val queryCreatorGetter : Map[T,QueryCreator]
  def getErrorMessage(value : T) : String

  lazy val values = queryCreatorGetter.keys.toSet

  def getQueryCreator(value: T): QueryCreator = {
    if (!values.contains(value))
    {
      throw new IllegalArgumentException(getErrorMessage(value))
    }
    queryCreatorGetter(value)
  }
}
