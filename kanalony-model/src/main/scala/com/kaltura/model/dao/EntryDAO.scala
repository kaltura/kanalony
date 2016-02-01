package com.kaltura.model.dao

import com.datastax.driver.core.Row
import com.kaltura.model.entities.Entry

/**
 * Created by ofirk on 27/01/2016.
 */
object EntryDAO extends DAOBase[Entry, String] {
  override val tableName = "dim_entries"
  override val idFieldName = "entryId"
  override def fromRow(row: Row) = if (row != null) Some(Entry(row.getString(idFieldName), row.getString("media_type"), Some(row.getString("categories")))) else None
}

