package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryOsClstBrowserTableAccessor extends CassandraTable[HourlyAggPrtnEntryOsClstBrowserTableAccessor, HourlyAggPrtnEntryOsClstBrowserRow] with RootConnector with IHourlyAggPrtnEntryOsClstBrowserTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_os_clst_browser"

  def fromRow(row: Row): HourlyAggPrtnEntryOsClstBrowserRow = {
    HourlyAggPrtnEntryOsClstBrowserRow(
      partner_id(row), 
entry_id(row), 
operating_system(row), 
metric(row), 
year(row), 
hour(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryOsClstBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, operatingSystem : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryOsClstBrowserRow(partnerId:Int,
entryId:String,
operatingSystem:Int,
metric:String,
year:Int,
hour:DateTime,
browser:Int,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryOsClstBrowserTableAccessor {
  def query(partnerId : Int, entryId : String, operatingSystem : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]]
 def query(partnerId : Int, entryId : String, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]]
 def query(partnerId : Int, entryId : String, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[HourlyAggPrtnEntryOsClstBrowserRow]]
}