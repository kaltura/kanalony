package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnCountryClstCityTableAccessor extends CassandraTable[TensecsAggPrtnCountryClstCityTableAccessor, TensecsAggPrtnCountryClstCityRow] with RootConnector with ITensecsAggPrtnCountryClstCityTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object city extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_country_clst_city"

  def fromRow(row: Row): TensecsAggPrtnCountryClstCityRow = {
    TensecsAggPrtnCountryClstCityRow(
      partner_id(row), 
country(row), 
metric(row), 
day(row), 
tensecs(row), 
city(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnCountryClstCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.city, entity.city)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, metric : String, day : Int) : Future[List[TensecsAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[TensecsAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[TensecsAggPrtnCountryClstCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnCountryClstCityRow(partnerId:Int,
country:String,
metric:String,
day:Int,
tensecs:DateTime,
city:String,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnCountryClstCityTableAccessor {
  def query(partnerId : Int, country : String, metric : String, day : Int) : Future[List[TensecsAggPrtnCountryClstCityRow]]
 def query(partnerId : Int, country : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryClstCityRow]]
 def query(partnerId : Int, country : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[TensecsAggPrtnCountryClstCityRow]]
def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnCountryClstCityRow]]
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryClstCityRow]]
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, cityStart : String, cityEnd : String) : Future[List[TensecsAggPrtnCountryClstCityRow]]
}