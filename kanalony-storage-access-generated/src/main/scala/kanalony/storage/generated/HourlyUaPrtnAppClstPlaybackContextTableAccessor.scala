package kanalony.storage.generated
import com.websudos.phantom.dsl._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.builder._
import shapeless.HNil
import scala.concurrent.Future

abstract class HourlyUaPrtnAppClstPlaybackContextTableAccessor extends CassandraTable[HourlyUaPrtnAppClstPlaybackContextTableAccessor, HourlyUaPrtnAppClstPlaybackContextRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
object application extends StringColumn(this)with PartitionKey[String]
object metric extends IntColumn(this)with PartitionKey[Int]
object year extends IntColumn(this)with PartitionKey[Int]
object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
object playback_context extends StringColumn(this)with ClusteringOrder[String] with Ascending
object value extends LongColumn(this)


  override def tableName = "hourly_ua_prtn_app_clst_playback_context"

  def fromRow(row: Row): HourlyUaPrtnAppClstPlaybackContextRow = {
    HourlyUaPrtnAppClstPlaybackContextRow(
      partner_id(row), 
application(row), 
metric(row), 
year(row), 
hour(row), 
playback_context(row), 
value(row)
    )
  }

  def store(entity: HourlyUaPrtnAppClstPlaybackContextRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partnerId)
.value(_.application, entity.application)
.value(_.metric, entity.metric)
.value(_.year, entity.year)
.value(_.hour, entity.hour)
.value(_.playback_context, entity.playbackContext)
.value(_.value, entity.value)

      .future()
  }

  def query(partnerId : Int, application : String, metric : Int, year : Int) : SelectQuery[HourlyUaPrtnAppClstPlaybackContextTableAccessor, HourlyUaPrtnAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
  }
 def query(partnerId : Int, application : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnAppClstPlaybackContextTableAccessor, HourlyUaPrtnAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerId : Int, application : String, metric : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : SelectQuery[HourlyUaPrtnAppClstPlaybackContextTableAccessor, HourlyUaPrtnAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id eqs partnerId).and(_.application eqs application)
.and(_.metric eqs metric)
.and(_.year eqs year)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
  }
def query(partnerIdList : List[Int], applicationList : List[String], metricList : List[Int], yearList : List[Int]) : SelectQuery[HourlyUaPrtnAppClstPlaybackContextTableAccessor, HourlyUaPrtnAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.year in yearList)
  }
 def query(partnerIdList : List[Int], applicationList : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[HourlyUaPrtnAppClstPlaybackContextTableAccessor, HourlyUaPrtnAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
  }
 def query(partnerIdList : List[Int], applicationList : List[String], metricList : List[Int], yearList : List[Int], hourStart : DateTime, hourEnd : DateTime, playbackContextStart : String, playbackContextEnd : String) : SelectQuery[HourlyUaPrtnAppClstPlaybackContextTableAccessor, HourlyUaPrtnAppClstPlaybackContextRow, Unlimited, Unordered, Unspecified, Chainned, HNil] = {
    select.where(_.partner_id in partnerIdList).and(_.application in applicationList)
.and(_.metric in metricList)
.and(_.year in yearList)
.and(_.hour gte hourStart)
.and(_.hour lt hourEnd)
.and(_.playback_context gte playbackContextStart)
.and(_.playback_context lt playbackContextEnd)
  }

}