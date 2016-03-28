package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsUaPrtnCountryClstCityTableAccessor extends CassandraTable[TensecsUaPrtnCountryClstCityTableAccessor, TensecsUaPrtnCountryClstCityRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object country extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object city extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_prtn_country_clst_city"

  def fromRow(row: Row): TensecsUaPrtnCountryClstCityRow = {
    TensecsUaPrtnCountryClstCityRow(
      partner_id(row), 
country(row), 
metric(row), 
tensecs(row), 
city(row), 
value(row)
    )
  }

  def store(entity: TensecsUaPrtnCountryClstCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.country, entity.country)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.city, entity.city)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, country : String, metric : Int) : SelectQuery[TensecsUaPrtnCountryClstCityTableAccessor, TensecsUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, country : String, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnCountryClstCityTableAccessor, TensecsUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerId : Int, country : String, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime, cityStart : String, cityEnd : String) : SelectQuery[TensecsUaPrtnCountryClstCityTableAccessor, TensecsUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.country eqs country)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
  }
def query(partnerIdList : List[Int], countryList : List[String], metricList : List[Int]) : SelectQuery[TensecsUaPrtnCountryClstCityTableAccessor, TensecsUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnCountryClstCityTableAccessor, TensecsUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerIdList : List[Int], countryList : List[String], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, cityStart : String, cityEnd : String) : SelectQuery[TensecsUaPrtnCountryClstCityTableAccessor, TensecsUaPrtnCountryClstCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.country in countryList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
  }

}