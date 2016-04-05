package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnEntryClstCountryCityTableAccessor extends CassandraTable[TensecsAggPrtnEntryClstCountryCityTableAccessor, TensecsAggPrtnEntryClstCountryCityRow] with RootConnector with ITensecsAggPrtnEntryClstCountryCityTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object country extends StringColumn(this)with ClusteringOrder[String] with Ascending
object city extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_entry_clst_country_city"

  def fromRow(row: Row): TensecsAggPrtnEntryClstCountryCityRow = {
    TensecsAggPrtnEntryClstCountryCityRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
day(row), 
tensecs(row), 
country(row), 
city(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnEntryClstCountryCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.country, entity.country)
.value(_.city, entity.city)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : String, day : Int) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String, cityStart : String, cityEnd : String) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String, cityStart : String, cityEnd : String) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnEntryClstCountryCityRow(partnerId:Int,
entryId:String,
metric:String,
day:Int,
tensecs:DateTime,
country:String,
city:String,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnEntryClstCountryCityTableAccessor {
  def query(partnerId : Int, entryId : String, metric : String, day : Int) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]]
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]]
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]]
 def query(partnerId : Int, entryId : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String, cityStart : String, cityEnd : String) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String, cityStart : String, cityEnd : String) : Future[List[TensecsAggPrtnEntryClstCountryCityRow]]
}