package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnCountryOsClstBrowserTableAccessor extends CassandraTable[HourlyAggPrtnCountryOsClstBrowserTableAccessor, HourlyAggPrtnCountryOsClstBrowserRow] with RootConnector with IHourlyAggPrtnCountryOsClstBrowserTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object browser extends IntColumn(this)with ClusteringOrder[Int] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_country_os_clst_browser"

  def fromRow(row: Row): HourlyAggPrtnCountryOsClstBrowserRow = {
    HourlyAggPrtnCountryOsClstBrowserRow(
      partner_id(row), 
country(row), 
operating_system(row), 
metric(row), 
year(row), 
hour(row), 
browser(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnCountryOsClstBrowserRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.browser, entity.browser)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.browser gte browserStart)
.and(_.browser lt browserEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnCountryOsClstBrowserRow(partnerId:Int,
country:String,
operatingSystem:Int,
metric:String,
year:Int,
hour:DateTime,
browser:Int,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnCountryOsClstBrowserTableAccessor {
  def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]]
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]]
 def query(partnerId : Int, country : String, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]]
def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]]
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]]
 def query(partnerIdList : List[Int], countryList : List[String], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, browserStart : Int, browserEnd : Int) : Future[List[HourlyAggPrtnCountryOsClstBrowserRow]]
}