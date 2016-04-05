package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnCountryCityTableAccessor extends CassandraTable[HourlyAggPrtnCountryCityTableAccessor, HourlyAggPrtnCountryCityRow] with RootConnector with IHourlyAggPrtnCountryCityTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object city extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_country_city"

  def fromRow(row: Row): HourlyAggPrtnCountryCityRow = {
    HourlyAggPrtnCountryCityRow(
      partner_id(row), 
country(row), 
city(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnCountryCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.city, entity.city)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, city : String, metric : String, year : Int) : Future[List[HourlyAggPrtnCountryCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, city : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnCountryCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnCountryCityRow(partnerId:Int,
country:String,
city:String,
metric:String,
year:Int,
hour:DateTime,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnCountryCityTableAccessor {
  def query(partnerId : Int, country : String, city : String, metric : String, year : Int) : Future[List[HourlyAggPrtnCountryCityRow]]
 def query(partnerId : Int, country : String, city : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryCityRow]]
def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnCountryCityRow]]
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCountryCityRow]]
}