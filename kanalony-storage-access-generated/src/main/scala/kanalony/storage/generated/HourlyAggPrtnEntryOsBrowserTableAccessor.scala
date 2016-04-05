package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryOsBrowserTableAccessor extends CassandraTable[HourlyAggPrtnEntryOsBrowserTableAccessor, HourlyAggPrtnEntryOsBrowserRow] with RootConnector with IHourlyAggPrtnEntryOsBrowserTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object browser extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_os_browser"

  def fromRow(row: Row): HourlyAggPrtnEntryOsBrowserRow = {
    HourlyAggPrtnEntryOsBrowserRow(
      partner_id(row), 
entry_id(row), 
operating_system(row), 
browser(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryOsBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.operating_system, entity.operatingSystem)
.value(_.browser, entity.browser)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, operatingSystem : Int, browser : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryOsBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.operating_system eqs operatingSystem)
.and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, operatingSystem : Int, browser : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryOsBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.operating_system eqs operatingSystem)
.and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryOsBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.operating_system in operatingSystemList)
.and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryOsBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.operating_system in operatingSystemList)
.and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryOsBrowserRow(partnerId:Int,
entryId:String,
operatingSystem:Int,
browser:Int,
metric:String,
year:Int,
hour:DateTime,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryOsBrowserTableAccessor {
  def query(partnerId : Int, entryId : String, operatingSystem : Int, browser : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryOsBrowserRow]]
 def query(partnerId : Int, entryId : String, operatingSystem : Int, browser : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryOsBrowserRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryOsBrowserRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], operatingSystemList : List[Int], browserList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryOsBrowserRow]]
}