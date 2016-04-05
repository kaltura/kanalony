package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnCountryCityClstEntryTableAccessor extends CassandraTable[HourlyAggPrtnCountryCityClstEntryTableAccessor, HourlyAggPrtnCountryCityClstEntryRow] with RootConnector with IHourlyAggPrtnCountryCityClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object city extends StringColumn(this)with PartitionKey[String]
object month extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_country_city_clst_entry"

  def fromRow(row: Row): HourlyAggPrtnCountryCityClstEntryRow = {
    HourlyAggPrtnCountryCityClstEntryRow(
      partner_id(row), 
country(row), 
city(row), 
month(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnCountryCityClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.city, entity.city)
.value(_.month, entity.month)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, city : String, month : Int, metric : String) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.month eqs month)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, city : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, city : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.month in monthList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnCountryCityClstEntryRow(partnerId:Int,
country:String,
city:String,
month:Int,
metric:String,
hour:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnCountryCityClstEntryTableAccessor {
  def query(partnerId : Int, country : String, city : String, month : Int, metric : String) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]]
 def query(partnerId : Int, country : String, city : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]]
 def query(partnerId : Int, country : String, city : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]]
def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]]
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]]
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCountryCityClstEntryRow]]
}