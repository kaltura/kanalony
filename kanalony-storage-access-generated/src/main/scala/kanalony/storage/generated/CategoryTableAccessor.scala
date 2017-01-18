package kanalony.storage.generated

import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._

import scala.concurrent.Future

/**
 * Created by orlylampert on 12/12/16.
 */
abstract class CategoryTableAccessor extends CassandraTable[CategoryTableAccessor, CategoryRow] with RootConnector {

  object id extends StringColumn(this)with PartitionKey[String]
  object name extends StringColumn(this)

  override def tableName = "dim_categories_metadata"

  def fromRow(row: Row): CategoryRow = {
    CategoryRow(
      id(row),
      name(row)
    )
  }

  def store(entity: CategoryRow): Future[ResultSet] = {
    insert.value(_.id, entity.categoryId)
      .value(_.name, entity.categoryName)
      .future()
  }

  def query(categoryId : String) : Future[List[CategoryRow]] = {
    select.where(_.id eqs categoryId)
      .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

  def query(categoryIdList : List[String]) : Future[List[CategoryRow]] = {
    select.where(_.id in categoryIdList)
      .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

case class CategoryRow(categoryId:String,
                    categoryName:String)



