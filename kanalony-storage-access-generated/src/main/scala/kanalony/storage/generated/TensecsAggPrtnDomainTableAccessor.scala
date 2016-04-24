package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnDomainTableAccessor extends CassandraTable[TensecsAggPrtnDomainTableAccessor, TensecsAggPrtnDomainRow] with RootConnector with ITensecsAggPrtnDomainTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object domain extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_domain"

  def fromRow(row: Row): TensecsAggPrtnDomainRow = {
    TensecsAggPrtnDomainRow(
      partner_id(row), 
domain(row), 
metric(row), 
day(row), 
tensecs(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnDomainRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.domain, entity.domain)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, domain : String, metric : String, day : Int) : Future[List[TensecsAggPrtnDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, domain : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnDomainRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.domain eqs domain)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnDomainRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.domain in domainList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnDomainRow(partnerId:Int,
domain:String,
metric:String,
day:Int,
tensecs:DateTime,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnDomainTableAccessor {
  def query(partnerId : Int, domain : String, metric : String, day : Int) : Future[List[TensecsAggPrtnDomainRow]]
 def query(partnerId : Int, domain : String, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnDomainRow]]
def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnDomainRow]]
 def query(partnerIdList : List[Int], domainList : List[String], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnDomainRow]]
}