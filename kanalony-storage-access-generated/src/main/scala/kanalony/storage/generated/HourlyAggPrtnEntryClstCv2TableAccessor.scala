package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryClstCv2TableAccessor extends CassandraTable[HourlyAggPrtnEntryClstCv2TableAccessor, HourlyAggPrtnEntryClstCv2Row] with RootConnector with IHourlyAggPrtnEntryClstCv2TableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var2 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_clst_cv2"

  def fromRow(row: Row): HourlyAggPrtnEntryClstCv2Row = {
    HourlyAggPrtnEntryClstCv2Row(
      partner_id(row), 
entry_id(row), 
year(row), 
metric(row), 
hour(row), 
custom_var2(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryClstCv2Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.year, entity.year)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.custom_var2, entity.customVar2)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, year : Int, metric : String) : Future[List[HourlyAggPrtnEntryClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.year eqs year)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[HourlyAggPrtnEntryClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var2 gte customVar2Start)
.and(_.custom_var2 lt customVar2End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], yearList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnEntryClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.year in yearList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[HourlyAggPrtnEntryClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var2 gte customVar2Start)
.and(_.custom_var2 lt customVar2End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryClstCv2Row(partnerId:Int,
entryId:String,
year:Int,
metric:String,
hour:DateTime,
customVar2:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryClstCv2TableAccessor {
  def query(partnerId : Int, entryId : String, year : Int, metric : String) : Future[List[HourlyAggPrtnEntryClstCv2Row]]
 def query(partnerId : Int, entryId : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstCv2Row]]
 def query(partnerId : Int, entryId : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[HourlyAggPrtnEntryClstCv2Row]]
def query(partnerIdList : List[Int], entryIdList : List[String], yearList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnEntryClstCv2Row]]
 def query(partnerIdList : List[Int], entryIdList : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstCv2Row]]
 def query(partnerIdList : List[Int], entryIdList : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[HourlyAggPrtnEntryClstCv2Row]]
}