package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/8/2016.
 */

object TablesMetadata {
  def metadata = List(
    createTableMetadata("hourly_ua_prtn_entry", "((partner,entry,metric,month),hour),value"),
    createTableMetadata("minutely_ua_", "((partner,metric),minute),value"),
    createTableMetadata("hourly_ua_", "((partner,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry", "((partner,entry,day,metric),minute),value"),
    createTableMetadata("minutely_ua_clst_entry", "((partner,day,metric),minute,entry),value"),
    createTableMetadata("hourly_ua_clst_entry", "((partner,metric,month),hour,entry),value"),
    createTableMetadata("minutely_ua_clst_country", "((partner,metric),minute,country),value"),
    createTableMetadata("hourly_ua_clst_country", "((partner,metric,year),hour,country),value"),
    createTableMetadata("minutely_ua_prtn_country", "((partner,country,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_country", "((partner,country,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_country", "((partner,entry,metric),minute,country),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_country", "((partner,entry,metric,year),hour,country),value"),
    createTableMetadata("minutely_ua_prtn_entry_country", "((partner,entry,country,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_country", "((partner,entry,country,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_country_city", "((partner,metric),minute,country,city),value"),
    createTableMetadata("hourly_ua_clst_country_city", "((partner,metric,year),hour,country,city),value"),
    createTableMetadata("minutely_ua_prtn_country_city", "((partner,country,city,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_country_city", "((partner,country,city,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_country_city", "((partner,entry,metric),minute,country,city),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_country_city", "((partner,entry,metric,year),hour,country,city),value"),
    createTableMetadata("minutely_ua_prtn_entry_country_city", "((partner,entry,country,city,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_country_city", "((partner,entry,country,city,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_device", "((partner,metric),minute,device),value"),
    createTableMetadata("hourly_ua_clst_device", "((partner,metric,year),hour,device),value"),
    createTableMetadata("minutely_ua_prtn_device", "((partner,device,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_device", "((partner,device,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_device", "((partner,entry,metric),minute,device),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_device", "((partner,entry,metric,year),hour,device),value"),
    createTableMetadata("minutely_ua_prtn_entry_device", "((partner,entry,device,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_device", "((partner,entry,device,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_os", "((partner,metric),minute,operating_system),value"),
    createTableMetadata("hourly_ua_clst_os", "((partner,metric,year),hour,operating_system),value"),
    createTableMetadata("minutely_ua_prtn_os", "((partner,operating_system,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_os", "((partner,operating_system,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_os", "((partner,entry,metric),minute,operating_system),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_os", "((partner,entry,metric,year),hour,operating_system),value"),
    createTableMetadata("minutely_ua_prtn_entry_os", "((partner,entry,operating_system,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_os", "((partner,entry,operating_system,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_browser", "((partner,metric),minute,browser),value"),
    createTableMetadata("hourly_ua_clst_browser", "((partner,metric,year),hour,browser),value"),
    createTableMetadata("minutely_ua_prtn_browser", "((partner,browser,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_browser", "((partner,browser,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_browser", "((partner,entry,metric),minute,browser),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_browser", "((partner,entry,metric,year),hour,browser),value"),
    createTableMetadata("minutely_ua_prtn_entry_browser", "((partner,entry,browser,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_browser", "((partner,entry,browser,metric,year),hour),value"),
    createTableMetadata("minutely_ua_ptrn_device_clst_os", "((partner,device,metric),minute,operating_system),value"),
    createTableMetadata("hourly_ua_ptrn_device_clst_os", "((partner,device,metric,year),hour,operating_system),value"),
    createTableMetadata("minutely_ua_prtn_device_os", "((partner,device,operating_system,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_device_os", "((partner,device,operating_system,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_device_clst_os", "((partner,entry,device,metric),minute,operating_system),value"),
    createTableMetadata("hourly_ua_prtn_entry_device_clst_os", "((partner,entry,device,metric,year),hour,operating_system),value"),
    createTableMetadata("minutely_ua_prtn_entry_device_os", "((partner,entry,device,operating_system,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_device_os", "((partner,entry,device,operating_system,metric,year),hour),value"),
    createTableMetadata("minutely_ua_ptrn_os_clst_browser", "((partner,operating_system,metric),minute,browser),value"),
    createTableMetadata("hourly_ua_ptrn_os_clst_browser", "((partner,operating_system,metric,year),hour,browser),value"),
    createTableMetadata("minutely_ua_prtn_os_browser", "((partner,operating_system,browser,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_os_browser", "((partner,operating_system,browser,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_os_clst_browser", "((partner,entry,operating_system,metric),minute,browser),value"),
    createTableMetadata("hourly_ua_prtn_entry_os_clst_browser", "((partner,entry,operating_system,metric,year),hour,browser),value"),
    createTableMetadata("minutely_ua_prtn_entry_os_browser", "((partner,entry,operating_system,browser,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_os_browser", "((partner,entry,operating_system,browser,metric,year),hour),value"),
    createTableMetadata("hourly_ua_prtn_country_clst_os_browser", "((partner,country,metric,year),hour,operating_system,browser),value"),
    createTableMetadata("hourly_ua_prtn_country_clst_os", "((partner,country,metric,year),hour,operating_system),value"),
    createTableMetadata("hourly_ua_prtn_country_clst_browser", "((partner,country,metric,year),hour,browser),value"),
    createTableMetadata("minutely_ua_clst_domain", "((partner,metric),minute,domain),value"),
    createTableMetadata("hourly_ua_clst_domain", "((partner,metric,year),hour,domain),value"),
    createTableMetadata("minutely_ua_prtn_domain", "((partner,domain,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_domain", "((partner,domain,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_domain", "((partner,entry,metric),minute,domain),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_domain", "((partner,entry,metric,year),hour,domain),value"),
    createTableMetadata("minutely_ua_prtn_entry_domain", "((partner,entry,domain,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_domain", "((partner,entry,domain,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_referrer", "((partner,metric),minute,referrer),value"),
    createTableMetadata("hourly_ua_clst_referrer", "((partner,metric,year),hour,referrer),value"),
    createTableMetadata("minutely_ua_prtn_referrer", "((partner,referrer,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_referrer", "((partner,referrer,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_referrer", "((partner,entry,metric),minute,referrer),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_referrer", "((partner,entry,metric,year),hour,referrer),value"),
    createTableMetadata("minutely_ua_prtn_entry_referrer", "((partner,entry,referrer,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_referrer", "((partner,entry,referrer,metric,year),hour),value"),
    createTableMetadata("minutely_ua_ptrn_domain_clst_referrer", "((partner,domain,metric),minute,referrer),value"),
    createTableMetadata("hourly_ua_ptrn_domain_clst_referrer", "((partner,domain,metric,year),hour,referrer),value"),
    createTableMetadata("minutely_ua_prtn_entry_domain_clst_referrer", "((partner,entry,domain,metric),minute,referrer),value"),
    createTableMetadata("hourly_ua_prtn_entry_domain_clst_referrer", "((partner,entry,domain,metric,year),hour,referrer),value"),
    createTableMetadata("minutely_ua_clst_app", "((partner,metric),minute,application),value"),
    createTableMetadata("hourly_ua_clst_app", "((partner,metric,year),hour,application),value"),
    createTableMetadata("minutely_ua_prtn_app", "((partner,application,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_app", "((partner,application,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_app", "((partner,entry,metric),minute,application),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_app", "((partner,entry,metric,year),hour,application),value"),
    createTableMetadata("minutely_ua_prtn_entry_app", "((partner,entry,application,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_app", "((partner,entry,application,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_playback_context", "((partner,metric),minute,playback_context),value"),
    createTableMetadata("hourly_ua_clst_playback_context", "((partner,metric,year),hour,playback_context),value"),
    createTableMetadata("minutely_ua_prtn_playback_context", "((partner,playback_context,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_playback_context", "((partner,playback_context,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_playback_context", "((partner,entry,metric),minute,playback_context),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_playback_context", "((partner,entry,metric,year),hour,playback_context),value"),
    createTableMetadata("minutely_ua_prtn_entry_playback_context", "((partner,entry,playback_context,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_playback_context", "((partner,entry,playback_context,metric,year),hour),value"),
    createTableMetadata("minutely_ua_ptrn_app_clst_playback_context", "((partner,application,metric),minute,playback_context),value"),
    createTableMetadata("hourly_ua_ptrn_app_clst_playback_context", "((partner,application,metric,year),hour,playback_context),value"),
    createTableMetadata("minutely_ua_prtn_app_playback_context", "((partner,application,playback_context,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_app_playback_context", "((partner,application,playback_context,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_app_clst_playback_context", "((partner,entry,application,year),minute,playback_context),value"),
    createTableMetadata("hourly_ua_prtn_entry_app_clst_playback_context", "((partner,entry,application,metric,year),hour,playback_context),value"),
    createTableMetadata("minutely_ua_prtn_entry_app_playback_context", "((partner,entry,application,playback_context,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_app_playback_context", "((partner,entry,application,playback_context,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_cv1", "((partner,metric),minute,custom_var1),value"),
    createTableMetadata("hourly_ua_clst_cv1", "((partner,metric,year),hour,custom_var1),value"),
    createTableMetadata("minutely_ua_prtn_cv1", "((partner,custom_var1,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_cv1", "((partner,custom_var1,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_cv1", "((partner,entry,metric),minute,custom_var1),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_cv1", "((partner,entry,metric,year),hour,custom_var1),value"),
    createTableMetadata("minutely_ua_prtn_entry_cv1", "((partner,entry,custom_var1,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_cv1", "((partner,entry,custom_var1,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_cv2", "((partner,metric),minute,custom_var2),value"),
    createTableMetadata("hourly_ua_clst_cv2", "((partner,metric,year),hour,custom_var2),value"),
    createTableMetadata("minutely_ua_prtn_cv2", "((partner,custom_var2,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_cv2", "((partner,custom_var2,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_cv2", "((partner,entry,metric),minute,custom_var2),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_cv2", "((partner,entry,metric,year),hour,custom_var2),value"),
    createTableMetadata("minutely_ua_prtn_entry_cv2", "((partner,entry,custom_var2,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_cv2", "((partner,entry,custom_var2,metric,year),hour),value"),
    createTableMetadata("minutely_ua_clst_cv3", "((partner,metric),minute,custom_var3),value"),
    createTableMetadata("hourly_ua_clst_cv3", "((partner,metric,year),hour,custom_var3),value"),
    createTableMetadata("minutely_ua_prtn_cv3", "((partner,custom_var3,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_cv3", "((partner,custom_var3,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_clst_cv3", "((partner,entry,metric),minute,custom_var3),value"),
    createTableMetadata("hourly_ua_prtn_entry_clst_cv3", "((partner,entry,metric,year),hour,custom_var3),value"),
    createTableMetadata("minutely_ua_prtn_entry_cv3", "((partner,entry,custom_var3,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_cv3", "((partner,entry,custom_var3,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_cv1_clst_cv2", "((partner,custom_var1,metric),minute,custom_var2),value"),
    createTableMetadata("hourly_ua_prtn_cv1_clst_cv2", "((partner,custom_var1,metric,year),hour,custom_var2),value"),
    createTableMetadata("minutely_ua_prtn_cv1_cv2", "((partner,custom_var1,custom_var2,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_cv1_cv2", "((partner,custom_var1,custom_var2,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_cv1_clst_cv2", "((partner,entry,custom_var1,metric),minute,custom_var2),value"),
    createTableMetadata("hourly_ua_prtn_entry_cv1_clst_cv2", "((partner,entry,custom_var1,metric,year),hour,custom_var2),value"),
    createTableMetadata("minutely_ua_prtn_entry_cv1_cv2", "((partner,entry,custom_var1,custom_var2,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_cv1_cv2", "((partner,entry,custom_var1,custom_var2,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_cv1_cv2_clst_cv3", "((partner,custom_var1,custom_var2,metric),minute,custom_var3),value"),
    createTableMetadata("hourly_ua_prtn_cv1_cv2_clst_cv3", "((partner,custom_var1,custom_var2,metric,year),hour,custom_var3),value"),
    createTableMetadata("minutely_ua_prtn_cv1_cv2_cv3", "((partner,custom_var1,custom_var2,custom_var3,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_cv1_cv2_cv3", "((partner,custom_var1,custom_var2,custom_var3,metric,year),hour),value"),
    createTableMetadata("minutely_ua_prtn_entry_cv1_cv2_clst_cv3", "((partner,entry,custom_var1,custom_var2,metric),minute,custom_var3),value"),
    createTableMetadata("hourly_ua_prtn_entry_cv1_cv2_clst_cv3", "((partner,entry,custom_var1,custom_var2,metric,year),hour,custom_var3),value"),
    createTableMetadata("minutely_ua_prtn_entry_cv1_cv2_cv3", "((partner,entry,custom_var1,custom_var2,custom_var3,metric),minute),value"),
    createTableMetadata("hourly_ua_prtn_entry_cv1_cv2_cv3", "((partner,entry,custom_var1,custom_var2,custom_var3,metric,year),hour),value")
  )

