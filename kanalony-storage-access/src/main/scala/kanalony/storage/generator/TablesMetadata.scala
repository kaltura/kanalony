package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/8/2016.
 */

object TablesMetadata {
  def metadata = List(
    createTableMetadata("hourly_user_activity_prtn_entry", "((partner,entry,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity", "((partner,metric),minute),count"),
    createTableMetadata("hourly_user_activity", "((partner,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry", "((partner,entry,metric),minute),count"),
    createTableMetadata("minutely_user_activity_clst_entry", "((partner,metric),minute,entry),count"),
    createTableMetadata("hourly_user_activity_clst_entry", "((partner,metric,month),hour,entry),count"),
    createTableMetadata("minutely_user_activity_clst_country", "((partner,metric),minute,country),count"),
    createTableMetadata("hourly_user_activity_clst_country", "((partner,metric,year),hour,country),count"),
    createTableMetadata("minutely_user_activity_prtn_country", "((partner,metric,country),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_country", "((partner,metric,country,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_country", "((partner,entry,metric),minute,country),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_country", "((partner,entry,metric,year),hour,country),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_country", "((partner,entry,country,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_country", "((partner,entry,country,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_country_city", "((partner,metric),minute,country,city),count"),
    createTableMetadata("hourly_user_activity_clst_country_city", "((partner,metric,year),hour,country,city),count"),
    createTableMetadata("minutely_user_activity_prtn_country_city", "((partner,country,city,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_country_city", "((partner,metric,country,city,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_country_city", "((partner,entry,metric),minute,country,city),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_country_city", "((partner,entry,metric,year),hour,country,city),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_country_city", "((partner,entry,country,city,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_country_city", "((partner,entry,metric,country,city,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_device", "((partner,metric),minute,device),count"),
    createTableMetadata("hourly_user_activity_clst_device", "((partner,metric,year),hour,device),count"),
    createTableMetadata("minutely_user_activity_prtn_device", "((partner,device,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_device", "((partner,device,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_device", "((partner,entry,metric),minute,device),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_device", "((partner,entry,metric,year),hour,device),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_device", "((partner,entry,device,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_device", "((partner,entry,device,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_os", "((partner,metric),minute,os),count"),
    createTableMetadata("hourly_user_activity_clst_os", "((partner,metric,year),hour,os),count"),
    createTableMetadata("minutely_user_activity_prtn_os", "((partner,os,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_os", "((partner,os,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_os", "((partner,entry,metric),minute,os),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_os", "((partner,entry,metric,year),hour,os),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_os", "((partner,entry,os,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_os", "((partner,entry,os,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_browser", "((partner,metric),minute,browser),count"),
    createTableMetadata("hourly_user_activity_clst_browser", "((partner,metric,year),hour,browser),count"),
    createTableMetadata("minutely_user_activity_prtn_browser", "((partner,browser,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_browser", "((partner,browser,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_browser", "((partner,entry,metric),minute,browser),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_browser", "((partner,entry,metric,year),hour,browser),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_browser", "((partner,entry,browser,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_browser", "((partner,entry,browser,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_ptrn_device_clst_os", "((partner,device,metric),minute,os),count"),
    createTableMetadata("hourly_user_activity_ptrn_device_clst_os", "((partner,device,metric,year),hour,os),count"),
    createTableMetadata("minutely_user_activity_prtn_device_os", "((partner,device,os,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_device_os", "((partner,device,os,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_device_clst_os", "((partner,entry,device,metric),minute,os),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_device_clst_os", "((partner,entry,device,metric,year),hour,os),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_device_os", "((partner,entry,device,os,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_device_os", "((partner,entry,device,os,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_ptrn_os_clst_browser", "((partner,os,metric),minute,browser),count"),
    createTableMetadata("hourly_user_activity_ptrn_os_clst_browser", "((partner,os,metric,year),hour,browser),count"),
    createTableMetadata("minutely_user_activity_prtn_os_browser", "((partner,os,browser,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_os_browser", "((partner,os,browser,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_os_clst_browser", "((partner,entry,os,metric),minute,browser),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_os_clst_browser", "((partner,entry,os,metric,year),hour,browser),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_os_browser", "((partner,entry,os,browser,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_os_browser", "((partner,entry,os,browser,metric,year),hour),count"),
    createTableMetadata("hourly_user_activity_prtn_country_clst_os_browser", "((partner,country,metric,year),hour,os,browser),count"),
    createTableMetadata("hourly_user_activity_prtn_country_clst_os", "((partner,country,metric,year),hour,os),count"),
    createTableMetadata("hourly_user_activity_prtn_country_clst_browser", "((partner,country,metric,year),hour,browser),count"),
    createTableMetadata("minutely_user_activity_clst_domain", "((partner,metric),minute,domain),count"),
    createTableMetadata("hourly_user_activity_clst_domain", "((partner,metric,year),hour,domain),count"),
    createTableMetadata("minutely_user_activity_prtn_domain", "((partner,domain,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_domain", "((partner,domain,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_domain", "((partner,entry,metric),minute,domain),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_domain", "((partner,entry,metric,year),hour,domain),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_domain", "((partner,entry,domain,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_domain", "((partner,entry,domain,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_referrer", "((partner,metric),minute,referrer),count"),
    createTableMetadata("hourly_user_activity_clst_referrer", "((partner,metric,year),hour,referrer),count"),
    createTableMetadata("minutely_user_activity_prtn_referrer", "((partner,referrer,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_referrer", "((partner,referrer,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_referrer", "((partner,entry,metric),minute,referrer),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_referrer", "((partner,entry,metric,year),hour,referrer),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_referrer", "((partner,entry,referrer,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_referrer", "((partner,entry,referrer,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_ptrn_domain_clst_referrer", "((partner,domain,metric),minute,referrer),count"),
    createTableMetadata("hourly_user_activity_ptrn_domain_clst_referrer", "((partner,domain,metric,year),hour,referrer),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_domain_clst_referrer", "((partner,entry,domain,metric),minute,referrer),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_domain_clst_referrer", "((partner,entry,domain,metric,year),hour,referrer),count"),
    createTableMetadata("minutely_user_activity_clst_application", "((partner,metric),minute,application),count"),
    createTableMetadata("hourly_user_activity_clst_application", "((partner,metric,year),hour,application),count"),
    createTableMetadata("minutely_user_activity_prtn_application", "((partner,application,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_application", "((partner,application,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_application", "((partner,entry,metric),minute,application),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_application", "((partner,entry,metric,year),hour,application),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_application", "((partner,entry,application,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_application", "((partner,entry,application,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_playbackcontext", "((partner,metric),minute,playbackContext),count"),
    createTableMetadata("hourly_user_activity_clst_playbackcontext", "((partner,metric,year),hour,playbackContext),count"),
    createTableMetadata("minutely_user_activity_prtn_playbackcontext", "((partner,playbackContext,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_playbackcontext", "((partner,playbackContext,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_playbackcontext", "((partner,entry,metric),minute,playbackContext),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_playbackcontext", "((partner,entry,metric,year),hour,playbackContext),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_playbackcontext", "((partner,entry,playbackContext,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_playbackcontext", "((partner,entry,playbackContext,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_ptrn_application_clst_playbackcontext", "((partner,application,metric),minute,playbackContext),count"),
    createTableMetadata("hourly_user_activity_ptrn_application_clst_playbackcontext", "((partner,application,metric,year),hour,playbackContext),count"),
    createTableMetadata("minutely_user_activity_prtn_application_playbackcontext", "((partner,application,playbackContext,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_application_playbackcontext", "((partner,application,playbackContext,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_application_clst_playbackcontext", "((partner,entry,application,year),minute,playbackContext),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_application_clst_playbackcontext", "((partner,entry,application,metric,year),hour,playbackContext),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_application_playbackcontext", "((partner,entry,application,playbackContext,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_application_playbackcontext", "((partner,entry,application,playbackContext,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_cf1", "((partner,metric),minute,cf1),count"),
    createTableMetadata("hourly_user_activity_clst_cf1", "((partner,metric,year),hour,cf1),count"),
    createTableMetadata("minutely_user_activity_prtn_cf1", "((partner,cf1,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_cf1", "((partner,cf1,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_cf1", "((partner,entry,metric),minute,cf1),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_cf1", "((partner,entry,metric,year),hour,cf1),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_cf1", "((partner,entry,cf1,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_cf1", "((partner,entry,cf1,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_cf2", "((partner,metric),minute,cf2),count"),
    createTableMetadata("hourly_user_activity_clst_cf2", "((partner,metric,year),hour,cf2),count"),
    createTableMetadata("minutely_user_activity_prtn_cf2", "((partner,cf2,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_cf2", "((partner,cf2,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_cf2", "((partner,entry,metric),minute,cf2),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_cf2", "((partner,entry,metric,year),hour,cf2),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_cf2", "((partner,entry,cf2,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_cf2", "((partner,entry,cf2,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_clst_cf3", "((partner,metric),minute,cf3),count"),
    createTableMetadata("hourly_user_activity_clst_cf3", "((partner,metric,year),hour,cf3),count"),
    createTableMetadata("minutely_user_activity_prtn_cf3", "((partner,cf3,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_cf3", "((partner,cf3,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_clst_cf3", "((partner,entry,metric),minute,cf3),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_clst_cf3", "((partner,entry,metric,year),hour,cf3),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_cf3", "((partner,entry,cf3,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_cf3", "((partner,entry,cf3,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_cf1_clst_cf2", "((partner,cf1,metric),minute,cf2),count"),
    createTableMetadata("hourly_user_activity_prtn_cf1_clst_cf2", "((partner,cf1,metric,year),hour,cf2),count"),
    createTableMetadata("minutely_user_activity_prtn_cf1_cf2", "((partner,cf1,cf2,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_cf1_cf2", "((partner,cf1,cf2,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_cf1_clst_cf2", "((partner,entry,cf1,metric),minute,cf2),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_cf1_clst_cf2", "((partner,entry,cf1,metric,year),hour,cf2),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_cf1_cf2", "((partner,entry,cf1,cf2,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_cf1_cf2", "((partner,entry,cf1,cf2,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_cf1_cf2_clst_cf3", "((partner,cf1,cf2,metric),minute,cf3),count"),
    createTableMetadata("hourly_user_activity_prtn_cf1_cf2_clst_cf3", "((partner,cf1,cf2,metric,year),hour,cf3),count"),
    createTableMetadata("minutely_user_activity_prtn_cf1_cf2_cf3", "((partner,cf1,cf2,cf3,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_cf1_cf2_cf3", "((partner,cf1,cf2,cf3,metric,year),hour),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_cf1_cf2_clst_cf3", "((partner,entry,cf1,cf2,metric),minute,cf3),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_cf1_cf2_clst_cf3", "((partner,entry,cf1,cf2,metric,year),hour,cf3),count"),
    createTableMetadata("minutely_user_activity_prtn_entry_cf1_cf2_cf3", "((partner,entry,cf1,cf2,cf3,metric),minute),count"),
    createTableMetadata("hourly_user_activity_prtn_entry_cf1_cf2_cf3", "((partner,entry,cf1,cf2,cf3,metric,year),hour),count")
  )

