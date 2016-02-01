package com.kaltura.model.dao

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.kaltura.core.cassandra.ClusterManager
import com.datastax.driver.core.{Statement, Row}
import QueryBuilder.{eq => eql}
import com.kaltura.model.entities.Partner
import scalikejdbc._

trait DAOBase[T, IDType]{

  def cassandraSession = ClusterManager.getSession
  val keySpace = "schema_tests"
  val tableName: String
  val idFieldName: String

  Class.forName("com.mysql.jdbc.Driver")
  ConnectionPool.singleton("jdbc:mysql://localhost:3306/test", "", "")
  implicit val mysqlSession = AutoSession

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