package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsUaPrtnReferrerTableAccessor extends CassandraTable[TensecsUaPrtnReferrerTableAccessor, TensecsUaPrtnReferrerRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object referrer extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_prtn_referrer"

  def fromRow(row: Row): TensecsUaPrtnReferrerRow = {
    TensecsUaPrtnReferrerRow(
      partner_id(row), 
referrer(row), 
metric(row), 
tensecs(row), 
value(row)
    )
  }

  def store(entity: TensecsUaPrtnReferrerRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.referrer, entity.referrer)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, referrer : String, metric : Int) : SelectQuery[TensecsUaPrtnReferrerTableAccessor, TensecsUaPrtnReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, referrer : String, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnReferrerTableAccessor, TensecsUaPrtnReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.referrer eqs referrer)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[Int]) : SelectQuery[TensecsUaPrtnReferrerTableAccessor, TensecsUaPrtnReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], referrerList : List[String], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnReferrerTableAccessor, TensecsUaPrtnReferrerRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.referrer in referrerList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }

}