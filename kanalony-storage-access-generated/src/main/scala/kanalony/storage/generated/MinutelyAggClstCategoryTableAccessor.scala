package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggClstCategoryTableAccessor extends CassandraTable[MinutelyAggClstCategoryTableAccessor, MinutelyAggClstCategoryRow] with RootConnector with IMinutelyAggClstCategoryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object category extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_clst_category"

  def fromRow(row: Row): MinutelyAggClstCategoryRow = {
    MinutelyAggClstCategoryRow(
      partner_id(row), 
metric(row), 
day(row), 
minute(row), 
category(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggClstCategoryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.category, entity.category)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : String, day : Int) : Future[List[MinutelyAggClstCategoryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstCategoryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, categoryStart : String, categoryEnd : String) : Future[List[MinutelyAggClstCategoryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggClstCategoryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstCategoryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, categoryStart : String, categoryEnd : String) : Future[List[MinutelyAggClstCategoryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.category gte categoryStart)
.and(_.category lt categoryEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggClstCategoryRow(partnerId:Int,
metric:String,
day:Int,
minute:DateTime,
category:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggClstCategoryTableAccessor {
  def query(partnerId : Int, metric : String, day : Int) : Future[List[MinutelyAggClstCategoryRow]]
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstCategoryRow]]
 def query(partnerId : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, categoryStart : String, categoryEnd : String) : Future[List[MinutelyAggClstCategoryRow]]
def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggClstCategoryRow]]
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggClstCategoryRow]]
 def query(partnerIdList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, categoryStart : String, categoryEnd : String) : Future[List[MinutelyAggClstCategoryRow]]
}