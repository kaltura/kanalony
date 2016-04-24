package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnReferrerClstEntryTableAccessor extends CassandraTable[HourlyAggPrtnReferrerClstEntryTableAccessor, HourlyAggPrtnReferrerClstEntryRow] with RootConnector with IHourlyAggPrtnReferrerClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object referrer extends StringColumn(this)with PartitionKey[String]
object month extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_referrer_clst_entry"

  def fromRow(row: Row): HourlyAggPrtnReferrerClstEntryRow = {
    HourlyAggPrtnReferrerClstEntryRow(
      partner_id(row), 
referrer(row), 
month(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnReferrerClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.referrer, entity.referrer)
.value(_.month, entity.month)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, referrer : String, month : Int, metric : String) : Future[List[HourlyAggPrtnReferrerClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.month eqs month)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, referrer : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnReferrerClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, referrer : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnReferrerClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], referrerList : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnReferrerClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.month in monthList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], referrerList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnReferrerClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], referrerList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnReferrerClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
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
case class HourlyAggPrtnReferrerClstEntryRow(partnerId:Int,
referrer:String,
month:Int,
metric:String,
hour:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnReferrerClstEntryTableAccessor {
  def query(partnerId : Int, referrer : String, month : Int, metric : String) : Future[List[HourlyAggPrtnReferrerClstEntryRow]]
 def query(partnerId : Int, referrer : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnReferrerClstEntryRow]]
 def query(partnerId : Int, referrer : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnReferrerClstEntryRow]]
def query(partnerIdList : List[Int], referrerList : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnReferrerClstEntryRow]]
 def query(partnerIdList : List[Int], referrerList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnReferrerClstEntryRow]]
 def query(partnerIdList : List[Int], referrerList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnReferrerClstEntryRow]]
}