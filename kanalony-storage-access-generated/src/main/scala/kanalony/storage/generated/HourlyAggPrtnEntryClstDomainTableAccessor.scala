package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnEntryClstDomainTableAccessor extends CassandraTable[HourlyAggPrtnEntryClstDomainTableAccessor, HourlyAggPrtnEntryClstDomainRow] with RootConnector with IHourlyAggPrtnEntryClstDomainTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object domain extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_entry_clst_domain"

  def fromRow(row: Row): HourlyAggPrtnEntryClstDomainRow = {
    HourlyAggPrtnEntryClstDomainRow(
      partner_id(row), 
entry_id(row), 
metric(row), 
year(row), 
hour(row), 
domain(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnEntryClstDomainRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.entry_id, entity.entryId)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.domain, entity.domain)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, entryId : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryClstDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, entryId : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, domainStart : String, domainEnd : String) : Future[List[HourlyAggPrtnEntryClstDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.entry_id eqs entryId)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.domain gte domainStart)
.and(_.domain lt domainEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryClstDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, domainStart : String, domainEnd : String) : Future[List[HourlyAggPrtnEntryClstDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.entry_id in entryIdList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.domain gte domainStart)
.and(_.domain lt domainEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnEntryClstDomainRow(partnerId:Int,
entryId:String,
metric:String,
year:Int,
hour:DateTime,
domain:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnEntryClstDomainTableAccessor {
  def query(partnerId : Int, entryId : String, metric : String, year : Int) : Future[List[HourlyAggPrtnEntryClstDomainRow]]
 def query(partnerId : Int, entryId : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstDomainRow]]
 def query(partnerId : Int, entryId : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, domainStart : String, domainEnd : String) : Future[List[HourlyAggPrtnEntryClstDomainRow]]
def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnEntryClstDomainRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnEntryClstDomainRow]]
 def query(partnerIdList : List[Int], entryIdList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, domainStart : String, domainEnd : String) : Future[List[HourlyAggPrtnEntryClstDomainRow]]
}