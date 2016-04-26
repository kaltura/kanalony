package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnCv1Cv2ClstCv3TableAccessor extends CassandraTable[MinutelyAggPrtnCv1Cv2ClstCv3TableAccessor, MinutelyAggPrtnCv1Cv2ClstCv3Row] with RootConnector with IMinutelyAggPrtnCv1Cv2ClstCv3TableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object custom_var2 extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var3 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_cv1_cv2_clst_cv3"

  def fromRow(row: Row): MinutelyAggPrtnCv1Cv2ClstCv3Row = {
    MinutelyAggPrtnCv1Cv2ClstCv3Row(
      partner_id(row), 
custom_var1(row), 
custom_var2(row), 
metric(row), 
day(row), 
minute(row), 
custom_var3(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnCv1Cv2ClstCv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var1, entity.customVar1)
.value(_.custom_var2, entity.customVar2)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.custom_var3, entity.customVar3)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar1 : String, customVar2 : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar1 : String, customVar2 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, customVar1 : String, customVar2 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, customVar3Start : String, customVar3End : String) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.custom_var2 eqs customVar2)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, customVar3Start : String, customVar3End : String) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.custom_var2 in customVar2List)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnCv1Cv2ClstCv3Row(partnerId:Int,
customVar1:String,
customVar2:String,
metric:String,
day:Int,
minute:DateTime,
customVar3:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnCv1Cv2ClstCv3TableAccessor {
  def query(partnerId : Int, customVar1 : String, customVar2 : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]]
 def query(partnerId : Int, customVar1 : String, customVar2 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]]
 def query(partnerId : Int, customVar1 : String, customVar2 : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, customVar3Start : String, customVar3End : String) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]]
def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]]
 def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]]
 def query(partnerIdList : List[Int], customVar1List : List[String], customVar2List : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, customVar3Start : String, customVar3End : String) : Future[List[MinutelyAggPrtnCv1Cv2ClstCv3Row]]
}