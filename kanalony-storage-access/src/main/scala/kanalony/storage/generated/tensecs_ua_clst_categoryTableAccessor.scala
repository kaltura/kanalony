package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class tensecs_ua_clst_categoryTableAccessor extends CassandraTable[tensecs_ua_clst_categoryTableAccessor, tensecs_ua_clst_categoryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object category extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_clst_category"

  def fromRow(row: Row): tensecs_ua_clst_categoryRow = {
    tensecs_ua_clst_categoryRow(
      partner_id(row), 
metric(row), 
tensecs(row), 
category(row), 
value(row)
    )
  }

  def store(entity: tensecs_ua_clst_categoryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.category, entity.category)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, metric : Int) : SelectQuery[tensecs_ua_clst_categoryTableAccessor, tensecs_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
  }
 def query(partner_id : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[tensecs_ua_clst_categoryTableAccessor, tensecs_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partner_id : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime, categoryStart : String, categoryEnd : String) : SelectQuery[tensecs_ua_clst_categoryTableAccessor, tensecs_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
  }
def query(partner_id_list : List[Int], metric_list : List[Int]) : SelectQuery[tensecs_ua_clst_categoryTableAccessor, tensecs_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
  }
 def query(partner_id_list : List[Int], metric_list : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[tensecs_ua_clst_categoryTableAccessor, tensecs_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partner_id_list : List[Int], metric_list : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, categoryStart : String, categoryEnd : String) : SelectQuery[tensecs_ua_clst_categoryTableAccessor, tensecs_ua_clst_categoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.metric in metric_list)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
  }

}