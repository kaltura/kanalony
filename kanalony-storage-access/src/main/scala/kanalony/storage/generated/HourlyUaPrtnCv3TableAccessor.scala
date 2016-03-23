package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnCv3TableAccessor extends CassandraTable[HourlyUaPrtnCv3TableAccessor, HourlyUaPrtnCv3Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var3 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_cv3"

  def fromRow(row: Row): HourlyUaPrtnCv3Row = {
    HourlyUaPrtnCv3Row(
      partner_id(row), 
custom_var3(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnCv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var3, entity.customVar3)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar3 : String, metric : Int, year : Int) : SelectQuery[HourlyUaPrtnCv3TableAccessor, HourlyUaPrtnCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, customVar3 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnCv3TableAccessor, HourlyUaPrtnCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
def query(partnerIdList : List[Int], customVar3List : List[String], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPrtnCv3TableAccessor, HourlyUaPrtnCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], customVar3List : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnCv3TableAccessor, HourlyUaPrtnCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }

}