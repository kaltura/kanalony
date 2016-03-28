package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnCountryClstCityTableAccessor extends CassandraTable[MinutelyUaPrtnCountryClstCityTableAccessor, MinutelyUaPrtnCountryClstCityRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object city extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_country_clst_city"

  def fromRow(row: Row): MinutelyUaPrtnCountryClstCityRow = {
    MinutelyUaPrtnCountryClstCityRow(
      partner_id(row), 
country(row), 
metric(row), 
minute(row), 
city(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnCountryClstCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.city, entity.city)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, metric : Int) : SelectQuery[MinutelyUaPrtnCountryClstCityTableAccessor, MinutelyUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, country : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnCountryClstCityTableAccessor, MinutelyUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, country : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, cityStart : String, cityEnd : String) : SelectQuery[MinutelyUaPrtnCountryClstCityTableAccessor, MinutelyUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
  }
def query(partnerIdList : List[Int], countryList : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnCountryClstCityTableAccessor, MinutelyUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnCountryClstCityTableAccessor, MinutelyUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, cityStart : String, cityEnd : String) : SelectQuery[MinutelyUaPrtnCountryClstCityTableAccessor, MinutelyUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
  }

}