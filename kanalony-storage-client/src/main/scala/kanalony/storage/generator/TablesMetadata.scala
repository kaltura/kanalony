package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/8/2016.
 */

object TablesMetadata {
  val metadata = List(
                    TableMetadata("hourly_user_activity_prtn_entry_clst_country",
                        PrimaryKey(PartitionKey(List(ColumnDefinition("partner_id", ColumnType.Int),
                                                     ColumnDefinition("entry_id", ColumnType.String),
                                                     ColumnDefinition("event_type", ColumnType.Int),
                                                     ColumnDefinition("year", ColumnType.Int))),
                          ClusteringKey(List(ClusteringColumnDefinition("hour", ColumnType.DateTime, OrderBy.Descending),
                                             ClusteringColumnDefinition("country", ColumnType.String)))),
                        List(ColumnDefinition("count", ColumnType.Long))
                     )
                )
}
