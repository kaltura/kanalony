package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnCategoryTableAccessor extends CassandraTable[MinutelyAggPrtnCategoryTableAccessor, MinutelyAggPrtnCategoryRow] with RootConnector with IMinutelyAggPrtnCategoryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object category extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_category"

  def fromRow(row: Row): MinutelyAggPrtnCategoryRow = {
    MinutelyAggPrtnCategoryRow(
      partner_id(row), 
category(row), 
metric(row), 
day(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnCategoryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.category, entity.category)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, category : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnCategoryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.category eqs category)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, category : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCategoryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.category eqs category)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], categoryList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnCategoryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.category in categoryList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], categoryList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCategoryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.category in categoryList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnCategoryRow(partnerId:Int,
category:String,
metric:String,
day:Int,
minute:DateTime,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnCategoryTableAccessor {
  def query(partnerId : Int, category : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnCategoryRow]]
 def query(partnerId : Int, category : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCategoryRow]]
def query(partnerIdList : List[Int], categoryList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnCategoryRow]]
 def query(partnerIdList : List[Int], categoryList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCategoryRow]]
}