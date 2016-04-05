package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnCv3ClstEntryTableAccessor extends CassandraTable[HourlyAggPrtnCv3ClstEntryTableAccessor, HourlyAggPrtnCv3ClstEntryRow] with RootConnector with IHourlyAggPrtnCv3ClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var3 extends StringColumn(this)with PartitionKey[String]
object month extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_cv3_clst_entry"

  def fromRow(row: Row): HourlyAggPrtnCv3ClstEntryRow = {
    HourlyAggPrtnCv3ClstEntryRow(
      partner_id(row), 
custom_var3(row), 
month(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnCv3ClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var3, entity.customVar3)
.value(_.month, entity.month)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar3 : String, month : Int, metric : String) : Future[List[HourlyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.month eqs month)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar3 : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar3 : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], customVar3List : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
.and(_.month in monthList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar3List : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar3List : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCv3ClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
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
case class HourlyAggPrtnCv3ClstEntryRow(partnerId:Int,
customVar3:String,
month:Int,
metric:String,
hour:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnCv3ClstEntryTableAccessor {
  def query(partnerId : Int, customVar3 : String, month : Int, metric : String) : Future[List[HourlyAggPrtnCv3ClstEntryRow]]
 def query(partnerId : Int, customVar3 : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCv3ClstEntryRow]]
 def query(partnerId : Int, customVar3 : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCv3ClstEntryRow]]
def query(partnerIdList : List[Int], customVar3List : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnCv3ClstEntryRow]]
 def query(partnerIdList : List[Int], customVar3List : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCv3ClstEntryRow]]
 def query(partnerIdList : List[Int], customVar3List : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCv3ClstEntryRow]]
}