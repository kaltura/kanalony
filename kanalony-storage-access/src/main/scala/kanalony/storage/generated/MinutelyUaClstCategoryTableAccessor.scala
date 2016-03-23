package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaClstCategoryTableAccessor extends CassandraTable[MinutelyUaClstCategoryTableAccessor, MinutelyUaClstCategoryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object category extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_clst_category"

  def fromRow(row: Row): MinutelyUaClstCategoryRow = {
    MinutelyUaClstCategoryRow(
      partner_id(row), 
metric(row), 
minute(row), 
category(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaClstCategoryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.category, entity.category)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : Int) : SelectQuery[MinutelyUaClstCategoryTableAccessor, MinutelyUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaClstCategoryTableAccessor, MinutelyUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, categoryStart : String, categoryEnd : String) : SelectQuery[MinutelyUaClstCategoryTableAccessor, MinutelyUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
  }
def query(partnerIdList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaClstCategoryTableAccessor, MinutelyUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaClstCategoryTableAccessor, MinutelyUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, categoryStart : String, categoryEnd : String) : SelectQuery[MinutelyUaClstCategoryTableAccessor, MinutelyUaClstCategoryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
  }

}