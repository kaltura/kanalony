package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsUaClstCountryCityTableAccessor extends CassandraTable[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object country extends StringColumn(this)with ClusteringOrder[String] with Ascending
object city extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_clst_country_city"

  def fromRow(row: Row): TensecsUaClstCountryCityRow = {
    TensecsUaClstCountryCityRow(
      partner_id(row), 
metric(row), 
tensecs(row), 
country(row), 
city(row), 
value(row)
    )
  }

  def store(entity: TensecsUaClstCountryCityRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.country, entity.country)
.value(_.city, entity.city)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : Int) : SelectQuery[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
  }
 def query(partnerId : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerId : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String) : SelectQuery[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
  }
 def query(partnerId : Int, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String, cityStart : String, cityEnd : String) : SelectQuery[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
  }
def query(partnerIdList : List[Int], metricList : List[Int]) : SelectQuery[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String) : SelectQuery[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, countryStart : String, countryEnd : String, cityStart : String, cityEnd : String) : SelectQuery[TensecsUaClstCountryCityTableAccessor, TensecsUaClstCountryCityRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.country gte countryStart)
.and(_.country lt countryEnd)
.and(_.city gte cityStart)
.and(_.city lt cityEnd)
  }

}