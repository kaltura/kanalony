package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnDomainClstReferrerTableAccessor extends CassandraTable[TensecsAggPrtnDomainClstReferrerTableAccessor, TensecsAggPrtnDomainClstReferrerRow] with RootConnector with ITensecsAggPrtnDomainClstReferrerTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object domain extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object referrer extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_domain_clst_referrer"

  def fromRow(row: Row): TensecsAggPrtnDomainClstReferrerRow = {
    TensecsAggPrtnDomainClstReferrerRow(
      partner_id(row), 
domain(row), 
metric(row), 
day(row), 
tensecs(row), 
referrer(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnDomainClstReferrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.domain, entity.domain)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.referrer, entity.referrer)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, domain : String, metric : String, day : Int) : Future[List[TensecsAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, domain : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, domain : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, referrerStart : String, referrerEnd : String) : Future[List[TensecsAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, referrerStart : String, referrerEnd : String) : Future[List[TensecsAggPrtnDomainClstReferrerRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
.and(_.referrer gte referrerStart)
.and(_.referrer lt referrerEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnDomainClstReferrerRow(partnerId:Int,
domain:String,
metric:String,
day:Int,
tensecs:DateTime,
referrer:String,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnDomainClstReferrerTableAccessor {
  def query(partnerId : Int, domain : String, metric : String, day : Int) : Future[List[TensecsAggPrtnDomainClstReferrerRow]]
 def query(partnerId : Int, domain : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnDomainClstReferrerRow]]
 def query(partnerId : Int, domain : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime, referrerStart : String, referrerEnd : String) : Future[List[TensecsAggPrtnDomainClstReferrerRow]]
def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnDomainClstReferrerRow]]
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnDomainClstReferrerRow]]
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime, referrerStart : String, referrerEnd : String) : Future[List[TensecsAggPrtnDomainClstReferrerRow]]
}