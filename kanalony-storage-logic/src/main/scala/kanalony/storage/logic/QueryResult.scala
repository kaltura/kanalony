package kanalony.storage.logic

/**
 * Created by elad.benedict on 2/16/2016.
 */
case class QueryResult(headers : List[String], rows : List[List[String]]) extends IQueryResult
