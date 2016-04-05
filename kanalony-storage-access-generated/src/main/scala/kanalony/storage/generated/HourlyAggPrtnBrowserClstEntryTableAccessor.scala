package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnBrowserClstEntryTableAccessor extends CassandraTable[HourlyAggPrtnBrowserClstEntryTableAccessor, HourlyAggPrtnBrowserClstEntryRow] with RootConnector with IHourlyAggPrtnBrowserClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object browser extends IntColumn(this)with PartitionKey[Int]
object month extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_browser_clst_entry"

  def fromRow(row: Row): HourlyAggPrtnBrowserClstEntryRow = {
    HourlyAggPrtnBrowserClstEntryRow(
      partner_id(row), 
browser(row), 
month(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnBrowserClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.browser, entity.browser)
.value(_.month, entity.month)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, browser : Int, month : Int, metric : String) : Future[List[HourlyAggPrtnBrowserClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.browser eqs browser)
.and(_.month eqs month)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, browser : Int, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnBrowserClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.browser eqs browser)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, browser : Int, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnBrowserClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.browser eqs browser)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], browserList : List[Int], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnBrowserClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.browser in browserList)
.and(_.month in monthList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], browserList : List[Int], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnBrowserClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.browser in browserList)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], browserList : List[Int], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnBrowserClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.browser in browserList)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnBrowserClstEntryRow(partnerId:Int,
browser:Int,
month:Int,
metric:String,
hour:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnBrowserClstEntryTableAccessor {
  def query(partnerId : Int, browser : Int, month : Int, metric : String) : Future[List[HourlyAggPrtnBrowserClstEntryRow]]
 def query(partnerId : Int, browser : Int, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnBrowserClstEntryRow]]
 def query(partnerId : Int, browser : Int, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnBrowserClstEntryRow]]
def query(partnerIdList : List[Int], browserList : List[Int], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnBrowserClstEntryRow]]
 def query(partnerIdList : List[Int], browserList : List[Int], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnBrowserClstEntryRow]]
 def query(partnerIdList : List[Int], browserList : List[Int], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnBrowserClstEntryRow]]
}