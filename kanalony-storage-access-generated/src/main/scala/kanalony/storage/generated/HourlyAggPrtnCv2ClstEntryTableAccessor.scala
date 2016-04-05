package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnCv2ClstEntryTableAccessor extends CassandraTable[HourlyAggPrtnCv2ClstEntryTableAccessor, HourlyAggPrtnCv2ClstEntryRow] with RootConnector with IHourlyAggPrtnCv2ClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object month extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_cv2_clst_entry"

  def fromRow(row: Row): HourlyAggPrtnCv2ClstEntryRow = {
    HourlyAggPrtnCv2ClstEntryRow(
      partner_id(row), 
custom_var2(row), 
month(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnCv2ClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var2, entity.customVar2)
.value(_.month, entity.month)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar2 : String, month : Int, metric : String) : Future[List[HourlyAggPrtnCv2ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var2 eqs customVar2)
.and(_.month eqs month)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar2 : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCv2ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var2 eqs customVar2)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar2 : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCv2ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var2 eqs customVar2)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], customVar2List : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnCv2ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var2 in customVar2List)
.and(_.month in monthList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar2List : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCv2ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var2 in customVar2List)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar2List : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCv2ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var2 in customVar2List)
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
case class HourlyAggPrtnCv2ClstEntryRow(partnerId:Int,
customVar2:String,
month:Int,
metric:String,
hour:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnCv2ClstEntryTableAccessor {
  def query(partnerId : Int, customVar2 : String, month : Int, metric : String) : Future[List[HourlyAggPrtnCv2ClstEntryRow]]
 def query(partnerId : Int, customVar2 : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCv2ClstEntryRow]]
 def query(partnerId : Int, customVar2 : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCv2ClstEntryRow]]
def query(partnerIdList : List[Int], customVar2List : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnCv2ClstEntryRow]]
 def query(partnerIdList : List[Int], customVar2List : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCv2ClstEntryRow]]
 def query(partnerIdList : List[Int], customVar2List : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCv2ClstEntryRow]]
}