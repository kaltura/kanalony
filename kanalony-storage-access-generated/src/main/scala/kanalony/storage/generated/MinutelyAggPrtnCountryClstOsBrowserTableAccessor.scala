package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnCountryClstOsBrowserTableAccessor extends CassandraTable[MinutelyAggPrtnCountryClstOsBrowserTableAccessor, MinutelyAggPrtnCountryClstOsBrowserRow] with RootConnector with IMinutelyAggPrtnCountryClstOsBrowserTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object operating_system extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_country_clst_os_browser"

  def fromRow(row: Row): MinutelyAggPrtnCountryClstOsBrowserRow = {
    MinutelyAggPrtnCountryClstOsBrowserRow(
      partner_id(row), 
country(row), 
metric(row), 
day(row), 
minute(row), 
operating_system(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnCountryClstOsBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.operating_system, entity.operatingSystem)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int, browserStart : Int, browserEnd : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int, browserStart : Int, browserEnd : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.operating_system gte operatingSystemStart)
.and(_.operating_system lt operatingSystemEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnCountryClstOsBrowserRow(partnerId:Int,
country:String,
metric:String,
day:Int,
minute:DateTime,
operatingSystem:Int,
browser:Int,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnCountryClstOsBrowserTableAccessor {
  def query(partnerId : Int, country : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]]
 def query(partnerId : Int, country : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]]
 def query(partnerId : Int, country : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]]
 def query(partnerId : Int, country : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int, browserStart : Int, browserEnd : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]]
def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]]
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]]
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]]
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, operatingSystemStart : Int, operatingSystemEnd : Int, browserStart : Int, browserEnd : Int) : Future[List[MinutelyAggPrtnCountryClstOsBrowserRow]]
}