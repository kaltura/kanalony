package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggPrtnCategoryTableAccessor extends CassandraTable[HourlyAggPrtnCategoryTableAccessor, HourlyAggPrtnCategoryRow] with RootConnector with IHourlyAggPrtnCategoryTableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object category extends StringColumn(this)with PartitionKey[String]
object metric extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_prtn_category"

  def fromRow(row: Row): HourlyAggPrtnCategoryRow = {
    HourlyAggPrtnCategoryRow(
      partner_id(row), 
category(row), 
metric(row), 
year(row), 
hour(row), 
value(row)
    )
  }

  def store(entity: HourlyAggPrtnCategoryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.category, entity.category)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, category : String, metric : String, year : Int) : Future[List[HourlyAggPrtnCategoryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.category eqs category)
.and(_.metric eqs metric)
.and(_.year eqs year)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, category : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCategoryRow]] = {
    select.where(_.partner_id eqs partnerId).and(_.category eqs category)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], categoryList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnCategoryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.category in categoryList)
.and(_.metric in metricList)
.and(_.year in yearList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], categoryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCategoryRow]] = {
    select.where(_.partner_id in partnerIdList).and(_.category in categoryList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggPrtnCategoryRow(partnerId:Int,
category:String,
metric:String,
year:Int,
hour:DateTime,
value:Long)


import scala.concurrent.Future

trait IHourlyAggPrtnCategoryTableAccessor {
  def query(partnerId : Int, category : String, metric : String, year : Int) : Future[List[HourlyAggPrtnCategoryRow]]
 def query(partnerId : Int, category : String, metric : String, year : Int, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCategoryRow]]
def query(partnerIdList : List[Int], categoryList : List[String], metricList : List[String], yearList : List[Int]) : Future[List[HourlyAggPrtnCategoryRow]]
 def query(partnerIdList : List[Int], categoryList : List[String], metricList : List[String], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggPrtnCategoryRow]]
}