package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaPrtnCv3TableAccessor extends CassandraTable[MinutelyUaPrtnCv3TableAccessor, MinutelyUaPrtnCv3Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object custom_var3 extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_cv3"

  def fromRow(row: Row): MinutelyUaPrtnCv3Row = {
    MinutelyUaPrtnCv3Row(
      partner_id(row), 
custom_var3(row), 
metric(row), 
minute(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaPrtnCv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.custom_var3, entity.customVar3)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, customVar3 : String, metric : Int) : SelectQuery[MinutelyUaPrtnCv3TableAccessor, MinutelyUaPrtnCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, customVar3 : String, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnCv3TableAccessor, MinutelyUaPrtnCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.custom_var3 eqs customVar3)
.and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
def query(partnerIdList : List[Int], customVar3List : List[String], metricList : List[Int]) : SelectQuery[MinutelyUaPrtnCv3TableAccessor, MinutelyUaPrtnCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], customVar3List : List[String], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaPrtnCv3TableAccessor, MinutelyUaPrtnCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.custom_var3 in customVar3List)
.and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }

}