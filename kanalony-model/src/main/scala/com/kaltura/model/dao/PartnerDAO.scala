package com.kaltura.model.dao

import com.datastax.driver.core.Row
import scalikejdbc._
import com.kaltura.model.entities.Partner

/**
 * Created by ofirk on 18/01/2016.
 */
object PartnerDAO extends DAOBase[Partner, Int] {
  override val tableName = "dim_partners"
  override val idFieldName = "id"
  override def fromRow(row: Row) = if (row != null) Some(Partner(row.getInt("id"), Some(row.getString("secret")))) else None

  def getById(id:Int): Option[Partner] = {
    DB readOnly { implicit session =>
      sql"select partner_id, secret from partners where partner_id = ${id}"
        .map(rs => Partner(rs.int("partner_id"), rs.stringOpt("secret"))).single.apply()
    }
  }

}
