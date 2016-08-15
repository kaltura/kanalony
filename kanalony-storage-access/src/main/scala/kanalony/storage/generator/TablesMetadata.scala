package kanalony.storage.generator

/**
 * Created by elad.benedict on 2/8/2016.
 */

object TablesMetadata {
  def metadata = List(
    createTableMetadata("minutely_agg_prtn_cv1_clst_cv2","((partner_id,custom_var1,metric,day),minute,custom_var2),value"),
    createTableMetadata("tensecs_agg_clst_app","((partner_id,metric,day),tensecs,application),value"),
    createTableMetadata("hourly_agg_prtn_country_city","((partner_id,country,city,metric,year),hour),value"),
    createTableMetadata("minutely_agg_clst_country_city","((partner_id,metric,day),minute,country,city),value"),
    createTableMetadata("hourly_agg_prtn_entry","((partner_id,entry_id,month,metric),hour),value"),
    createTableMetadata("tensecs_agg_prtn_country_os_clst_browser","((partner_id,country,operating_system,metric,day),tensecs,browser),value"),
    createTableMetadata("hourly_agg_prtn_domain_clst_referrer","((partner_id,domain,metric,year),hour,referrer),value"),
    createTableMetadata("minutely_agg_prtn_domain","((partner_id,domain,metric,day),minute),value"),
    createTableMetadata("tensecs_agg_prtn_os","((partner_id,operating_system,metric,day),tensecs),value"),
    createTableMetadata("minutely_agg_prtn_app_clst_playbackcontext","((partner_id,application,metric,day),minute,playback_context),value"),
    createTableMetadata("hourly_agg_prtn_app_clst_playbackcontext","((partner_id,application,metric,year),hour,playback_context),value"),
    createTableMetadata("tensecs_agg_prtn_country_clst_os_browser","((partner_id,country,metric,day),tensecs,operating_system,browser),value"),
    createTableMetadata("hourly_agg_prtn_cv1_cv2","((partner_id,custom_var1,custom_var2,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_country","((partner_id,country,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_app","((partner_id,application,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_device_clst_os","((partner_id,device,metric,day),minute,operating_system),value"),
    createTableMetadata("hourly_agg_prtn_device_clst_os","((partner_id,device,metric,year),hour,operating_system),value"),
    createTableMetadata("minutely_agg_prtn_playbackcontext","((partner_id,playback_context,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_country_clst_os","((partner_id,country,metric,day),minute,operating_system),value"),
    createTableMetadata("hourly_agg_prtn_cv1_clst_cv2","((partner_id,custom_var1,year,metric),hour,custom_var2),value"),
    createTableMetadata("hourly_agg_clst_country","((partner_id,metric,year),hour,country),value"),
    createTableMetadata("hourly_agg_prtn_country_clst_browser","((partner_id,country,year,metric),hour,browser),value"),
    createTableMetadata("tensecs_agg_clst_entry","((partner_id,day,metric),tensecs,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_country_os_clst_browser","((partner_id,country,operating_system,metric,year),hour,browser),value"),
    createTableMetadata("hourly_agg_prtn_cv1","((partner_id,custom_var1,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_cv3","((partner_id,custom_var3,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_cv2","((partner_id,custom_var2,year,metric),hour),value"),
    createTableMetadata("hourly_agg_clst_cv1","((partner_id,year,metric),hour,custom_var1),value"),
    createTableMetadata("hourly_agg_clst_cv3","((partner_id,year,metric),hour,custom_var3),value"),
    createTableMetadata("hourly_agg_clst_cv2","((partner_id,year,metric),hour,custom_var2),value"),
    createTableMetadata("minutely_agg_prtn_country_clst_city","((partner_id,country,metric,day),minute,city),value"),
    createTableMetadata("hourly_agg_prtn_referrer","((partner_id,referrer,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_os","((partner_id,operating_system,metric,day),minute),value"),
    createTableMetadata("hourly_agg_clst_device","((partner_id,metric,year),hour,device),value"),
    createTableMetadata("hourly_agg_clst_referrer","((partner_id,metric,year),hour,referrer),value"),
    createTableMetadata("minutely_agg_clst_domain","((partner_id,metric,day),minute,domain),value"),
    createTableMetadata("minutely_agg_prtn_os_clst_browser","((partner_id,operating_system,metric,day),minute,browser),value"),
    createTableMetadata("tensecs_agg_prtn_referrer","((partner_id,referrer,metric,day),tensecs),value"),
    createTableMetadata("hourly_agg_prtn_playbackcontext","((partner_id,playback_context,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_cv1_cv2_clst_cv3","((partner_id,custom_var1,custom_var2,metric,day),minute,custom_var3),value"),
    createTableMetadata("minutely_agg_prtn_country_clst_os_browser","((partner_id,country,metric,day),minute,operating_system,browser),value"),
    createTableMetadata("hourly_agg_prtn_device_os","((partner_id,device,operating_system,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_cv1","((partner_id,custom_var1,metric,day),minute),value"),
    createTableMetadata("minutely_agg_clst_device","((partner_id,metric,day),minute,device),value"),
    createTableMetadata("minutely_agg_prtn_cv3","((partner_id,custom_var3,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_cv2","((partner_id,custom_var2,metric,day),minute),value"),
    createTableMetadata("hourly_agg_prtn_os_browser","((partner_id,operating_system,browser,metric,year),hour),value"),
    createTableMetadata("hourly_agg_prtn_country_clst_os","((partner_id,country,year,metric),hour,operating_system),value"),
    createTableMetadata("minutely_agg_clst_browser","((partner_id,metric,day),minute,browser),value"),
    createTableMetadata("minutely_agg_prtn_cv1_cv2_cv3","((partner_id,custom_var1,custom_var2,custom_var3,metric,day),minute),value"),
    createTableMetadata("minutely_agg_clst_entry","((partner_id,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_country_os_browser","((partner_id,country,operating_system,browser,metric,year),hour),value"),
    createTableMetadata("minutely_agg_clst_country","((partner_id,metric,day),minute,country),value"),
    createTableMetadata("minutely_agg_prtn_device_os","((partner_id,device,operating_system,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_cv1_cv2","((partner_id,custom_var1,custom_var2,metric,day),minute),value"),
    createTableMetadata("hourly_agg_clst_browser","((partner_id,metric,year),hour,browser),value"),
    createTableMetadata("hourly_agg_clst_domain","((partner_id,metric,year),hour,domain),value"),
    createTableMetadata("tensecs_agg_clst_referrer","((partner_id,metric,day),tensecs,referrer),value"),
    createTableMetadata("hourly_agg_clst_app","((partner_id,metric,year),hour,application),value"),
    createTableMetadata("minutely_agg_prtn_country","((partner_id,country,metric,day),minute),value"),
    createTableMetadata("hourly_agg_prtn_cv1_cv2_cv3","((partner_id,custom_var1,custom_var2,custom_var3,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_os_clst_browser","((partner_id,operating_system,metric,year),hour,browser),value"),
    createTableMetadata("tensecs_agg_prtn_country_city","((partner_id,country,city,metric,day),tensecs),value"),
    createTableMetadata("hourly_agg_prtn_cv1_cv2_clst_cv3","((partner_id,custom_var1,custom_var2,year,metric),hour,custom_var3),value"),
    createTableMetadata("hourly_agg_prtn_device","((partner_id,device,metric,year),hour),value"),
    createTableMetadata("hourly_agg_clst_os","((partner_id,metric,year),hour,operating_system),value"),
    createTableMetadata("hourly_agg_clst_entry","((partner_id,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_country_clst_browser","((partner_id,country,metric,day),minute,browser),value"),
    createTableMetadata("tensecs_agg_prtn_country_clst_city","((partner_id,country,metric,day),tensecs,city),value"),
    createTableMetadata("minutely_agg_clst_app","((partner_id,metric,day),minute,application),value"),
    createTableMetadata("tensecs_agg_clst_country_city","((partner_id,metric,day),tensecs,country,city),value"),
    createTableMetadata("minutely_agg_prtn_app_playbackcontext","((partner_id,application,playback_context,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry","((partner_id,entry_id,day,metric),minute),value"),
    createTableMetadata("minutely_agg_prtn_os_browser","((partner_id,operating_system,browser,metric,day),minute),value"),
    createTableMetadata("tensecs_agg_prtn_domain_clst_referrer","((partner_id,domain,metric,day),tensecs,referrer),value"),
    createTableMetadata("minutely_agg_clst_os","((partner_id,metric,day),minute,operating_system),value"),
    createTableMetadata("minutely_agg_prtn_domain_clst_referrer","((partner_id,domain,metric,day),minute,referrer),value"),
    createTableMetadata("minutely_agg_clst_referrer","((partner_id,metric,day),minute,referrer),value"),
    createTableMetadata("hourly_agg_clst_playbackcontext","((partner_id,metric,year),hour,playback_context),value"),
    createTableMetadata("tensecs_agg_clst_domain","((partner_id,metric,day),tensecs,domain),value"),
    createTableMetadata("hourly_agg_prtn_app_playbackcontext","((partner_id,application,playback_context,metric,year),hour),value"),
    createTableMetadata("minutely_agg_clst_playbackcontext","((partner_id,metric,day),minute,playback_context),value"),
    createTableMetadata("hourly_agg_prtn_country_clst_os_browser","((partner_id,country,metric,year),hour,operating_system,browser),value"),
    createTableMetadata("tensecs_agg_prtn_app","((partner_id,application,metric,day),tensecs),value"),
    createTableMetadata("minutely_agg_prtn_device","((partner_id,device,metric,day),minute),value"),
    createTableMetadata("tensecs_agg_prtn_entry","((partner_id,entry_id,day,metric),tensecs),value"),
    createTableMetadata("minutely_agg_prtn_country_os_clst_browser","((partner_id,country,operating_system,metric,day),minute,browser),value"),
    createTableMetadata("tensecs_agg_clst_country","((partner_id,metric,day),tensecs,country),value"),
    createTableMetadata("minutely_agg_prtn_referrer","((partner_id,referrer,metric,day),minute),value"),
    createTableMetadata("hourly_agg","((partner_id,metric,year),hour),value"),
    createTableMetadata("hourly_agg_prtn_domain","((partner_id,domain,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_browser","((partner_id,browser,metric,day),minute),value"),
    createTableMetadata("minutely_agg_clst_cv1","((partner_id,metric,day),minute,custom_var1),value"),
    createTableMetadata("minutely_agg_clst_cv3","((partner_id,metric,day),minute,custom_var3),value"),
    createTableMetadata("minutely_agg_clst_cv2","((partner_id,metric,day),minute,custom_var2),value"),
    createTableMetadata("minutely_agg_prtn_country_city","((partner_id,country,city,metric,day),minute),value"),
    createTableMetadata("hourly_agg_prtn_os","((partner_id,operating_system,metric,year),hour),value"),
    createTableMetadata("hourly_agg_clst_country_city","((partner_id,metric,year),hour,country,city),value"),
    createTableMetadata("tensecs_agg_prtn_domain","((partner_id,domain,metric,day),tensecs),value"),
    createTableMetadata("hourly_agg_prtn_country_clst_city","((partner_id,country,metric,year),hour,city),value"),
    createTableMetadata("hourly_agg_prtn_browser","((partner_id,browser,metric,year),hour),value"),
    createTableMetadata("tensecs_agg_prtn_country","((partner_id,country,metric,day),tensecs),value"),
    createTableMetadata("hourly_agg_prtn_app","((partner_id,application,metric,year),hour),value"),
    createTableMetadata("minutely_agg","((partner_id,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_cv1_clst_cv2","((partner_id,entry_id,custom_var1,metric,day),minute,custom_var2),value"),
    createTableMetadata("tensecs_agg_prtn_entry_clst_app","((partner_id,entry_id,metric,day),tensecs,application),value"),
    createTableMetadata("hourly_agg_prtn_entry_country_city","((partner_id,entry_id,country,city,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_country_city","((partner_id,entry_id,metric,day),minute,country,city),value"),
    createTableMetadata("hourly_agg_prtn_entry_domain_clst_referrer","((partner_id,entry_id,domain,metric,year),hour,referrer),value"),
    createTableMetadata("minutely_agg_prtn_entry_domain","((partner_id,entry_id,domain,metric,day),minute),value"),
    createTableMetadata("tensecs_agg_prtn_entry_os","((partner_id,entry_id,operating_system,metric,day),tensecs),value"),
    createTableMetadata("minutely_agg_prtn_entry_app_clst_playbackcontext","((partner_id,entry_id,application,metric,day),minute,playback_context),value"),
    createTableMetadata("hourly_agg_prtn_entry_app_clst_playbackcontext","((partner_id,entry_id,application,metric,year),hour,playback_context),value"),
    createTableMetadata("tensecs_agg_prtn_entry_country_clst_os_browser","((partner_id,entry_id,country,metric,day),tensecs,operating_system,browser),value"),
    createTableMetadata("hourly_agg_prtn_entry_cv1_cv2","((partner_id,entry_id,custom_var1,custom_var2,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_entry_country","((partner_id,entry_id,country,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_entry_app","((partner_id,entry_id,application,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_device_clst_os","((partner_id,entry_id,device,metric,day),minute,operating_system),value"),
    createTableMetadata("hourly_agg_prtn_entry_device_clst_os","((partner_id,entry_id,device,metric,year),hour,operating_system),value"),
    createTableMetadata("minutely_agg_prtn_entry_playbackcontext","((partner_id,entry_id,playback_context,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_country_clst_os","((partner_id,entry_id,country,metric,day),minute,operating_system),value"),
    createTableMetadata("hourly_agg_prtn_entry_cv1_clst_cv2","((partner_id,entry_id,custom_var1,year,metric),hour,custom_var2),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_country","((partner_id,entry_id,metric,year),hour,country),value"),
    createTableMetadata("hourly_agg_prtn_entry_cv1","((partner_id,entry_id,custom_var1,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_entry_cv3","((partner_id,entry_id,custom_var3,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_entry_cv2","((partner_id,entry_id,custom_var2,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_cv1","((partner_id,entry_id,year,metric),hour,custom_var1),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_cv3","((partner_id,entry_id,year,metric),hour,custom_var3),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_cv2","((partner_id,entry_id,year,metric),hour,custom_var2),value"),
    createTableMetadata("minutely_agg_prtn_entry_country_clst_city","((partner_id,entry_id,country,metric,day),minute,city),value"),
    createTableMetadata("hourly_agg_prtn_entry_referrer","((partner_id,entry_id,referrer,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_entry_os","((partner_id,entry_id,operating_system,metric,day),minute),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_device","((partner_id,entry_id,metric,year),hour,device),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_referrer","((partner_id,entry_id,metric,year),hour,referrer),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_domain","((partner_id,entry_id,metric,day),minute,domain),value"),
    createTableMetadata("minutely_agg_prtn_entry_os_clst_browser","((partner_id,entry_id,operating_system,metric,day),minute,browser),value"),
    createTableMetadata("tensecs_agg_prtn_entry_referrer","((partner_id,entry_id,referrer,metric,day),tensecs),value"),
    createTableMetadata("hourly_agg_prtn_entry_playbackcontext","((partner_id,entry_id,playback_context,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_entry_cv1_cv2_clst_cv3","((partner_id,entry_id,custom_var1,custom_var2,metric,day),minute,custom_var3),value"),
    createTableMetadata("hourly_agg_prtn_entry_device_os","((partner_id,entry_id,device,operating_system,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_entry_cv1","((partner_id,entry_id,custom_var1,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_device","((partner_id,entry_id,metric,day),minute,device),value"),
    createTableMetadata("minutely_agg_prtn_entry_cv3","((partner_id,entry_id,custom_var3,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_cv2","((partner_id,entry_id,custom_var2,metric,day),minute),value"),
    createTableMetadata("hourly_agg_prtn_entry_os_browser","((partner_id,entry_id,operating_system,browser,metric,year),hour),value"),
    createTableMetadata("hourly_agg_prtn_entry_country_clst_os","((partner_id,entry_id,country,year,metric),hour,operating_system),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_browser","((partner_id,entry_id,metric,day),minute,browser),value"),
    createTableMetadata("minutely_agg_prtn_entry_cv1_cv2_cv3","((partner_id,entry_id,custom_var1,custom_var2,custom_var3,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_country","((partner_id,entry_id,metric,day),minute,country),value"),
    createTableMetadata("minutely_agg_prtn_entry_device_os","((partner_id,entry_id,device,operating_system,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_cv1_cv2","((partner_id,entry_id,custom_var1,custom_var2,metric,day),minute),value"),
    createTableMetadata("tensecs_agg_prtn_entry_clst_os","((partner_id,entry_id,metric,day),tensecs,operating_system),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_browser","((partner_id,entry_id,metric,year),hour,browser),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_domain","((partner_id,entry_id,metric,year),hour,domain),value"),
    createTableMetadata("tensecs_agg_prtn_entry_clst_referrer","((partner_id,entry_id,metric,day),tensecs,referrer),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_app","((partner_id,entry_id,metric,year),hour,application),value"),
    createTableMetadata("minutely_agg_prtn_entry_country","((partner_id,entry_id,country,metric,day),minute),value"),
    createTableMetadata("hourly_agg_prtn_entry_cv1_cv2_cv3","((partner_id,custom_var1,entry_id,custom_var2,custom_var3,year,metric),hour),value"),
    createTableMetadata("hourly_agg_prtn_entry_os_clst_browser","((partner_id,entry_id,operating_system,metric,year),hour,browser),value"),
    createTableMetadata("tensecs_agg_prtn_entry_country_city","((partner_id,entry_id,country,city,metric,day),tensecs),value"),
    createTableMetadata("hourly_agg_prtn_entry_cv1_cv2_clst_cv3","((partner_id,entry_id,custom_var1,custom_var2,year,metric),hour,custom_var3),value"),
    createTableMetadata("hourly_agg_prtn_entry_device","((partner_id,entry_id,device,metric,year),hour),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_os","((partner_id,entry_id,metric,year),hour,operating_system),value"),
    createTableMetadata("minutely_agg_prtn_entry_country_clst_browser","((partner_id,entry_id,country,metric,day),minute,browser),value"),
    createTableMetadata("tensecs_agg_prtn_entry_country_clst_city","((partner_id,entry_id,country,metric,day),tensecs,city),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_app","((partner_id,entry_id,metric,day),minute,application),value"),
    createTableMetadata("tensecs_agg_prtn_entry_clst_country_city","((partner_id,entry_id,metric,day),tensecs,country,city),value"),
    createTableMetadata("minutely_agg_prtn_entry_app_playbackcontext","((partner_id,entry_id,application,playback_context,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_os_browser","((partner_id,entry_id,operating_system,browser,metric,day),minute),value"),
    createTableMetadata("tensecs_agg_prtn_entry_domain_clst_referrer","((partner_id,entry_id,domain,metric,day),tensecs,referrer),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_os","((partner_id,entry_id,metric,day),minute,operating_system),value"),
    createTableMetadata("minutely_agg_prtn_entry_domain_clst_referrer","((partner_id,entry_id,domain,metric,day),minute,referrer),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_referrer","((partner_id,entry_id,metric,day),minute,referrer),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_playbackcontext","((partner_id,entry_id,metric,year),hour,playback_context),value"),
    createTableMetadata("tensecs_agg_prtn_entry_clst_domain","((partner_id,entry_id,metric,day),tensecs,domain),value"),
    createTableMetadata("hourly_agg_prtn_entry_app_playbackcontext","((partner_id,entry_id,application,playback_context,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_playbackcontext","((partner_id,entry_id,metric,day),minute,playback_context),value"),
    createTableMetadata("tensecs_agg_prtn_entry_app","((partner_id,entry_id,application,metric,day),tensecs),value"),
    createTableMetadata("minutely_agg_prtn_entry_device","((partner_id,entry_id,device,metric,day),minute),value"),
    createTableMetadata("tensecs_agg_prtn_entry_clst_country","((partner_id,entry_id,metric,day),tensecs,country),value"),
    createTableMetadata("minutely_agg_prtn_entry_referrer","((partner_id,entry_id,referrer,metric,day),minute),value"),
    createTableMetadata("hourly_agg_prtn_entry_domain","((partner_id,entry_id,domain,metric,year),hour),value"),
    createTableMetadata("minutely_agg_prtn_entry_browser","((partner_id,entry_id,browser,metric,day),minute),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_cv1","((partner_id,entry_id,metric,day),minute,custom_var1),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_cv3","((partner_id,entry_id,metric,day),minute,custom_var3),value"),
    createTableMetadata("minutely_agg_prtn_entry_clst_cv2","((partner_id,entry_id,metric,day),minute,custom_var2),value"),
    createTableMetadata("minutely_agg_prtn_entry_country_city","((partner_id,entry_id,country,city,metric,day),minute),value"),
    createTableMetadata("hourly_agg_prtn_entry_os","((partner_id,entry_id,operating_system,metric,year),hour),value"),
    createTableMetadata("hourly_agg_prtn_entry_clst_country_city","((partner_id,entry_id,metric,year),hour,country,city),value"),
    createTableMetadata("tensecs_agg_prtn_entry_domain","((partner_id,entry_id,domain,metric,day),tensecs),value"),
    createTableMetadata("hourly_agg_prtn_entry_country_clst_city","((partner_id,entry_id,country,metric,year),hour,city),value"),
    createTableMetadata("hourly_agg_prtn_entry_browser","((partner_id,entry_id,browser,metric,year),hour),value"),
    createTableMetadata("tensecs_agg_prtn_entry_country","((partner_id,entry_id,country,metric,day),tensecs),value"),
    createTableMetadata("hourly_agg_prtn_entry_app","((partner_id,entry_id,application,metric,year),hour),value"),
    createTableMetadata("hourly_agg_prtn_category","((partner_id,category,metric,year),hour),value"),
    createTableMetadata("hourly_agg_clst_category","((partner_id,metric,year),hour,category),value"),
    createTableMetadata("minutely_agg_prtn_category","((partner_id,category,metric,day),minute),value"),
    createTableMetadata("minutely_agg_clst_category","((partner_id,metric,day),minute,category),value"),
    createTableMetadata("tensecs_agg_prtn_category","((partner_id,category,metric,day),tensecs),value"),
    createTableMetadata("tensecs_agg_clst_category","((partner_id,metric,day),tensecs,category),value"),
    createTableMetadata("hourly_agg_prtn_category_clst_entry","((partner_id,category,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_category_clst_entry","((partner_id,category,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_app_clst_entry","((partner_id,application,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_app_clst_entry","((partner_id,application,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_app_playbackcontext_clst_entry","((partner_id,application,playback_context,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_app_playbackcontext_clst_entry","((partner_id,application,playback_context,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_browser_clst_entry","((partner_id,browser,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_browser_clst_entry","((partner_id,browser,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_country_clst_entry","((partner_id,country,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_country_clst_entry","((partner_id,country,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_country_city_clst_entry","((partner_id,country,city,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_country_city_clst_entry","((partner_id,country,city,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_cv1_clst_entry","((partner_id,custom_var1,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_cv1_clst_entry","((partner_id,custom_var1,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_cv2_clst_entry","((partner_id,custom_var2,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_cv2_clst_entry","((partner_id,custom_var2,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_cv3_clst_entry","((partner_id,custom_var3,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_cv3_clst_entry","((partner_id,custom_var3,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_cv1_cv2_clst_entry","((partner_id,custom_var1,custom_var2,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_cv1_cv2_clst_entry","((partner_id,custom_var1,custom_var2,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_cv1_cv2_cv3_clst_entry","((partner_id,custom_var1,custom_var2,custom_var3,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_cv1_cv2_cv3_clst_entry","((partner_id,custom_var1,custom_var2,custom_var3,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_device_clst_entry","((partner_id,device,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_device_clst_entry","((partner_id,device,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_device_os_clst_entry","((partner_id,device,operating_system,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_device_os_clst_entry","((partner_id,device,operating_system,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_domain_clst_entry","((partner_id,domain,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_domain_clst_entry","((partner_id,domain,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_referrer_clst_entry","((partner_id,referrer,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_referrer_clst_entry","((partner_id,referrer,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_os_clst_entry","((partner_id,operating_system,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_os_clst_entry","((partner_id,operating_system,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_os_browser_clst_entry","((partner_id,operating_system,browser,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_os_browser_clst_entry","((partner_id,operating_system,browser,day,metric),minute,entry_id),value"),
    createTableMetadata("hourly_agg_prtn_playbackcontext_clst_entry","((partner_id,playback_context,month,metric),hour,entry_id),value"),
    createTableMetadata("minutely_agg_prtn_playbackcontext_clst_entry","((partner_id,playback_context,day,metric),minute,entry_id),value"),
    createTableMetadata("tensecs_agg","((partner_id,metric,day),tensecs),value")
  )

