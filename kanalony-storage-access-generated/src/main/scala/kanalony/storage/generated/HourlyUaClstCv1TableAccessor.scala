package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaClstCv1TableAccessor extends CassandraTable[HourlyUaClstCv1TableAccessor, HourlyUaClstCv1Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var1 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_clst_cv1"

  def fromRow(row: Row): HourlyUaClstCv1Row = {
    HourlyUaClstCv1Row(
      partner_id(row), 
year(row), 
metric(row), 
hour(row), 
custom_var1(row), 
value(row)
    )
  }

  def store(entity: HourlyUaClstCv1Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.year, entity.year)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.custom_var1, entity.customVar1)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, year : Int, metric : Int) : SelectQuery[HourlyUaClstCv1TableAccessor, HourlyUaClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.year eqs year)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, year : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaClstCv1TableAccessor, HourlyUaClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, year : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime, customVar1Start : String, customVar1End : String) : SelectQuery[HourlyUaClstCv1TableAccessor, HourlyUaClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var1 gte customVar1Start)
.and(_.custom_var1 lt customVar1End)
  }
def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[Int]) : SelectQuery[HourlyUaClstCv1TableAccessor, HourlyUaClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.year in yearList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaClstCv1TableAccessor, HourlyUaClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[Int], hourStart : DateTime, hourEnd : DateTime, customVar1Start : String, customVar1End : String) : SelectQuery[HourlyUaClstCv1TableAccessor, HourlyUaClstCv1Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var1 gte customVar1Start)
.and(_.custom_var1 lt customVar1End)
  }

}