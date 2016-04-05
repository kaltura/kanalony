package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryCountryClstCityTableAccessor extends CassandraTable[HourlyAggPrtnEntryCountryClstCityTableAccessor, HourlyAggPrtnEntryCountryClstCityRow] with RootConnector with IHourlyAggPrtnEntryCountryClstCityTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object city extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_country_clst_city"

  def fromRow(row: Row): HourlyAggPrtnEntryCountryClstCityRow = {
    HourlyAggPrtnEntryCountryClstCityRow(
      partner_id(row), 
entry_id(row), 
country(row), 
metric(row), 
year(row), 
hour(row), 
city(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryCountryClstCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.city, entity.city)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, country : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, country : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, country : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.country in countryList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.country in countryList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.country in countryList)
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
case class HourlyAggPrtnEntryCountryClstCityRow(partnerId:Int,
entryId:String,
country:String,
metric:String,
year:Int,
hour:DateTime,
city:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryCountryClstCityTableAccessor {
  def query(partnerId : Int, entryId : String, country : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]]
 def query(partnerId : Int, entryId : String, country : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]]
 def query(partnerId : Int, entryId : String, country : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[HourlyAggPrtnEntryCountryClstCityRow]]
}