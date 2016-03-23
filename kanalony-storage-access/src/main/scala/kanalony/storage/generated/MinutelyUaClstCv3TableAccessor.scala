package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyUaClstCv3TableAccessor extends CassandraTable[MinutelyUaClstCv3TableAccessor, MinutelyUaClstCv3Row] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var3 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_clst_cv3"

  def fromRow(row: Row): MinutelyUaClstCv3Row = {
    MinutelyUaClstCv3Row(
      partner_id(row), 
metric(row), 
minute(row), 
custom_var3(row), 
value(row)
    )
  }

  def store(entity: MinutelyUaClstCv3Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.minute, entity.minute)
.value(_.custom_var3, entity.customVar3)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : Int) : SelectQuery[MinutelyUaClstCv3TableAccessor, MinutelyUaClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaClstCv3TableAccessor, MinutelyUaClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerId : Int, metric : Int, minuteStart : DateTime, minuteEnd : DateTime, customVar3Start : String, customVar3End : String) : SelectQuery[MinutelyUaClstCv3TableAccessor, MinutelyUaClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
  }
def query(partnerIdList : List[Int], metricList : List[Int]) : SelectQuery[MinutelyUaClstCv3TableAccessor, MinutelyUaClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[MinutelyUaClstCv3TableAccessor, MinutelyUaClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partnerIdList : List[Int], metricList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, customVar3Start : String, customVar3End : String) : SelectQuery[MinutelyUaClstCv3TableAccessor, MinutelyUaClstCv3Row, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.custom_var3 gte customVar3Start)
.and(_.custom_var3 lt customVar3End)
  }

}