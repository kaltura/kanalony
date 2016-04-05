package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnDeviceTableAccessor extends CassandraTable[HourlyAggPrtnDeviceTableAccessor, HourlyAggPrtnDeviceRow] with RootConnector with IHourlyAggPrtnDeviceTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object device extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_device"

  def fromRow(row: Row): HourlyAggPrtnDeviceRow = {
    HourlyAggPrtnDeviceRow(
      partner_id(row), 
device(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnDeviceRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.device, entity.device)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, device : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnDeviceRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.device eqs device)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, device : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnDeviceRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.device eqs device)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], deviceList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnDeviceRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.device in deviceList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], deviceList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnDeviceRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.device in deviceList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnDeviceRow(partnerId:Int,
device:Int,
metric:String,
year:Int,
hour:DateTime,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnDeviceTableAccessor {
  def query(partnerId : Int, device : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnDeviceRow]]
 def query(partnerId : Int, device : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnDeviceRow]]
def query(partnerIdList : List[Int], deviceList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnDeviceRow]]
 def query(partnerIdList : List[Int], deviceList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnDeviceRow]]
}