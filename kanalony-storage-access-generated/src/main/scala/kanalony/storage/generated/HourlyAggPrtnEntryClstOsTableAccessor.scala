package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryClstOsTableAccessor extends CassandraTable[HourlyAggPrtnEntryClstOsTableAccessor, HourlyAggPrtnEntryClstOsRow] with RootConnector with IHourlyAggPrtnEntryClstOsTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object operating_system extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_clst_os"

  def fromRow(row: Row): HourlyAggPrtnEntryClstOsRow = {
    HourlyAggPrtnEntryClstOsRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
year(row), 
hour(row), 
operating_system(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryClstOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.operating_system, entity.operatingSystem)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[HourlyAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[HourlyAggPrtnEntryClstOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryClstOsRow(partnerId:Int,
entryId:String,
metric:String,
year:Int,
hour:DateTime,
operatingSystem:Int,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryClstOsTableAccessor {
  def query(partnerId : Int, entryId : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryClstOsRow]]
 def query(partnerId : Int, entryId : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstOsRow]]
 def query(partnerId : Int, entryId : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[HourlyAggPrtnEntryClstOsRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryClstOsRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstOsRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[HourlyAggPrtnEntryClstOsRow]]
}