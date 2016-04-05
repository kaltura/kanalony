package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnOsTableAccessor extends CassandraTable[HourlyAggPrtnOsTableAccessor, HourlyAggPrtnOsRow] with RootConnector with IHourlyAggPrtnOsTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_os"

  def fromRow(row: Row): HourlyAggPrtnOsRow = {
    HourlyAggPrtnOsRow(
      partner_id(row), 
operating_system(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, operatingSystem : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnOsRow(partnerId:Int,
operatingSystem:Int,
metric:String,
year:Int,
hour:DateTime,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnOsTableAccessor {
  def query(partnerId : Int, operatingSystem : Int, metric : String, year : Int) : Future[List[HourlyAggPrtnOsRow]]
 def query(partnerId : Int, operatingSystem : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnOsRow]]
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnOsRow]]
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnOsRow]]
}