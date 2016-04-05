package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnCountryCityTableAccessor extends CassandraTable[TensecsAggPrtnCountryCityTableAccessor, TensecsAggPrtnCountryCityRow] with RootConnector with ITensecsAggPrtnCountryCityTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object city extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_country_city"

  def fromRow(row: Row): TensecsAggPrtnCountryCityRow = {
    TensecsAggPrtnCountryCityRow(
      partner_id(row), 
country(row), 
city(row), 
metric(row), 
day(row), 
tensecs(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnCountryCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.city, entity.city)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, city : String, metric : String, day : Int) : Future[List[TensecsAggPrtnCountryCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, country : String, city : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnCountryCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnCountryCityRow(partnerId:Int,
country:String,
city:String,
metric:String,
day:Int,
tensecs:DateTime,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnCountryCityTableAccessor {
  def query(partnerId : Int, country : String, city : String, metric : String, day : Int) : Future[List[TensecsAggPrtnCountryCityRow]]
 def query(partnerId : Int, country : String, city : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryCityRow]]
def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnCountryCityRow]]
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnCountryCityRow]]
}