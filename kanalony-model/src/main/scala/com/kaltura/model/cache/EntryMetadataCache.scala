package com.kaltura.model.cache

import com.datastax.driver.core.Row
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.kaltura.model.dao.EntryMetadataDAO
import com.kaltura.model.entities.EntryMetadata

/**
 * Created by orlylampert on 12/27/16.
 */
sealed class EntryMetadataCache extends CacheBase[EntryMetadata,String]{
  override val tableName = "dim_entries_metadata"
  override val idFieldName = "id"
  override def fromRow(row: Row) = if (row != null) Some(EntryMetadata(row.getString(idFieldName), row.getString("name"))) else None

  def getById(partnerId: Int, entryId: String) : EntryMetadata = {
    findById(entryId).getOrElse {
      val entry = EntryMetadataDAO.getById(partnerId, entryId).getOrElse(EntryMetadata("-1", "Missing Entry Name"))
      cassandraSession.execute(QueryBuilder
        .insertInto(keySpace, tableName)
        .value("id", entry.id)
        .value("name", entry.name)

      )
      entry

    }
  }
}

object EntryMetadataCache extends EntryMetadataCache