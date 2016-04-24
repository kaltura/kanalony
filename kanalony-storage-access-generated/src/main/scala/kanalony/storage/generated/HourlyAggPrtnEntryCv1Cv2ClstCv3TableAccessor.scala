package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryCv1Cv2ClstCv3TableAccessor extends CassandraTable[HourlyAggPrtnEntryCv1Cv2ClstCv3TableAccessor, HourlyAggPrtnEntryCv1Cv2ClstCv3Row] with RootConnector with IHourlyAggPrtnEntryCv1Cv2ClstCv3TableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var3 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_cv1_cv2_clst_cv3"

  def fromRow(row: Row): HourlyAggPrtnEntryCv1Cv2ClstCv3Row = {
    HourlyAggPrtnEntryCv1Cv2ClstCv3Row(
      partner_id(row), 
entry_id(row), 
custom_var1(row), 
custom_var2(row), 
year(row), 
metric(row), 
hour(row), 
custom_var3(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryCv1Cv2ClstCv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.custom_var1, entity.customVar1)
.value(_.custom_var2, entity.customVar2)
.value(_.year, entity.year)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.custom_var3, entity.customVar3)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, year : Int, metric : String) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.year eqs year)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, customVar3Start : String, customVar3End : String) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.year in yearList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, customVar3Start : String, customVar3End : String) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryCv1Cv2ClstCv3Row(partnerId:Int,
entryId:String,
customVar1:String,
customVar2:String,
year:Int,
metric:String,
hour:DateTime,
customVar3:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryCv1Cv2ClstCv3TableAccessor {
  def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, year : Int, metric : String) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]]
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]]
 def query(partnerId : Int, entryId : String, customVar1 : String, customVar2 : String, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, customVar3Start : String, customVar3End : String) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]]
def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]]
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]]
 def query(partnerIdList : List[Int], entryIdList : List[String], customVar1List : List[String], customVar2List : List[String], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, customVar3Start : String, customVar3End : String) : Future[List[HourlyAggPrtnEntryCv1Cv2ClstCv3Row]]
}