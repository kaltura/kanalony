package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class MinutelyAggPrtnDomainClstReferrerTableAccessor extends CassandraTable[MinutelyAggPrtnDomainClstReferrerTableAccessor, MinutelyAggPrtnDomainClstReferrerRow] with RootConnector with IMinutelyAggPrtnDomainClstReferrerTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object domain extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object referrer extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_agg_prtn_domain_clst_referrer"

  def fromRow(row: Row): MinutelyAggPrtnDomainClstReferrerRow = {
    MinutelyAggPrtnDomainClstReferrerRow(
      partner_id(row), 
domain(row), 
metric(row), 
day(row), 
minute(row), 
referrer(row), 
value(row)
    )
  }

  def store(entity: MinutelyAggPrtnDomainClstReferrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.domain, entity.domain)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.minute, entity.minute)
.value(_.referrer, entity.referrer)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, domain : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, domain : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, domain : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, referrerStart : String, referrerEnd : String) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, referrerStart : String, referrerEnd : String) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class MinutelyAggPrtnDomainClstReferrerRow(partnerId:Int,
domain:String,
metric:String,
day:Int,
minute:DateTime,
referrer:String,
value:Long)


import scala.concurrent.Future

trait IMinutelyAggPrtnDomainClstReferrerTableAccessor {
  def query(partnerId : Int, domain : String, metric : String, day : Int) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]]
 def query(partnerId : Int, domain : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]]
 def query(partnerId : Int, domain : String, metric : String, day : Int, minuteStart : DateTime, minuteEnd : DateTime, referrerStart : String, referrerEnd : String) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]]
def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]]
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]]
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], minuteStart : DateTime, minuteEnd : DateTime, referrerStart : String, referrerEnd : String) : Future[List[MinutelyAggPrtnDomainClstReferrerRow]]
}