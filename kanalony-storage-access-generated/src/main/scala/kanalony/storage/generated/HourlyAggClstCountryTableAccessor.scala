package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggClstCountryTableAccessor extends CassandraTable[HourlyAggClstCountryTableAccessor, HourlyAggClstCountryRow] with RootConnector with IHourlyAggClstCountryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object country extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_clst_country"

  def fromRow(row: Row): HourlyAggClstCountryRow = {
    HourlyAggClstCountryRow(
      partner_id(row), 
metric(row), 
year(row), 
hour(row), 
country(row), 
value(row)
    )
  }

  def store(entity: HourlyAggClstCountryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.country, entity.country)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : String, year : Int) : Future[List[HourlyAggClstCountryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstCountryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, countryStart : String, countryEnd : String) : Future[List[HourlyAggClstCountryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggClstCountryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstCountryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, countryStart : String, countryEnd : String) : Future[List[HourlyAggClstCountryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggClstCountryRow(partnerId:Int,
metric:String,
year:Int,
hour:DateTime,
country:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggClstCountryTableAccessor {
  def query(partnerId : Int, metric : String, year : Int) : Future[List[HourlyAggClstCountryRow]]
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstCountryRow]]
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, countryStart : String, countryEnd : String) : Future[List[HourlyAggClstCountryRow]]
def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggClstCountryRow]]
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstCountryRow]]
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, countryStart : String, countryEnd : String) : Future[List[HourlyAggClstCountryRow]]
}