  def createColumnDefinition(s: String): IColumnDefinition = s match {
    case "partner" => ColumnDefinition("partner_id", ColumnType.Int)
    case "entry" => ColumnDefinition("entry_id", ColumnType.String)
    case "metric" => ColumnDefinition("metric", ColumnType.Int)
    case "year" => ColumnDefinition("year", ColumnType.Int)
    case "month" => ColumnDefinition("month", ColumnType.Int)
    case "day" => ColumnDefinition("day", ColumnType.Int)
    case "country" => ColumnDefinition("country", ColumnType.String)
    case "city" => ColumnDefinition("city", ColumnType.String)
    case "operating_system" => ColumnDefinition("operating_system", ColumnType.Int)
    case "browser" => ColumnDefinition("browser", ColumnType.Int)
    case "device" => ColumnDefinition("device", ColumnType.Int)
    case "domain" => ColumnDefinition("domain", ColumnType.String)
    case "referrer" => ColumnDefinition("referrer", ColumnType.String)
    case "application" => ColumnDefinition("application", ColumnType.String)
    case "custom_var1" => ColumnDefinition("custom_var1", ColumnType.String)
    case "custom_var2" => ColumnDefinition("custom_var2", ColumnType.String)
    case "custom_var3" => ColumnDefinition("custom_var3", ColumnType.String)
    case "playback_context" => ColumnDefinition("playback_context", ColumnType.String)
    case "value" => ColumnDefinition("value", ColumnType.Long)
  }

