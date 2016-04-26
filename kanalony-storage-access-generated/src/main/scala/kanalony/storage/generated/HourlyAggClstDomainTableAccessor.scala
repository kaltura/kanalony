package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggClstDomainTableAccessor extends CassandraTable[HourlyAggClstDomainTableAccessor, HourlyAggClstDomainRow] with RootConnector with IHourlyAggClstDomainTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object domain extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_clst_domain"

  def fromRow(row: Row): HourlyAggClstDomainRow = {
    HourlyAggClstDomainRow(
      partner_id(row), 
metric(row), 
year(row), 
hour(row), 
domain(row), 
value(row)
    )
  }

  def store(entity: HourlyAggClstDomainRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.domain, entity.domain)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, metric : String, year : Int) : Future[List[HourlyAggClstDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, domainStart : String, domainEnd : String) : Future[List[HourlyAggClstDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.domain gte domainStart)
.and(_.domain lt domainEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggClstDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, domainStart : String, domainEnd : String) : Future[List[HourlyAggClstDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.domain gte domainStart)
.and(_.domain lt domainEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggClstDomainRow(partnerId:Int,
metric:String,
year:Int,
hour:DateTime,
domain:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggClstDomainTableAccessor {
  def query(partnerId : Int, metric : String, year : Int) : Future[List[HourlyAggClstDomainRow]]
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstDomainRow]]
 def query(partnerId : Int, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime, domainStart : String, domainEnd : String) : Future[List[HourlyAggClstDomainRow]]
def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggClstDomainRow]]
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstDomainRow]]
 def query(partnerIdList : List[Int], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, domainStart : String, domainEnd : String) : Future[List[HourlyAggClstDomainRow]]
}