  def createColumnDefinition(s: String): IColumnDefinition = s match {
    case "partner" => ColumnDefinition("partner_id", ColumnType.Int)
    case "entry" => ColumnDefinition("entry_id", ColumnType.String)
    case "metric" => ColumnDefinition("metric", ColumnType.Int)
    case "year" => ColumnDefinition("year", ColumnType.Int)
    case "month" => ColumnDefinition("month", ColumnType.Int)
    case "country" => ColumnDefinition("country", ColumnType.String)
    case "city" => ColumnDefinition("city", ColumnType.String)
    case "os" => ColumnDefinition("os", ColumnType.String)
    case "browser" => ColumnDefinition("browser", ColumnType.String)
    case "device" => ColumnDefinition("device", ColumnType.String)
    case "domain" => ColumnDefinition("domain", ColumnType.String)
    case "referrer" => ColumnDefinition("referrer", ColumnType.String)
    case "application" => ColumnDefinition("application", ColumnType.String)
    case "cf1" => ColumnDefinition("cf1", ColumnType.String)
    case "cf2" => ColumnDefinition("cf2", ColumnType.String)
    case "cf3" => ColumnDefinition("cf3", ColumnType.String)
    case "playbackContext" => ColumnDefinition("playbackContext", ColumnType.String)
    case "count" => ColumnDefinition("count", ColumnType.Long)
  }

