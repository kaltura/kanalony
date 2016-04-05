package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnOsClstBrowserTableAccessor extends CassandraTable[MinutelyAggPrtnOsClstBrowserTableAccessor, MinutelyAggPrtnOsClstBrowserRow] with RootConnector with IMinutelyAggPrtnOsClstBrowserTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_os_clst_browser"

  def fromRow(row: Row): MinutelyAggPrtnOsClstBrowserRow = {
    MinutelyAggPrtnOsClstBrowserRow(
      partner_id(row), 
operating_system(row), 
metric(row), 
day(row), 
minute(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnOsClstBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int) : Future[List[MinutelyAggPrtnOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[MinutelyAggPrtnOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[MinutelyAggPrtnOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnOsClstBrowserRow(partnerId:Int,
operatingSystem:Int,
metric:String,
day:Int,
minute:DateTime,
browser:Int,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnOsClstBrowserTableAccessor {
  def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int) : Future[List[MinutelyAggPrtnOsClstBrowserRow]]
 def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnOsClstBrowserRow]]
 def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[MinutelyAggPrtnOsClstBrowserRow]]
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnOsClstBrowserRow]]
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnOsClstBrowserRow]]
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[MinutelyAggPrtnOsClstBrowserRow]]
}