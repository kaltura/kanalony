package kanalony.storage.logic

import kanalony.storage.api.DbClientFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by elad.benedict on 2/10/2016.
 */

abstract class QueryBase[TReq, TQueryRow] extends IQuery {

  val dbApi = DbClientFactory

  protected def extractParams(queryParams : QueryParams) : TReq

  private[logic] def executeQuery(params : TReq) : Future[List[TQueryRow]]

  protected def getResultHeaders() : List[String]

  protected def getResultRow(row: TQueryRow) : List[String]

  def query(params : QueryParams): Future[IQueryResult] = {
    val inputParams = extractParams(params)
    val retrievedRowsFuture = executeQuery(inputParams)
    val result = retrievedRowsFuture.map {
      retrievedRows => {
        val processedRows = retrievedRows.map(row => getResultRow(row))
        new QueryResult(getResultHeaders(), processedRows)
      }
    }
    result
  }
}