  def createClusteringColumnDefinition(s: String): IClusteringColumnDefinition = s match {
    case "hour" => ClusteringColumnDefinition("hour", ColumnType.DateTime, OrderBy.Descending)
    case "minute" => ClusteringColumnDefinition("minute", ColumnType.DateTime, OrderBy.Descending)
    case "entry" => ClusteringColumnDefinition("entry_id", ColumnType.String)
    case "country" => ClusteringColumnDefinition("country", ColumnType.String)
    case "city" => ClusteringColumnDefinition("city", ColumnType.String)
    case "device" => ClusteringColumnDefinition("device", ColumnType.String)
    case "browser" => ClusteringColumnDefinition("browser", ColumnType.String)
    case "os" => ClusteringColumnDefinition("os", ColumnType.String)
    case "domain" => ClusteringColumnDefinition("domain", ColumnType.String)
    case "referrer" => ClusteringColumnDefinition("referrer", ColumnType.String)
    case "application" => ClusteringColumnDefinition("application", ColumnType.String)
    case "playbackContext" => ClusteringColumnDefinition("playbackContext", ColumnType.String)
    case "cf1" => ClusteringColumnDefinition("cf1", ColumnType.String)
    case "cf2" => ClusteringColumnDefinition("cf2", ColumnType.String)
    case "cf3" => ClusteringColumnDefinition("cf3", ColumnType.String)
  }

  def createPartitionKey(partitionKeyColumns: String) : PartitionKey = {
    PartitionKey(partitionKeyColumns.split(",").map(createColumnDefinition(_)).toList)
  }

  def createClusteringKey(clusteringColumns: String) : ClusteringKey = {
    ClusteringKey(clusteringColumns.split(",").map(createClusteringColumnDefinition(_)).toList)
  }

  def createAdditionalColumns(additionalColumns: String) : List[IColumnDefinition] = {
    additionalColumns.split(",").map(createColumnDefinition(_)).toList
  }

  def createTableMetadata(tableName : String, tableMetadata : String) : TableMetadata = {
    val tableMetadataPattern = """\(\((.*)\),?(.*)\),?(.*)""".r
    tableMetadata match {
      case tableMetadataPattern(partitionKeyColumns, clusteringColumns, additionalColumns) => {
        val partitionKey = createPartitionKey(partitionKeyColumns)
        val clusteringKey = createClusteringKey(clusteringColumns)
        val primaryKey = PrimaryKey(partitionKey, clusteringKey)
        val additionalCols = createAdditionalColumns(additionalColumns)
        TableMetadata(tableName, primaryKey, additionalCols)
      }
    }
  }
}
