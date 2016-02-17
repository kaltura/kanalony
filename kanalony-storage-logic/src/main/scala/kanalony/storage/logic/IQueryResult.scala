package kanalony.storage.logic

/**
 * Created by elad.benedict on 2/16/2016.
 */
trait IQueryResult {
  val headers : List[String]
  val rows : List[List[String]]
}
