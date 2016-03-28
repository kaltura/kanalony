package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class TensecsUaPrtnAppTableAccessor extends CassandraTable[TensecsUaPrtnAppTableAccessor, TensecsUaPrtnAppRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object application extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object tensecs extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object value extends LongColumn(this)


  override def tableName = "tensecs_ua_prtn_app"

  def fromRow(row: Row): TensecsUaPrtnAppRow = {
    TensecsUaPrtnAppRow(
      partner_id(row), 
application(row), 
metric(row), 
tensecs(row), 
value(row)
    )
  }

  def store(entity: TensecsUaPrtnAppRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.application, entity.application)
.value(_.metric, entity.metric)
.value(_.tensecs, entity.tensecs)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, application : String, metric : Int) : SelectQuery[TensecsUaPrtnAppTableAccessor, TensecsUaPrtnAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.metric eqs metric)
  }
 def query(partnerId : Int, application : String, metric : Int, tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnAppTableAccessor, TensecsUaPrtnAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }
def query(partnerIdList : List[Int], applicationList : List[String], metricList : List[Int]) : SelectQuery[TensecsUaPrtnAppTableAccessor, TensecsUaPrtnAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.metric in metricList)
  }
 def query(partnerIdList : List[Int], applicationList : List[String], metricList : List[Int], tensecsStart : DateTime, tensecsEnd : DateTime) : SelectQuery[TensecsUaPrtnAppTableAccessor, TensecsUaPrtnAppRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.tensecs gte tensecsStart)
.and(_.tensecs lt tensecsEnd)
  }

}