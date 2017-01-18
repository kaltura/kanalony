package com.kaltura.model.cache

import com.datastax.driver.core.Row
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.kaltura.model.dao.CategoryMetadataDAO
import com.kaltura.model.entities.CategoryMetadata

/**
 * Created by orlylampert on 1/9/17.
 */
sealed class CategoryMetadataCache extends CacheBase[CategoryMetadata,String] {
  override val tableName = "dim_categories_metadata"
  override val idFieldName = "id"

  override def fromRow(row: Row) = if (row != null) Some(CategoryMetadata(row.getString("id"), row.getString("name"))) else None

  def getById(partnerId: Int, categoryId: String) : CategoryMetadata = {
    findById(categoryId).getOrElse {
      val category = CategoryMetadataDAO.getById(partnerId, categoryId).getOrElse(CategoryMetadata("-1", "Missing Category Name"))
      cassandraSession.execute(QueryBuilder
        .insertInto(keySpace, tableName)
        .value("id", category.id)
        .value("name", category.name)

      )
      category
    }
  }
}

object CategoryMetadataCache extends CategoryMetadataCache