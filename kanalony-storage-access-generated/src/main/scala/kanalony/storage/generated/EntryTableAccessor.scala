package kanalony.storage.generated

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._

import scala.concurrent.Future

/**
 * Created by orlylampert on 12/12/16.
 */
abstract class EntryTableAccessor extends CassandraTable[EntryTableAccessor, EntryRow] with RootConnector {

  object id extends StringColumn(this)with PartitionKey[String]
  object name extends StringColumn(this)

  override def tableName = "dim_entries_metadata"

  def fromRow(row: Row): EntryRow = {
    EntryRow(
      id(row),
      name(row)
    )
  }

  def store(entity: EntryRow): Future[ResultSet] = {
    insert.value(_.id, entity.entryId)
      .value(_.name, entity.entryName)
      .future()
  }

  def query(entryId : String) : Future[List[EntryRow]] = {
    select.where(_.id eqs entryId)
      .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

  def query(entryIdList : List[String]) : Future[List[EntryRow]] = {
    select.where(_.id in entryIdList)
      .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

case class EntryRow(entryId:String,
                    entryName:String)



