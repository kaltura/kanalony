package com.kaltura.model.entities

import scalikejdbc.WrappedResultSet
import scalikejdbc._

case class Partner (
                      id: Int,
                      secret: Option[String]=None
                    )

object Partner extends SQLSyntaxSupport[Partner] {
  override val tableName = "partners"
  def apply(rs: WrappedResultSet) = new Partner(rs.int("partner_id"),rs.stringOpt("secret"))
}