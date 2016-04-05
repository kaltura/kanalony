package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryCv1ClstCv2TableAccessor extends CassandraTable[HourlyAggPrtnEntryCv1ClstCv2TableAccessor, HourlyAggPrtnEntryCv1ClstCv2Row] with RootConnector with IHourlyAggPrtnEntryCv1ClstCv2TableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var2 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_cv1_clst_cv2"

  def fromRow(row: Row): HourlyAggPrtnEntryCv1ClstCv2Row = {
    HourlyAggPrtnEntryCv1ClstCv2Row(
      partner_id(row), 
entry_id(row), 
custom_var1(row), 
year(row), 
metric(row), 
hour(row), 
custom_var2(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryCv1ClstCv2Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.custom_var1, entity.customVar1)
.value(_.year, entity.year)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.custom_var2, entity.customVar2)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, customVar1 : String, year : Int, metric : String) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.year eqs year)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var2 gte customVar2Start)
.and(_.custom_var2 lt customVar2End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], yearList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.year in yearList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
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
case class HourlyAggPrtnEntryCv1ClstCv2Row(partnerId:Int,
entryId:String,
customVar1:String,
year:Int,
metric:String,
hour:DateTime,
customVar2:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryCv1ClstCv2TableAccessor {
  def query(partnerId : Int, entryId : String, customVar1 : String, year : Int, metric : String) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]]
 def query(partnerId : Int, entryId : String, customVar1 : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]]
 def query(partnerId : Int, entryId : String, customVar1 : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]]
def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], yearList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]]
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]]
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[HourlyAggPrtnEntryCv1ClstCv2Row]]
}