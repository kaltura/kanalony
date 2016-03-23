package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsUaClstCategoryTableAccessor extends CassandraTable[TensecsUaClstCategoryTableAccessor, TensecsUaClstCategoryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object category extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_clst_category"

  def fromRow(row: Row): TensecsUaClstCategoryRow = {
    TensecsUaClstCategoryRow(
      partner_id(row), 
metric(row), 
tensecs(row), 
category(row), 
value(row)
    )
  }

  def store(entity: TensecsUaClstCategoryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.category, entity.category)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : Int) : SelectQuery[TensecsUaClstCategoryTableAccessor, TensecsUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
  }
 def query(partnerId : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaClstCategoryTableAccessor, TensecsUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerId : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime, categoryStart : String, categoryEnd : String) : SelectQuery[TensecsUaClstCategoryTableAccessor, TensecsUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
  }
def query(partnerIdList : List[Int], metricList : List[Int]) : SelectQuery[TensecsUaClstCategoryTableAccessor, TensecsUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaClstCategoryTableAccessor, TensecsUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, categoryStart : String, categoryEnd : String) : SelectQuery[TensecsUaClstCategoryTableAccessor, TensecsUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
  }

}