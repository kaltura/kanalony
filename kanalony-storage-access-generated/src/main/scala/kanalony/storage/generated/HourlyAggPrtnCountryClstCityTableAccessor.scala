package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnCountryClstCityTableAccessor extends CassandraTable[HourlyAggPrtnCountryClstCityTableAccessor, HourlyAggPrtnCountryClstCityRow] with RootConnector with IHourlyAggPrtnCountryClstCityTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object city extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_country_clst_city"

  def fromRow(row: Row): HourlyAggPrtnCountryClstCityRow = {
    HourlyAggPrtnCountryClstCityRow(
      partner_id(row), 
country(row), 
metric(row), 
year(row), 
hour(row), 
city(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnCountryClstCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.city, entity.city)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, metric : String, year : Int) : Future[List[HourlyAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[HourlyAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[HourlyAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnCountryClstCityRow(partnerId:Int,
country:String,
metric:String,
year:Int,
hour:DateTime,
city:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnCountryClstCityTableAccessor {
  def query(partnerId : Int, country : String, metric : String, year : Int) : Future[List[HourlyAggPrtnCountryClstCityRow]]
 def query(partnerId : Int, country : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryClstCityRow]]
 def query(partnerId : Int, country : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[HourlyAggPrtnCountryClstCityRow]]
def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnCountryClstCityRow]]
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryClstCityRow]]
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[HourlyAggPrtnCountryClstCityRow]]
}