package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnBrowserTableAccessor extends CassandraTable[MinutelyAggPrtnBrowserTableAccessor, MinutelyAggPrtnBrowserRow] with RootConnector with IMinutelyAggPrtnBrowserTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object browser extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_browser"

  def fromRow(row: Row): MinutelyAggPrtnBrowserRow = {
    MinutelyAggPrtnBrowserRow(
      partner_id(row), 
browser(row), 
metric(row), 
day(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.browser, entity.browser)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, browser : Int, metric : String, day : Int) : Future[List[MinutelyAggPrtnBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, browser : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.browser eqs browser)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], browserList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], browserList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.browser in browserList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnBrowserRow(partnerId:Int,
browser:Int,
metric:String,
day:Int,
minute:DateTime,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnBrowserTableAccessor {
  def query(partnerId : Int, browser : Int, metric : String, day : Int) : Future[List[MinutelyAggPrtnBrowserRow]]
 def query(partnerId : Int, browser : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnBrowserRow]]
def query(partnerIdList : List[Int], browserList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnBrowserRow]]
 def query(partnerIdList : List[Int], browserList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnBrowserRow]]
}