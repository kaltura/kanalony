package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnReferrerClstEntryTableAccessor extends CassandraTable[HourlyUaPrtnReferrerClstEntryTableAccessor, HourlyUaPrtnReferrerClstEntryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object referrer extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object metric extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object entry_id extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_referrer_clst_entry"

  def fromRow(row: Row): HourlyUaPrtnReferrerClstEntryRow = {
    HourlyUaPrtnReferrerClstEntryRow(
      partner_id(row), 
referrer(row), 
year(row), 
metric(row), 
hour(row), 
entry_id(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnReferrerClstEntryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.referrer, entity.referrer)
.value(_.year, entity.year)
.value(_.metric, entity.metric)
.value(_.hour, entity.hour)
.value(_.entry_id, entity.entryId)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, referrer : String, year : Int, metric : Int) : SelectQuery[HourlyUaPrtnReferrerClstEntryTableAccessor, HourlyUaPrtnReferrerClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.year eqs year)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, referrer : String, year : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnReferrerClstEntryTableAccessor, HourlyUaPrtnReferrerClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, referrer : String, year : Int, metric : Int, hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[HourlyUaPrtnReferrerClstEntryTableAccessor, HourlyUaPrtnReferrerClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.year eqs year)
.and(_.metric eqs metric)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }
def query(partnerIdList : List[Int], referrerList : List[String], yearList : List[Int], metricList : List[Int]) : SelectQuery[HourlyUaPrtnReferrerClstEntryTableAccessor, HourlyUaPrtnReferrerClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.year in yearList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], referrerList : List[String], yearList : List[Int], metricList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnReferrerClstEntryTableAccessor, HourlyUaPrtnReferrerClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], referrerList : List[String], yearList : List[Int], metricList : List[Int], hourStart : DateTime, hourEnd : DateTime, entryIdStart : String, entryIdEnd : String) : SelectQuery[HourlyUaPrtnReferrerClstEntryTableAccessor, HourlyUaPrtnReferrerClstEntryRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.year in yearList)
.and(_.metric in metricList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.entry_id gte entryIdStart)
.and(_.entry_id lt entryIdEnd)
  }

}