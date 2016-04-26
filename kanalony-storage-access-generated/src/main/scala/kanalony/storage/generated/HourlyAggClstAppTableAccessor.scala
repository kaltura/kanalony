package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggClstAppTableAccessor extends CassandraTable[HourlyAggClstAppTableAccessor, HourlyAggClstAppRow] with RootConnector with IHourlyAggClstAppTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object application extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_clst_app"

  def fromRow(row: Row): HourlyAggClstAppRow = {
    HourlyAggClstAppRow(
      partner_id(row), 
metric(row), 
year(row), 
hour(row), 
application(row), 
value(row)
    )
  }

  def store(entity: HourlyAggClstAppRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.application, entity.application)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : String, year : Int) : Future[List[HourlyAggClstAppRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstAppRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, applicationStart : String, applicationEnd : String) : Future[List[HourlyAggClstAppRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.application gte applicationStart)
.and(_.application lt applicationEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggClstAppRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstAppRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, applicationStart : String, applicationEnd : String) : Future[List[HourlyAggClstAppRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.application gte applicationStart)
.and(_.application lt applicationEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggClstAppRow(partnerId:Int,
metric:String,
year:Int,
hour:DateTime,
application:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggClstAppTableAccessor {
  def query(partnerId : Int, metric : String, year : Int) : Future[List[HourlyAggClstAppRow]]
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstAppRow]]
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, applicationStart : String, applicationEnd : String) : Future[List[HourlyAggClstAppRow]]
def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggClstAppRow]]
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstAppRow]]
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, applicationStart : String, applicationEnd : String) : Future[List[HourlyAggClstAppRow]]
}