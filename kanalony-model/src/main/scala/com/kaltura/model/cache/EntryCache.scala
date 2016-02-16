package com.kaltura.model.cache

import com.datastax.driver.core.Row
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.kaltura.core.utils.ReadableTimeUnits._
import com.kaltura.model.dao.EntryDAO
import com.kaltura.model.entities.Entry

/**
 * Created by ofirk on 07/02/2016.
 */
sealed class EntryCache extends CacheBase[Entry,String]{
  override val tableName = "dim_entries"
  override val idFieldName = "id"
  override def fromRow(row: Row) = if (row != null) Some(Entry(row.getString(idFieldName), Some(row.getString("categories")))) else None

  val ttl = 1 day

  def getById(partnerId: Int, entryId: String) : Entry = {
    findById(entryId).getOrElse {
      val entry = EntryDAO.getById(partnerId, entryId).getOrElse(Entry(entryId))
      cassandraSession.execute(QueryBuilder
        .insertInto(keySpace, tableName)
        .value("id", entry.id)
        .value("categories", entry.categories.getOrElse(""))
        .using(QueryBuilder.ttl(ttl))
      )
      entry
    }
  }
}

object EntryCache extends EntryCache