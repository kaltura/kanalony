package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnCountryOsClstBrowserTableAccessor extends CassandraTable[TensecsAggPrtnCountryOsClstBrowserTableAccessor, TensecsAggPrtnCountryOsClstBrowserRow] with RootConnector with ITensecsAggPrtnCountryOsClstBrowserTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_country_os_clst_browser"

  def fromRow(row: Row): TensecsAggPrtnCountryOsClstBrowserRow = {
    TensecsAggPrtnCountryOsClstBrowserRow(
      partner_id(row), 
country(row), 
operating_system(row), 
metric(row), 
day(row), 
tensecs(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnCountryOsClstBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, day : Int) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnCountryOsClstBrowserRow(partnerId:Int,
country:String,
operatingSystem:Int,
metric:String,
day:Int,
tensecs:DateTime,
browser:Int,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnCountryOsClstBrowserTableAccessor {
  def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, day : Int) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]]
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]]
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]]
def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]]
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]]
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[TensecsAggPrtnCountryOsClstBrowserRow]]
}