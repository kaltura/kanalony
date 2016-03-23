package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnEntryCountryTableAccessor extends CassandraTable[MinutelyUaPrtnEntryCountryTableAccessor, MinutelyUaPrtnEntryCountryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_country"

  def fromRow(row: Row): MinutelyUaPrtnEntryCountryRow = {
    MinutelyUaPrtnEntryCountryRow(
      partner_id(row), 
entry_id(row), 
country(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnEntryCountryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, country : String, metric : Int) : SelectQuery[MinutelyUaPrtnEntryCountryTableAccessor, MinutelyUaPrtnEntryCountryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.country eqs country)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, entryId : String, country : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryCountryTableAccessor, MinutelyUaPrtnEntryCountryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnEntryCountryTableAccessor, MinutelyUaPrtnEntryCountryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.country in countryList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], countryList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnEntryCountryTableAccessor, MinutelyUaPrtnEntryCountryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.country in countryList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}