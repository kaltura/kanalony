package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaClstAppTableAccessor extends CassandraTable[MinutelyUaClstAppTableAccessor, MinutelyUaClstAppRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object application extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_clst_app"

  def fromRow(row: Row): MinutelyUaClstAppRow = {
    MinutelyUaClstAppRow(
      partner_id(row), 
metric(row), 
minute(row), 
application(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaClstAppRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.application, entity.application)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : Int) : SelectQuery[MinutelyUaClstAppTableAccessor, MinutelyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaClstAppTableAccessor, MinutelyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, applicationStart : String, applicationEnd : String) : SelectQuery[MinutelyUaClstAppTableAccessor, MinutelyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.application gte applicationStart)
.and(_.application lt applicationEnd)
  }
def query(partnerIdList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaClstAppTableAccessor, MinutelyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaClstAppTableAccessor, MinutelyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, applicationStart : String, applicationEnd : String) : SelectQuery[MinutelyUaClstAppTableAccessor, MinutelyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.application gte applicationStart)
.and(_.application lt applicationEnd)
  }

}