  def createColumnDefinition(s: String, isInPartitionKey : Boolean, isInClusteringKey : Boolean): IColumnDefinition = s match {
    case "partner_id" => ColumnDefinition(ColumnNames.partner_id, ColumnType.Int, isInPartitionKey, isInClusteringKey)
    case "entry_id" => ColumnDefinition(ColumnNames.entry_id, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "metric" => ColumnDefinition(ColumnNames.metric, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "year" => ColumnDefinition(ColumnNames.year, ColumnType.Int, isInPartitionKey, isInClusteringKey)
    case "month" => ColumnDefinition(ColumnNames.month, ColumnType.Int, isInPartitionKey, isInClusteringKey)
    case "day" => ColumnDefinition(ColumnNames.day, ColumnType.Int, isInPartitionKey, isInClusteringKey)
    case "country" => ColumnDefinition(ColumnNames.country, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "city" => ColumnDefinition(ColumnNames.city, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "operating_system" => ColumnDefinition(ColumnNames.operating_system, ColumnType.Int, isInPartitionKey, isInClusteringKey)
    case "browser" => ColumnDefinition(ColumnNames.browser, ColumnType.Int, isInPartitionKey, isInClusteringKey)
    case "device" => ColumnDefinition(ColumnNames.device, ColumnType.Int, isInPartitionKey, isInClusteringKey)
    case "domain" => ColumnDefinition(ColumnNames.domain, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "referrer" => ColumnDefinition(ColumnNames.referrer, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "application" => ColumnDefinition(ColumnNames.application, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "custom_var1" => ColumnDefinition(ColumnNames.custom_var1, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "custom_var2" => ColumnDefinition(ColumnNames.custom_var2, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "custom_var3" => ColumnDefinition(ColumnNames.custom_var3, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "playback_context" => ColumnDefinition(ColumnNames.playback_context, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "category" => ColumnDefinition(ColumnNames.category, ColumnType.String, isInPartitionKey, isInClusteringKey)
    case "value" => ColumnDefinition(ColumnNames.value, ColumnType.Long, isInPartitionKey, isInClusteringKey)
  }

  def createClusteringColumnDefinition(s: String): IClusteringColumnDefinition = s match {
    case "hour" => ClusteringColumnDefinition(ColumnNames.hour, ColumnType.DateTime, OrderBy.Descending)
    case "minute" => ClusteringColumnDefinition(ColumnNames.minute, ColumnType.DateTime, OrderBy.Descending)
    case "tensecs" => ClusteringColumnDefinition(ColumnNames.tensecs, ColumnType.DateTime, OrderBy.Descending)
    case "entry_id" => ClusteringColumnDefinition(ColumnNames.entry_id, ColumnType.String)
    case "country" => ClusteringColumnDefinition(ColumnNames.country, ColumnType.String)
    case "category" => ClusteringColumnDefinition(ColumnNames.category, ColumnType.String)
    case "city" => ClusteringColumnDefinition(ColumnNames.city, ColumnType.String)
    case "device" => ClusteringColumnDefinition(ColumnNames.device, ColumnType.Int)
    case "browser" => ClusteringColumnDefinition(ColumnNames.browser, ColumnType.Int)
    case "operating_system" => ClusteringColumnDefinition(ColumnNames.operating_system, ColumnType.Int)
    case "domain" => ClusteringColumnDefinition(ColumnNames.domain, ColumnType.String)
    case "referrer" => ClusteringColumnDefinition(ColumnNames.referrer, ColumnType.String)
    case "application" => ClusteringColumnDefinition(ColumnNames.application, ColumnType.String)
    case "playback_context" => ClusteringColumnDefinition(ColumnNames.playback_context, ColumnType.String)
    case "custom_var1" => ClusteringColumnDefinition(ColumnNames.custom_var1, ColumnType.String)
    case "custom_var2" => ClusteringColumnDefinition(ColumnNames.custom_var2, ColumnType.String)
    case "custom_var3" => ClusteringColumnDefinition(ColumnNames.custom_var3, ColumnType.String)
  }

  def createPartitionKey(partitionKeyColumns: String) : PartitionKey = {
    PartitionKey(partitionKeyColumns.split(",").map(createColumnDefinition(_, true, false)).toList)
  }

  def createClusteringKey(clusteringColumns: String) : ClusteringKey = {
    ClusteringKey(clusteringColumns.split(",").map(createClusteringColumnDefinition(_)).toList)
  }

  def createAdditionalColumns(additionalColumns: String) : List[IColumnDefinition] = {
    additionalColumns.split(",").map(createColumnDefinition(_, false, false)).toList
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
