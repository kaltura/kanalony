package com.kaltura.model.cache

import com.datastax.driver.core.Row
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.kaltura.model.dao.PartnerDAO
import com.kaltura.model.entities.Partner

/**
 * Created by ofirk on 07/02/2016.
 */
sealed class PartnerCache extends CacheBase[Partner, Int]{

  override val tableName = "dim_partners"
  override val idFieldName = "id"
  override def fromRow(row: Row) = if (row != null) Some(Partner(row.getInt("id"), Some(row.getString("secret")))) else None
  val ttl = 60 * 60 * 24

  def getById(id: Int) : Partner = {
    findById(id).getOrElse {
      val partner = PartnerDAO.getById(id).getOrElse(Partner(id))
      cassandraSession.execute(QueryBuilder
        .insertInto(keySpace, tableName)
        .value("id", partner.id)
        .value("secret", partner.secret.getOrElse("")).using(QueryBuilder.ttl(ttl))
      )
      partner
    }
  }
}

object PartnerCache extends PartnerCache
