package com.kaltura.model.cache

import com.datastax.driver.core.{Row, Statement}
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.QueryBuilder._
import QueryBuilder.{eq => eql}
import com.kaltura.core.cassandra.ClusterManager

/**
 * Created by ofirk on 07/02/2016.
 */
trait CacheBase[T, IDType] {
  def cassandraSession = ClusterManager.getSession
  val keySpace = "enrichment_cache"
  val tableName: String
  val idFieldName: String

  def findById(id:IDType): Option[T] = {
    val s:Statement = QueryBuilder
      .select()
      .all()
      .from(keySpace, tableName)
      .where(eql(idFieldName,id))
      .limit(1)
    fromRow(cassandraSession.execute(s).one)
  }

  def fromRow(row: Row): Option[T]
}