  def createClusteringColumnDefinition(s: String): IClusteringColumnDefinition = s match {
    case "hour" => ClusteringColumnDefinition("hour", ColumnType.DateTime, OrderBy.Descending)
    case "minute" => ClusteringColumnDefinition("minute", ColumnType.DateTime, OrderBy.Descending)
    case "entry" => ClusteringColumnDefinition("entry_id", ColumnType.String)
    case "country" => ClusteringColumnDefinition("country", ColumnType.String)
    case "city" => ClusteringColumnDefinition("city", ColumnType.String)
    case "device" => ClusteringColumnDefinition("device", ColumnType.Int)
    case "browser" => ClusteringColumnDefinition("browser", ColumnType.Int)
    case "operating_system" => ClusteringColumnDefinition("operating_system", ColumnType.Int)
    case "domain" => ClusteringColumnDefinition("domain", ColumnType.String)
    case "referrer" => ClusteringColumnDefinition("referrer", ColumnType.String)
    case "application" => ClusteringColumnDefinition("application", ColumnType.String)
    case "playback_context" => ClusteringColumnDefinition("playback_context", ColumnType.String)
    case "custom_var1" => ClusteringColumnDefinition("custom_var1", ColumnType.String)
    case "custom_var2" => ClusteringColumnDefinition("custom_var2", ColumnType.String)
    case "custom_var3" => ClusteringColumnDefinition("custom_var3", ColumnType.String)
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
