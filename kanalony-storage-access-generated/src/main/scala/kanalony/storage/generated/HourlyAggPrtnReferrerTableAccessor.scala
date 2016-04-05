package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnReferrerTableAccessor extends CassandraTable[HourlyAggPrtnReferrerTableAccessor, HourlyAggPrtnReferrerRow] with RootConnector with IHourlyAggPrtnReferrerTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object referrer extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_referrer"

  def fromRow(row: Row): HourlyAggPrtnReferrerRow = {
    HourlyAggPrtnReferrerRow(
      partner_id(row), 
referrer(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnReferrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.referrer, entity.referrer)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, referrer : String, metric : String, year : Int) : Future[List[HourlyAggPrtnReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, referrer : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnReferrerRow(partnerId:Int,
referrer:String,
metric:String,
year:Int,
hour:DateTime,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnReferrerTableAccessor {
  def query(partnerId : Int, referrer : String, metric : String, year : Int) : Future[List[HourlyAggPrtnReferrerRow]]
 def query(partnerId : Int, referrer : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnReferrerRow]]
def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnReferrerRow]]
 def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnReferrerRow]]
}