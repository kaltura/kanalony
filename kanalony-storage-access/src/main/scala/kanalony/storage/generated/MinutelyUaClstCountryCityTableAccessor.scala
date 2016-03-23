package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaClstCountryCityTableAccessor extends CassandraTable[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object country extends StringColumn(this)with ClusteringOrder[String] with Ascending
object city extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_clst_country_city"

  def fromRow(row: Row): MinutelyUaClstCountryCityRow = {
    MinutelyUaClstCountryCityRow(
      partner_id(row), 
metric(row), 
minute(row), 
country(row), 
city(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaClstCountryCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.country, entity.country)
.value(_.city, entity.city)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : Int) : SelectQuery[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, countryStart : String, countryEnd : String) : SelectQuery[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, countryStart : String, countryEnd : String, cityStart : String, cityEnd : String) : SelectQuery[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
  }
def query(partnerIdList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, countryStart : String, countryEnd : String) : SelectQuery[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, countryStart : String, countryEnd : String, cityStart : String, cityEnd : String) : SelectQuery[MinutelyUaClstCountryCityTableAccessor, MinutelyUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
  }

}