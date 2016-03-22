package kanalony.storage.generator

/**
 * Created by elad.benedict on 3/2/2016.
 */
object ColumnNames extends Enumeration {
  val partner_id, entry_id, metric, year, month, day, country, city, operating_system,
  browser, device, domain, referrer, application, custom_var1, custom_var2, custom_var3,
  playback_context, value, hour, minute, tensecs, category = Value

}
