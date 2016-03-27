package kanalony.storage.generator

import java.io.File

import kanalony.storage.utils.fs

/**
 * Created by elad.benedict on 3/27/2016.
 */
object CassandraCqlGeneratorDriver {

  def main (args: Array[String]) {

    val keySpaceName = "kanalony_user_activity"
    val keyspaceCreation = s"CREATE KEYSPACE IF NOT EXISTS ${keySpaceName} WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;"

    val tableNamePlaceholder = "%TABLE_NAME%"
    val columnDefsPlaceholder = "%COLUMN_DEFS%"
    val partitionKeyColsPlaceholder = "%PARTITION_KEY_COLS%"
    val clusteringColsPlaceholder = "%CLUSTERING_KEY_COLS%"
    val timeColPlaceholder = "%TIME_COL%"
    val ttlPlaceholder = "%TTL%"
    val gcGracePlaceholder = "%GC_GRACE_SECS%"

    val tableCreationTemplate = s"""CREATE TABLE IF NOT EXISTS ${keySpaceName}.%TABLE_NAME% (
                                  |    %COLUMN_DEFS%
                                  |    PRIMARY KEY ((%PARTITION_KEY_COLS%), %CLUSTERING_KEY_COLS%)
                                  |) WITH CLUSTERING ORDER BY (%TIME_COL% DESC)
                                  |  AND default_time_to_live=%TTL%
                                  |  AND gc_grace_seconds=%GC_GRACE_SECS%"""


    def getCassandraType(typeName: ColumnType.Value) : String = typeName match {
      case ColumnType.DateTime => "timestamp"
      case ColumnType.Int => "int"
      case ColumnType.Long => "bigint"
      case ColumnType.String => "text"
    }

    def getTtl(typeName: ColumnNames.Value) : String = typeName match {
      case ColumnNames.minute => "15552000"
      case ColumnNames.tensecs => "604800"
      case _=> "0"
    }

    def getGracePeriod() = "43200"

    val tables = TablesMetadata.metadata.map(tm => {
        tableCreationTemplate
          .replace(tableNamePlaceholder, tm.tableName)
          .replace(columnDefsPlaceholder, tm.columns.map(c => s"${c.name} ${getCassandraType(c.typeName)}").mkString("", ",\n\t", ","))
          .replace(partitionKeyColsPlaceholder, tm.primaryKey.pk.columns.map(_.name).mkString(","))
          .replace(clusteringColsPlaceholder, tm.primaryKey.ck.columns.map(_.name).mkString(","))
          .replace(timeColPlaceholder, tm.primaryKey.ck.columns.head.name.toString)
          .replace(ttlPlaceholder, getTtl(tm.primaryKey.ck.columns.head.name))
          .replace(gcGracePlaceholder, getGracePeriod())
    })

    fs.printToFile(new File("kanalony_user_activity_tables.cql"))(p=>(p.write(tables.mkString("\n\n").stripMargin)))
    fs.printToFile(new File("kanalony_user_activity_keyspace.cql"))(p=>(p.write(keyspaceCreation)))
  }
}
