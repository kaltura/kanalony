package kanalony.storage

//import kanalony.storage.clients._

//import kanalony.storage.generated._

/**
 * Created by elad.benedict on 2/7/2016.
 */
object DbClientFactory {

  val connector = ConnectorFactory.connector

  //object hourly_user_activity_prtn_entry_clst_country extends hourly_user_activity_prtn_entry_clst_countryTableAccessor with connector.Connector

  //val H_UA_PartnerEntry_Country_StorageClient = hourly_user_activity_prtn_entry_clst_country

}
