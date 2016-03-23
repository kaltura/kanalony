package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnCountryCityClstEntryTableAccessor extends CassandraTable[MinutelyUaPrtnCountryCityClstEntryTableAccessor, MinutelyUaPrtnCountryCityClstEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object city extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_country_city_clst_entry"

  def fromRow(row: Row): MinutelyUaPrtnCountryCityClstEntryRow = {
    MinutelyUaPrtnCountryCityClstEntryRow(
      partner_id(row), 
country(row), 
city(row), 
metric(row), 
minute(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnCountryCityClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.city, entity.city)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, city : String, metric : Int) : SelectQuery[MinutelyUaPrtnCountryCityClstEntryTableAccessor, MinutelyUaPrtnCountryCityClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, country : String, city : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnCountryCityClstEntryTableAccessor, MinutelyUaPrtnCountryCityClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, country : String, city : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[MinutelyUaPrtnCountryCityClstEntryTableAccessor, MinutelyUaPrtnCountryCityClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.city eqs city)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }
def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnCountryCityClstEntryTableAccessor, MinutelyUaPrtnCountryCityClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnCountryCityClstEntryTableAccessor, MinutelyUaPrtnCountryCityClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], countryList : List[String], cityList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[MinutelyUaPrtnCountryCityClstEntryTableAccessor, MinutelyUaPrtnCountryCityClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.city in cityList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }

}