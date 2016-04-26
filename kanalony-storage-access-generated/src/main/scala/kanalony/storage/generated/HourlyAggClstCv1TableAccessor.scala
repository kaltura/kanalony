package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyAggClstCv1TableAccessor extends CassandraTable[HourlyAggClstCv1TableAccessor, HourlyAggClstCv1Row] with RootConnector with IHourlyAggClstCv1TableAccessor {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object metric extends StringColumn(this)with PartitionKey[String]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object custom_var1 extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_agg_clst_cv1"

  def fromRow(row: Row): HourlyAggClstCv1Row = {
    HourlyAggClstCv1Row(
      partner_id(row), 
year(row), 
metric(row), 
hour(row), 
custom_var1(row), 
value(row)
    )
  }

  def store(entity: HourlyAggClstCv1Row): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.year, entity.year)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.custom_var1, entity.customVar1)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, year : Int, metric : String) : Future[List[HourlyAggClstCv1Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.year eqs year)
.and(_.metric eqs metric)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstCv1Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerId : Int, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, customVar1Start : String, customVar1End : String) : Future[List[HourlyAggClstCv1Row]] = {
    select.where(_.partner_id eqs partnerId).and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var1 gte customVar1Start)
.and(_.custom_var1 lt customVar1End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[String]) : Future[List[HourlyAggClstCv1Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.year in yearList)
.and(_.metric in metricList)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstCv1Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }
 def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, customVar1Start : String, customVar1End : String) : Future[List[HourlyAggClstCv1Row]] = {
    select.where(_.partner_id in partnerIdList).and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.custom_var1 gte customVar1Start)
.and(_.custom_var1 lt customVar1End)
    .fetch()(session, scala.concurrent.ExecutionContext.Implicits.global, space)
  }

}

import org.joda.time.DateTime
case class HourlyAggClstCv1Row(partnerId:Int,
year:Int,
metric:String,
hour:DateTime,
customVar1:String,
value:Long)


import scala.concurrent.Future

trait IHourlyAggClstCv1TableAccessor {
  def query(partnerId : Int, year : Int, metric : String) : Future[List[HourlyAggClstCv1Row]]
 def query(partnerId : Int, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstCv1Row]]
 def query(partnerId : Int, year : Int, metric : String, hourStart : DateTime, hourEnd : DateTime, customVar1Start : String, customVar1End : String) : Future[List[HourlyAggClstCv1Row]]
def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[String]) : Future[List[HourlyAggClstCv1Row]]
 def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime) : Future[List[HourlyAggClstCv1Row]]
 def query(partnerIdList : List[Int], yearList : List[Int], metricList : List[String], hourStart : DateTime, hourEnd : DateTime, customVar1Start : String, customVar1End : String) : Future[List[HourlyAggClstCv1Row]]
}