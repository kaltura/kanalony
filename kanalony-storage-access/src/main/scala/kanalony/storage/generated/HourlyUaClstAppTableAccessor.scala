package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaClstAppTableAccessor extends CassandraTable[HourlyUaClstAppTableAccessor, HourlyUaClstAppRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object application extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_clst_app"

  def fromRow(row: Row): HourlyUaClstAppRow = {
    HourlyUaClstAppRow(
      partner_id(row), 
metric(row), 
year(row), 
hour(row), 
application(row), 
value(row)
    )
  }

  def store(entity: HourlyUaClstAppRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.application, entity.application)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : Int, year : Int) : SelectQuery[HourlyUaClstAppTableAccessor, HourlyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaClstAppTableAccessor, HourlyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, applicationStart : String, applicationEnd : String) : SelectQuery[HourlyUaClstAppTableAccessor, HourlyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.application gte applicationStart)
.and(_.application lt applicationEnd)
  }
def query(partnerIdList : List[Int], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaClstAppTableAccessor, HourlyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaClstAppTableAccessor, HourlyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, applicationStart : String, applicationEnd : String) : SelectQuery[HourlyUaClstAppTableAccessor, HourlyUaClstAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.application gte applicationStart)
.and(_.application lt applicationEnd)
  }

}