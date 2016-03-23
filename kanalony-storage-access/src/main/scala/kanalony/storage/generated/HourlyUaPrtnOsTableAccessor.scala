package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnOsTableAccessor extends CassandraTable[HourlyUaPrtnOsTableAccessor, HourlyUaPrtnOsRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_os"

  def fromRow(row: Row): HourlyUaPrtnOsRow = {
    HourlyUaPrtnOsRow(
      partner_id(row), 
operating_system(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, operatingSystem : Int, metric : Int, year : Int) : SelectQuery[HourlyUaPrtnOsTableAccessor, HourlyUaPrtnOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnOsTableAccessor, HourlyUaPrtnOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPrtnOsTableAccessor, HourlyUaPrtnOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnOsTableAccessor, HourlyUaPrtnOsRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}