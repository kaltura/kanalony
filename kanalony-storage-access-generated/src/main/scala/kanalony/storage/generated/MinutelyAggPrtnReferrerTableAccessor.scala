package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnReferrerTableAccessor extends CassandraTable[MinutelyAggPrtnReferrerTableAccessor, MinutelyAggPrtnReferrerRow] with RootConnector with IMinutelyAggPrtnReferrerTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object referrer extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_referrer"

  def fromRow(row: Row): MinutelyAggPrtnReferrerRow = {
    MinutelyAggPrtnReferrerRow(
      partner_id(row), 
referrer(row), 
metric(row), 
day(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnReferrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.referrer, entity.referrer)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, referrer : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, referrer : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnReferrerRow(partnerId:Int,
referrer:String,
metric:String,
day:Int,
minute:DateTime,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnReferrerTableAccessor {
  def query(partnerId : Int, referrer : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnReferrerRow]]
 def query(partnerId : Int, referrer : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnReferrerRow]]
def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnReferrerRow]]
 def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnReferrerRow]]
}