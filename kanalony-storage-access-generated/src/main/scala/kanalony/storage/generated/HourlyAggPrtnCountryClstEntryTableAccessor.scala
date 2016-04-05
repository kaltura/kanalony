package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnCountryClstEntryTableAccessor extends CassandraTable[HourlyAggPrtnCountryClstEntryTableAccessor, HourlyAggPrtnCountryClstEntryRow] with RootConnector with IHourlyAggPrtnCountryClstEntryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object month extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_country_clst_entry"

  def fromRow(row: Row): HourlyAggPrtnCountryClstEntryRow = {
    HourlyAggPrtnCountryClstEntryRow(
      partner_id(row), 
country(row), 
month(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnCountryClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.month, entity.month)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, month : Int, metric : String) : Future[List[HourlyAggPrtnCountryClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.month eqs month)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCountryClstEntryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.month eqs month)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnCountryClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.month in monthList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.month in monthList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCountryClstEntryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
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
case class HourlyAggPrtnCountryClstEntryRow(partnerId:Int,
country:String,
month:Int,
metric:String,
hour:DateTime,
entryId:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnCountryClstEntryTableAccessor {
  def query(partnerId : Int, country : String, month : Int, metric : String) : Future[List[HourlyAggPrtnCountryClstEntryRow]]
 def query(partnerId : Int, country : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryClstEntryRow]]
 def query(partnerId : Int, country : String, month : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCountryClstEntryRow]]
def query(partnerIdList : List[Int], countryList : List[String], monthList : List[Int], metricList : List[String]) : Future[List[HourlyAggPrtnCountryClstEntryRow]]
 def query(partnerIdList : List[Int], countryList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryClstEntryRow]]
 def query(partnerIdList : List[Int], countryList : List[String], monthList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : Future[List[HourlyAggPrtnCountryClstEntryRow]]
}