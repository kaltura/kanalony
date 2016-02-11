package kanalony.storage.logic

import kanalony.storage.api.DbClientFactory

import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/10/2016.
 */

abstract class QueryBase[T] extends IQuery {

  val dbApi = DbClientFactory

  protected def typifyParams(params : Map[String, Any]) : T

  protected def executeQuery(params : T) : Future[IQueryResult]

  def execute(params : Map[String, Any]): Future[IQueryResult] = {
    val inputParams = typifyParams(params)
    executeQuery(inputParams)
  }
}