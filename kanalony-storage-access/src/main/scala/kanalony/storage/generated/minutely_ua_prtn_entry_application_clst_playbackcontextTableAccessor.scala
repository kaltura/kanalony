package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor extends CassandraTable[minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_clst_playbackcontextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object entry_id extends StringColumn(this)with PartitionKey[String]
object application extends StringColumn(this)with PartitionKey[String]
object year extends IntColumn(this)with PartitionKey[Int]
object minute extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object playbackContext extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "minutely_ua_prtn_entry_application_clst_playbackcontext"

  def fromRow(row: Row): minutely_ua_prtn_entry_application_clst_playbackcontextRow = {
    minutely_ua_prtn_entry_application_clst_playbackcontextRow(
      partner_id(row), 
entry_id(row), 
application(row), 
year(row), 
minute(row), 
playbackContext(row), 
value(row)
    )
  }

  def store(entity: minutely_ua_prtn_entry_application_clst_playbackcontextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
.value(_.entry_id, entity.entry_id)
.value(_.application, entity.application)
.value(_.year, entity.year)
.value(_.minute, entity.minute)
.value(_.playbackContext, entity.playbackContext)
.value(_.value, entity.value)

      .future()
  }

  def query(partner_id : Int, entry_id : String, application : String, year : Int) : SelectQuery[minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.application eqs application)
.and(_.year eqs year)
  }
 def query(partner_id : Int, entry_id : String, application : String, year : Int, minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.application eqs application)
.and(_.year eqs year)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id : Int, entry_id : String, application : String, year : Int, minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : SelectQuery[minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
.and(_.application eqs application)
.and(_.year eqs year)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.playbackContext gte playbackContextStart)
.and(_.playbackContext lt playbackContextEnd)
  }
def query(partner_id_list : List[Int], entry_id_list : List[String], application_list : List[String], year_list : List[Int]) : SelectQuery[minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.application in application_list)
.and(_.year in year_list)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], application_list : List[String], year_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime) : SelectQuery[minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.application in application_list)
.and(_.year in year_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
  }
 def query(partner_id_list : List[Int], entry_id_list : List[String], application_list : List[String], year_list : List[Int], minuteStart : DateTime, minuteEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : SelectQuery[minutely_ua_prtn_entry_application_clst_playbackcontextTableAccessor, minutely_ua_prtn_entry_application_clst_playbackcontextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
.and(_.application in application_list)
.and(_.year in year_list)
.and(_.minute gte minuteStart)
.and(_.minute lt minuteEnd)
.and(_.playbackContext gte playbackContextStart)
.and(_.playbackContext lt playbackContextEnd)
  }

}