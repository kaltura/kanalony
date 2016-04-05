package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnCv1ClstCv2TableAccessor extends CassandraTable[MinutelyAggPrtnCv1ClstCv2TableAccessor, MinutelyAggPrtnCv1ClstCv2Row] with RootConnector with IMinutelyAggPrtnCv1ClstCv2TableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var2 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_cv1_clst_cv2"

  def fromRow(row: Row): MinutelyAggPrtnCv1ClstCv2Row = {
    MinutelyAggPrtnCv1ClstCv2Row(
      partner_id(row), 
custom_var1(row), 
metric(row), 
day(row), 
minute(row), 
custom_var2(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnCv1ClstCv2Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var1, entity.customVar1)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.custom_var2, entity.customVar2)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar1 : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar1 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar1 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.custom_var2 gte customVar2Start)
.and(_.custom_var2 lt customVar2End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.custom_var2 gte customVar2Start)
.and(_.custom_var2 lt customVar2End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnCv1ClstCv2Row(partnerId:Int,
customVar1:String,
metric:String,
day:Int,
minute:DateTime,
customVar2:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnCv1ClstCv2TableAccessor {
  def query(partnerId : Int, customVar1 : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]]
 def query(partnerId : Int, customVar1 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]]
 def query(partnerId : Int, customVar1 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]]
def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]]
 def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]]
 def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, customVar2Start : String, customVar2End : String) : Future[List[MinutelyAggPrtnCv1ClstCv2Row]]
}