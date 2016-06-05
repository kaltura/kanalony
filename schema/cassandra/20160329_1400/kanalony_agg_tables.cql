CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv1_clst_cv2 (
    partner_id int,
	custom_var1 text,
	metric text,
	day int,
	minute timestamp,
	custom_var2 text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,metric,day), minute,custom_var2)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_clst_app (
    partner_id int,
	metric text,
	day int,
	tensecs timestamp,
	application text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), tensecs,application)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_city (
    partner_id int,
	country text,
	city text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,country,city,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_country_city (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	country text,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,country,city)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry (
    partner_id int,
	entry_id text,
	month int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,month,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_country_os_clst_browser (
    partner_id int,
	country text,
	operating_system int,
	metric text,
	day int,
	tensecs timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,country,operating_system,metric,day), tensecs,browser)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_domain_clst_referrer (
    partner_id int,
	domain text,
	metric text,
	year int,
	hour timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,domain,metric,year), hour,referrer)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_domain (
    partner_id int,
	domain text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,domain,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_os (
    partner_id int,
	operating_system int,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_app_clst_playbackcontext (
    partner_id int,
	application text,
	metric text,
	day int,
	minute timestamp,
	playback_context text,
	value bigint,
    PRIMARY KEY ((partner_id,application,metric,day), minute,playback_context)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_app_clst_playbackcontext (
    partner_id int,
	application text,
	metric text,
	year int,
	hour timestamp,
	playback_context text,
	value bigint,
    PRIMARY KEY ((partner_id,application,metric,year), hour,playback_context)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_country_clst_os_browser (
    partner_id int,
	country text,
	metric text,
	day int,
	tensecs timestamp,
	operating_system int,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,day), tensecs,operating_system,browser)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv1_cv2 (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country (
    partner_id int,
	country text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_app (
    partner_id int,
	application text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,application,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_device_clst_os (
    partner_id int,
	device int,
	metric text,
	day int,
	minute timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,device,metric,day), minute,operating_system)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_device_clst_os (
    partner_id int,
	device int,
	metric text,
	year int,
	hour timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,device,metric,year), hour,operating_system)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_playbackcontext (
    partner_id int,
	playback_context text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,playback_context,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country_clst_os (
    partner_id int,
	country text,
	metric text,
	day int,
	minute timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,day), minute,operating_system)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv1_clst_cv2 (
    partner_id int,
	custom_var1 text,
	year int,
	metric text,
	hour timestamp,
	custom_var2 text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,year,metric), hour,custom_var2)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_country (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	country text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,country)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_clst_browser (
    partner_id int,
	country text,
	year int,
	metric text,
	hour timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,country,year,metric), hour,browser)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_clst_entry (
    partner_id int,
	day int,
	metric text,
	tensecs timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,day,metric), tensecs,entry_id)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_os_clst_browser (
    partner_id int,
	country text,
	operating_system int,
	metric text,
	year int,
	hour timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,country,operating_system,metric,year), hour,browser)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv1 (
    partner_id int,
	custom_var1 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv3 (
    partner_id int,
	custom_var3 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var3,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv2 (
    partner_id int,
	custom_var2 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var2,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_cv1 (
    partner_id int,
	year int,
	metric text,
	hour timestamp,
	custom_var1 text,
	value bigint,
    PRIMARY KEY ((partner_id,year,metric), hour,custom_var1)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_cv3 (
    partner_id int,
	year int,
	metric text,
	hour timestamp,
	custom_var3 text,
	value bigint,
    PRIMARY KEY ((partner_id,year,metric), hour,custom_var3)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_cv2 (
    partner_id int,
	year int,
	metric text,
	hour timestamp,
	custom_var2 text,
	value bigint,
    PRIMARY KEY ((partner_id,year,metric), hour,custom_var2)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country_clst_city (
    partner_id int,
	country text,
	metric text,
	day int,
	minute timestamp,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,day), minute,city)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_referrer (
    partner_id int,
	referrer text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,referrer,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_os (
    partner_id int,
	operating_system int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_device (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	device int,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,device)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_referrer (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,referrer)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_domain (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	domain text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,domain)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_os_clst_browser (
    partner_id int,
	operating_system int,
	metric text,
	day int,
	minute timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,metric,day), minute,browser)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_referrer (
    partner_id int,
	referrer text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,referrer,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_playbackcontext (
    partner_id int,
	playback_context text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,playback_context,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv1_cv2_clst_cv3 (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	metric text,
	day int,
	minute timestamp,
	custom_var3 text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,metric,day), minute,custom_var3)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country_clst_os_browser (
    partner_id int,
	country text,
	metric text,
	day int,
	minute timestamp,
	operating_system int,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,day), minute,operating_system,browser)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_device_os (
    partner_id int,
	device int,
	operating_system int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,device,operating_system,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv1 (
    partner_id int,
	custom_var1 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_device (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	device int,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,device)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv3 (
    partner_id int,
	custom_var3 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var3,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv2 (
    partner_id int,
	custom_var2 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var2,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_os_browser (
    partner_id int,
	operating_system int,
	browser int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,browser,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_clst_os (
    partner_id int,
	country text,
	year int,
	metric text,
	hour timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,country,year,metric), hour,operating_system)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_browser (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,browser)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv1_cv2_cv3 (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	custom_var3 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,custom_var3,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_entry (
    partner_id int,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_os_browser (
    partner_id int,
	country text,
	operating_system int,
	browser int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,country,operating_system,browser,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_country (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	country text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,country)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_device_os (
    partner_id int,
	device int,
	operating_system int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,device,operating_system,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv1_cv2 (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_clst_os (
    partner_id int,
	metric text,
	day int,
	tensecs timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), tensecs,operating_system)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_browser (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,browser)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_domain (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	domain text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,domain)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_clst_referrer (
    partner_id int,
	metric text,
	day int,
	tensecs timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), tensecs,referrer)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_app (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	application text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,application)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country (
    partner_id int,
	country text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv1_cv2_cv3 (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	custom_var3 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,custom_var3,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_os_clst_browser (
    partner_id int,
	operating_system int,
	metric text,
	year int,
	hour timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,metric,year), hour,browser)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_country_city (
    partner_id int,
	country text,
	city text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,country,city,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv1_cv2_clst_cv3 (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	year int,
	metric text,
	hour timestamp,
	custom_var3 text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,year,metric), hour,custom_var3)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_device (
    partner_id int,
	device int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,device,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_os (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,operating_system)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_entry (
    partner_id int,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country_clst_browser (
    partner_id int,
	country text,
	metric text,
	day int,
	minute timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,day), minute,browser)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_country_clst_city (
    partner_id int,
	country text,
	metric text,
	day int,
	tensecs timestamp,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,day), tensecs,city)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_app (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	application text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,application)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_clst_country_city (
    partner_id int,
	metric text,
	day int,
	tensecs timestamp,
	country text,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), tensecs,country,city)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_app_playbackcontext (
    partner_id int,
	application text,
	playback_context text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,application,playback_context,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry (
    partner_id int,
	entry_id text,
	day int,
	metric text,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,day,metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_os_browser (
    partner_id int,
	operating_system int,
	browser int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,browser,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_domain_clst_referrer (
    partner_id int,
	domain text,
	metric text,
	day int,
	tensecs timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,domain,metric,day), tensecs,referrer)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_os (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,operating_system)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_domain_clst_referrer (
    partner_id int,
	domain text,
	metric text,
	day int,
	minute timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,domain,metric,day), minute,referrer)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_referrer (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,referrer)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_playbackcontext (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	playback_context text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,playback_context)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_clst_domain (
    partner_id int,
	metric text,
	day int,
	tensecs timestamp,
	domain text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), tensecs,domain)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_app_playbackcontext (
    partner_id int,
	application text,
	playback_context text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,application,playback_context,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_playbackcontext (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	playback_context text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,playback_context)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_clst_os_browser (
    partner_id int,
	country text,
	metric text,
	year int,
	hour timestamp,
	operating_system int,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,year), hour,operating_system,browser)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_app (
    partner_id int,
	application text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,application,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_device (
    partner_id int,
	device int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,device,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry (
    partner_id int,
	entry_id text,
	day int,
	metric text,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,day,metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country_os_clst_browser (
    partner_id int,
	country text,
	operating_system int,
	metric text,
	day int,
	minute timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,country,operating_system,metric,day), minute,browser)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_clst_country (
    partner_id int,
	metric text,
	day int,
	tensecs timestamp,
	country text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), tensecs,country)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_referrer (
    partner_id int,
	referrer text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,referrer,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_domain (
    partner_id int,
	domain text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,domain,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_browser (
    partner_id int,
	browser int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,browser,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_cv1 (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	custom_var1 text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,custom_var1)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_cv3 (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	custom_var3 text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,custom_var3)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_cv2 (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	custom_var2 text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,custom_var2)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country_city (
    partner_id int,
	country text,
	city text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,country,city,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_os (
    partner_id int,
	operating_system int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_country_city (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	country text,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,country,city)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_domain (
    partner_id int,
	domain text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,domain,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_clst_city (
    partner_id int,
	country text,
	metric text,
	year int,
	hour timestamp,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,year), hour,city)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_browser (
    partner_id int,
	browser int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,browser,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_country (
    partner_id int,
	country text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,country,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_app (
    partner_id int,
	application text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,application,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_cv1_clst_cv2 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	metric text,
	day int,
	minute timestamp,
	custom_var2 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,metric,day), minute,custom_var2)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_clst_app (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	tensecs timestamp,
	application text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), tensecs,application)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_country_city (
    partner_id int,
	entry_id text,
	country text,
	city text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,city,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_country_city (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	country text,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,country,city)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_domain_clst_referrer (
    partner_id int,
	entry_id text,
	domain text,
	metric text,
	year int,
	hour timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,domain,metric,year), hour,referrer)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_domain (
    partner_id int,
	entry_id text,
	domain text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,domain,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_os (
    partner_id int,
	entry_id text,
	operating_system int,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,operating_system,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_app_clst_playbackcontext (
    partner_id int,
	entry_id text,
	application text,
	metric text,
	day int,
	minute timestamp,
	playback_context text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,application,metric,day), minute,playback_context)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_app_clst_playbackcontext (
    partner_id int,
	entry_id text,
	application text,
	metric text,
	year int,
	hour timestamp,
	playback_context text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,application,metric,year), hour,playback_context)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_country_clst_os_browser (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	day int,
	tensecs timestamp,
	operating_system int,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,day), tensecs,operating_system,browser)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_cv1_cv2 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	custom_var2 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,custom_var2,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_country (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_app (
    partner_id int,
	entry_id text,
	application text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,application,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_device_clst_os (
    partner_id int,
	entry_id text,
	device int,
	metric text,
	day int,
	minute timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,device,metric,day), minute,operating_system)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_device_clst_os (
    partner_id int,
	entry_id text,
	device int,
	metric text,
	year int,
	hour timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,device,metric,year), hour,operating_system)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_playbackcontext (
    partner_id int,
	entry_id text,
	playback_context text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,playback_context,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_country_clst_os (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	day int,
	minute timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,day), minute,operating_system)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_cv1_clst_cv2 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	year int,
	metric text,
	hour timestamp,
	custom_var2 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,year,metric), hour,custom_var2)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_country (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	country text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,country)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_cv1 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_cv3 (
    partner_id int,
	entry_id text,
	custom_var3 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var3,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_cv2 (
    partner_id int,
	entry_id text,
	custom_var2 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var2,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_cv1 (
    partner_id int,
	entry_id text,
	year int,
	metric text,
	hour timestamp,
	custom_var1 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,year,metric), hour,custom_var1)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_cv3 (
    partner_id int,
	entry_id text,
	year int,
	metric text,
	hour timestamp,
	custom_var3 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,year,metric), hour,custom_var3)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_cv2 (
    partner_id int,
	entry_id text,
	year int,
	metric text,
	hour timestamp,
	custom_var2 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,year,metric), hour,custom_var2)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_country_clst_city (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	day int,
	minute timestamp,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,day), minute,city)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_referrer (
    partner_id int,
	entry_id text,
	referrer text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,referrer,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_os (
    partner_id int,
	entry_id text,
	operating_system int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,operating_system,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_device (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	device int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,device)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_referrer (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,referrer)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_domain (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	domain text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,domain)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_os_clst_browser (
    partner_id int,
	entry_id text,
	operating_system int,
	metric text,
	day int,
	minute timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,operating_system,metric,day), minute,browser)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_referrer (
    partner_id int,
	entry_id text,
	referrer text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,referrer,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_playbackcontext (
    partner_id int,
	entry_id text,
	playback_context text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,playback_context,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_cv1_cv2_clst_cv3 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	custom_var2 text,
	metric text,
	day int,
	minute timestamp,
	custom_var3 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,custom_var2,metric,day), minute,custom_var3)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_device_os (
    partner_id int,
	entry_id text,
	device int,
	operating_system int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,device,operating_system,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_cv1 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_device (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	device int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,device)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_cv3 (
    partner_id int,
	entry_id text,
	custom_var3 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var3,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_cv2 (
    partner_id int,
	entry_id text,
	custom_var2 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var2,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_os_browser (
    partner_id int,
	entry_id text,
	operating_system int,
	browser int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,operating_system,browser,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_country_clst_os (
    partner_id int,
	entry_id text,
	country text,
	year int,
	metric text,
	hour timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,year,metric), hour,operating_system)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_browser (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,browser)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_cv1_cv2_cv3 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	custom_var2 text,
	custom_var3 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,custom_var2,custom_var3,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_country (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	country text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,country)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_device_os (
    partner_id int,
	entry_id text,
	device int,
	operating_system int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,device,operating_system,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_cv1_cv2 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	custom_var2 text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,custom_var2,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_clst_os (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	tensecs timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), tensecs,operating_system)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_browser (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,browser)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_domain (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	domain text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,domain)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_clst_referrer (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	tensecs timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), tensecs,referrer)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_app (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	application text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,application)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_country (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_cv1_cv2_cv3 (
    partner_id int,
	custom_var1 text,
	entry_id text,
	custom_var2 text,
	custom_var3 text,
	year int,
	metric text,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,entry_id,custom_var2,custom_var3,year,metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_os_clst_browser (
    partner_id int,
	entry_id text,
	operating_system int,
	metric text,
	year int,
	hour timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,operating_system,metric,year), hour,browser)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_country_city (
    partner_id int,
	entry_id text,
	country text,
	city text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,city,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_cv1_cv2_clst_cv3 (
    partner_id int,
	entry_id text,
	custom_var1 text,
	custom_var2 text,
	year int,
	metric text,
	hour timestamp,
	custom_var3 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,custom_var1,custom_var2,year,metric), hour,custom_var3)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_device (
    partner_id int,
	entry_id text,
	device int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,device,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_os (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,operating_system)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_country_clst_browser (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	day int,
	minute timestamp,
	browser int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,day), minute,browser)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_country_clst_city (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	day int,
	tensecs timestamp,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,day), tensecs,city)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_app (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	application text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,application)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_clst_country_city (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	tensecs timestamp,
	country text,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), tensecs,country,city)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_app_playbackcontext (
    partner_id int,
	entry_id text,
	application text,
	playback_context text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,application,playback_context,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_os_browser (
    partner_id int,
	entry_id text,
	operating_system int,
	browser int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,operating_system,browser,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_domain_clst_referrer (
    partner_id int,
	entry_id text,
	domain text,
	metric text,
	day int,
	tensecs timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,domain,metric,day), tensecs,referrer)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_os (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	operating_system int,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,operating_system)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_domain_clst_referrer (
    partner_id int,
	entry_id text,
	domain text,
	metric text,
	day int,
	minute timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,domain,metric,day), minute,referrer)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_referrer (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	referrer text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,referrer)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_playbackcontext (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	playback_context text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,playback_context)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_clst_domain (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	tensecs timestamp,
	domain text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), tensecs,domain)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_app_playbackcontext (
    partner_id int,
	entry_id text,
	application text,
	playback_context text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,application,playback_context,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_playbackcontext (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	playback_context text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,playback_context)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_app (
    partner_id int,
	entry_id text,
	application text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,application,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_device (
    partner_id int,
	entry_id text,
	device int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,device,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_clst_country (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	tensecs timestamp,
	country text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), tensecs,country)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_referrer (
    partner_id int,
	entry_id text,
	referrer text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,referrer,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_domain (
    partner_id int,
	entry_id text,
	domain text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,domain,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_browser (
    partner_id int,
	entry_id text,
	browser int,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,browser,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_cv1 (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	custom_var1 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,custom_var1)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_cv3 (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	custom_var3 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,custom_var3)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_clst_cv2 (
    partner_id int,
	entry_id text,
	metric text,
	day int,
	minute timestamp,
	custom_var2 text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,day), minute,custom_var2)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_entry_country_city (
    partner_id int,
	entry_id text,
	country text,
	city text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,city,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_os (
    partner_id int,
	entry_id text,
	operating_system int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,operating_system,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_clst_country_city (
    partner_id int,
	entry_id text,
	metric text,
	year int,
	hour timestamp,
	country text,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,metric,year), hour,country,city)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_domain (
    partner_id int,
	entry_id text,
	domain text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,domain,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_country_clst_city (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	year int,
	hour timestamp,
	city text,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,year), hour,city)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_browser (
    partner_id int,
	entry_id text,
	browser int,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,browser,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_entry_country (
    partner_id int,
	entry_id text,
	country text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,country,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_entry_app (
    partner_id int,
	entry_id text,
	application text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,entry_id,application,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_category (
    partner_id int,
	category text,
	metric text,
	year int,
	hour timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,category,metric,year), hour)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_clst_category (
    partner_id int,
	metric text,
	year int,
	hour timestamp,
	category text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,year), hour,category)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_category (
    partner_id int,
	category text,
	metric text,
	day int,
	minute timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,category,metric,day), minute)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_clst_category (
    partner_id int,
	metric text,
	day int,
	minute timestamp,
	category text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), minute,category)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_prtn_category (
    partner_id int,
	category text,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,category,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg_clst_category (
    partner_id int,
	metric text,
	day int,
	tensecs timestamp,
	category text,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), tensecs,category)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_category_clst_entry (
    partner_id int,
	category text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,category,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_category_clst_entry (
    partner_id int,
	category text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,category,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_app_clst_entry (
    partner_id int,
	application text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,application,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_app_clst_entry (
    partner_id int,
	application text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,application,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_app_playbackcontext_clst_entry (
    partner_id int,
	application text,
	playback_context text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,application,playback_context,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_app_playbackcontext_clst_entry (
    partner_id int,
	application text,
	playback_context text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,application,playback_context,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_browser_clst_entry (
    partner_id int,
	browser int,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,browser,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_browser_clst_entry (
    partner_id int,
	browser int,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,browser,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_clst_entry (
    partner_id int,
	country text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,country,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country_clst_entry (
    partner_id int,
	country text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,country,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_country_city_clst_entry (
    partner_id int,
	country text,
	city text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,country,city,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_country_city_clst_entry (
    partner_id int,
	country text,
	city text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,country,city,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv1_clst_entry (
    partner_id int,
	custom_var1 text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv1_clst_entry (
    partner_id int,
	custom_var1 text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv2_clst_entry (
    partner_id int,
	custom_var2 text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var2,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv2_clst_entry (
    partner_id int,
	custom_var2 text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var2,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv3_clst_entry (
    partner_id int,
	custom_var3 text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var3,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv3_clst_entry (
    partner_id int,
	custom_var3 text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var3,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv1_cv2_clst_entry (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv1_cv2_clst_entry (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_cv1_cv2_cv3_clst_entry (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	custom_var3 text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,custom_var3,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_cv1_cv2_cv3_clst_entry (
    partner_id int,
	custom_var1 text,
	custom_var2 text,
	custom_var3 text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,custom_var1,custom_var2,custom_var3,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_device_clst_entry (
    partner_id int,
	device int,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,device,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_device_clst_entry (
    partner_id int,
	device int,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,device,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_device_os_clst_entry (
    partner_id int,
	device int,
	operating_system int,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,device,operating_system,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_device_os_clst_entry (
    partner_id int,
	device int,
	operating_system int,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,device,operating_system,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_domain_clst_entry (
    partner_id int,
	domain text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,domain,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_domain_clst_entry (
    partner_id int,
	domain text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,domain,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_referrer_clst_entry (
    partner_id int,
	referrer text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,referrer,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_referrer_clst_entry (
    partner_id int,
	referrer text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,referrer,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_os_clst_entry (
    partner_id int,
	operating_system int,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_os_clst_entry (
    partner_id int,
	operating_system int,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_os_browser_clst_entry (
    partner_id int,
	operating_system int,
	browser int,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,browser,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_os_browser_clst_entry (
    partner_id int,
	operating_system int,
	browser int,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,operating_system,browser,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.hourly_agg_prtn_playbackcontext_clst_entry (
    partner_id int,
	playback_context text,
	month int,
	metric text,
	hour timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,playback_context,month,metric), hour,entry_id)
) WITH CLUSTERING ORDER BY (hour DESC)
  AND default_time_to_live=0
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.minutely_agg_prtn_playbackcontext_clst_entry (
    partner_id int,
	playback_context text,
	day int,
	metric text,
	minute timestamp,
	entry_id text,
	value bigint,
    PRIMARY KEY ((partner_id,playback_context,day,metric), minute,entry_id)
) WITH CLUSTERING ORDER BY (minute DESC)
  AND default_time_to_live=15552000
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};

CREATE TABLE IF NOT EXISTS kanalony_agg.tensecs_agg (
    partner_id int,
	metric text,
	day int,
	tensecs timestamp,
	value bigint,
    PRIMARY KEY ((partner_id,metric,day), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC)
  AND default_time_to_live=604800
  AND gc_grace_seconds=43200 
  AND compaction = {'class': 'org.apache.cassandra.db.compaction.DateTieredCompactionStrategy'};