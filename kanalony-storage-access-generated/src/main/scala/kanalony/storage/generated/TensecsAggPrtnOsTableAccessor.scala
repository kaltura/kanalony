package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsAggPrtnOsTableAccessor extends CassandraTable[TensecsAggPrtnOsTableAccessor, TensecsAggPrtnOsRow] with RootConnector with ITensecsAggPrtnOsTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object operating_system extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object day extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "tensecs_agg_prtn_os"

  def fromRow(row: Row): TensecsAggPrtnOsRow = {
    TensecsAggPrtnOsRow(
      partner_id(row), 
operating_system(row), 
metric(row), 
day(row), 
tensecs(row), 
value(row)
    )
  }

  def store(entity: TensecsAggPrtnOsRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.operating_system, entity.operatingSystem)
.value(_.metric, entity.metric)
.value(_.day, entity.day)
.value(_.tensecs, entity.tensecs)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int) : Future[List[TensecsAggPrtnOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnOsRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.operating_system eqs operatingSystem)
.and(_.metric eqs metric)
.and(_.day eqs day)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnOsRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.operating_system in operatingSystemList)
.and(_.metric in metricList)
.and(_.day in dayList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class TensecsAggPrtnOsRow(partnerId:Int,
operatingSystem:Int,
metric:String,
day:Int,
tensecs:DateTime,
value:Long)


import scala.concurrent.Future

trait ITensecsAggPrtnOsTableAccessor {
  def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int) : Future[List[TensecsAggPrtnOsRow]]
 def query(partnerId : Int, operatingSystem : Int, metric : String, day : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnOsRow]]
def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int]) : Future[List[TensecsAggPrtnOsRow]]
 def query(partnerIdList : List[Int], operatingSystemList : List[Int], metricList : List[String], dayList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : Future[List[TensecsAggPrtnOsRow]]
}