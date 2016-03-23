package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnCv1ClstCv2TableAccessor extends CassandraTable[HourlyUaPrtnCv1ClstCv2TableAccessor, HourlyUaPrtnCv1ClstCv2Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var1 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var2 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_cv1_clst_cv2"

  def fromRow(row: Row): HourlyUaPrtnCv1ClstCv2Row = {
    HourlyUaPrtnCv1ClstCv2Row(
      partner_id(row), 
custom_var1(row), 
metric(row), 
year(row), 
hour(row), 
custom_var2(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnCv1ClstCv2Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var1, entity.customVar1)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.custom_var2, entity.customVar2)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar1 : String, metric : Int, year : Int) : SelectQuery[HourlyUaPrtnCv1ClstCv2TableAccessor, HourlyUaPrtnCv1ClstCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, customVar1 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnCv1ClstCv2TableAccessor, HourlyUaPrtnCv1ClstCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, customVar1 : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : SelectQuery[HourlyUaPrtnCv1ClstCv2TableAccessor, HourlyUaPrtnCv1ClstCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var1 eqs customVar1)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var2 gte customVar2Start)
.and(_.custom_var2 lt customVar2End)
  }
def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPrtnCv1ClstCv2TableAccessor, HourlyUaPrtnCv1ClstCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnCv1ClstCv2TableAccessor, HourlyUaPrtnCv1ClstCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], customVar1List : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, customVar2Start : String, customVar2End : String) : SelectQuery[HourlyUaPrtnCv1ClstCv2TableAccessor, HourlyUaPrtnCv1ClstCv2Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var1 in customVar1List)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var2 gte customVar2Start)
.and(_.custom_var2 lt customVar2End)
  }

}