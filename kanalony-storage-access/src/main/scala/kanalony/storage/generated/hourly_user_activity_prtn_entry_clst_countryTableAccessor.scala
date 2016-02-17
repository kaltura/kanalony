package kanalony.storage.generated

import com.websudos.phantom.builder._
import com.websudos.phantom.builder.query._
import com.websudos.phantom.dsl._

import scala.concurrent.Future

abstract class hourly_user_activity_prtn_entry_clst_countryTableAccessor extends CassandraTable[hourly_user_activity_prtn_entry_clst_countryTableAccessor, hourly_user_activity_prtn_entry_clst_countryRow] with RootConnector {

  object partner_id extends IntColumn(this)with PartitionKey[Int]
  object entry_id extends StringColumn(this)with PartitionKey[String]
  object event_type extends IntColumn(this)with PartitionKey[Int]
  object year extends IntColumn(this)with PartitionKey[Int]
  object hour extends DateTimeColumn(this)with ClusteringOrder[DateTime] with Descending
  object country extends StringColumn(this)with ClusteringOrder[String] with Ascending
  object count extends LongColumn(this)


  override def tableName = "hourly_user_activity_prtn_entry_clst_country"

  def fromRow(row: Row): hourly_user_activity_prtn_entry_clst_countryRow = {
    hourly_user_activity_prtn_entry_clst_countryRow(
      partner_id(row),
      entry_id(row),
      event_type(row),
      year(row),
      hour(row),
      country(row),
      count(row)
    )
  }

  def store(entity: hourly_user_activity_prtn_entry_clst_countryRow): Future[ResultSet] = {
    insert.value(_.partner_id, entity.partner_id)
      .value(_.entry_id, entity.entry_id)
      .value(_.event_type, entity.event_type)
      .value(_.year, entity.year)
      .value(_.hour, entity.hour)
      .value(_.country, entity.country)
      .value(_.count, entity.count)

      .future()
  }

  def query(partner_id : Int, entry_id : String, event_type : Int, year : Int) : SelectQuery[hourly_user_activity_prtn_entry_clst_countryTableAccessor, hourly_user_activity_prtn_entry_clst_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
      .and(_.event_type eqs event_type)
      .and(_.year eqs year)
  }
  def query(partner_id : Int, entry_id : String, event_type : Int, year : Int, hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_prtn_entry_clst_countryTableAccessor, hourly_user_activity_prtn_entry_clst_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
      .and(_.event_type eqs event_type)
      .and(_.year eqs year)
      .and(_.hour gte hourStart)
      .and(_.hour lt hourEnd)
  }
  def query(partner_id : Int, entry_id : String, event_type : Int, year : Int, hourStart : DateTime, hourEnd : DateTime, countryStart : String, countryEnd : String) : SelectQuery[hourly_user_activity_prtn_entry_clst_countryTableAccessor, hourly_user_activity_prtn_entry_clst_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id eqs partner_id).and(_.entry_id eqs entry_id)
      .and(_.event_type eqs event_type)
      .and(_.year eqs year)
      .and(_.hour gte hourStart)
      .and(_.hour lt hourEnd)
      .and(_.country gte countryStart)
      .and(_.country lt countryEnd)
  }
  def query(partner_id_list : List[Int], entry_id_list : List[String], event_type_list : List[Int], year_list : List[Int]) : SelectQuery[hourly_user_activity_prtn_entry_clst_countryTableAccessor, hourly_user_activity_prtn_entry_clst_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
      .and(_.event_type in event_type_list)
      .and(_.year in year_list)
  }
  def query(partner_id_list : List[Int], entry_id_list : List[String], event_type_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime) : SelectQuery[hourly_user_activity_prtn_entry_clst_countryTableAccessor, hourly_user_activity_prtn_entry_clst_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
      .and(_.event_type in event_type_list)
      .and(_.year in year_list)
      .and(_.hour gte hourStart)
      .and(_.hour lt hourEnd)
  }
  def query(partner_id_list : List[Int], entry_id_list : List[String], event_type_list : List[Int], year_list : List[Int], hourStart : DateTime, hourEnd : DateTime, countryStart : String, countryEnd : String) : SelectQuery[hourly_user_activity_prtn_entry_clst_countryTableAccessor, hourly_user_activity_prtn_entry_clst_countryRow, Unlimited, Unordered, Unspecified, Chainned] = {
    select.where(_.partner_id in partner_id_list).and(_.entry_id in entry_id_list)
      .and(_.event_type in event_type_list)
      .and(_.year in year_list)
      .and(_.hour gte hourStart)
      .and(_.hour lt hourEnd)
      .and(_.country gte countryStart)
      .and(_.country lt countryEnd)
  }

}