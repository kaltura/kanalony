CREATE KEYSPACE IF NOT EXISTS kanalony_user_activity WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv1_clst_cv2 (
    partner_id int,
    custom_var1 text,
    metric int,
    minute timestamp,
    custom_var2 text,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, metric), minute, custom_var2)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var2 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_clst_app (
    partner_id int,
    metric int,
    tensecs timestamp,
    application text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), tensecs, application)
) WITH CLUSTERING ORDER BY (tensecs DESC, application ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_city (
    partner_id int,
    country text,
    city text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, city, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_country_city (
    partner_id int,
    metric int,
    minute timestamp,
    country text,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, country, city)
) WITH CLUSTERING ORDER BY (minute DESC, country ASC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry (
    partner_id int,
    entry_id text,
    month int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, month, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_country_os_clst_browser (
    partner_id int,
    country text,
    operating_system int,
    metric int,
    tensecs timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, country, operating_system, metric), tensecs, browser)
) WITH CLUSTERING ORDER BY (tensecs DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_domain_clst_referrer (
    partner_id int,
    domain text,
    metric int,
    year int,
    hour timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, domain, metric, year), hour, referrer)
) WITH CLUSTERING ORDER BY (hour DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_domain (
    partner_id int,
    domain text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, domain, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_os (
    partner_id int,
    operating_system int,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_app_clst_playback_context (
    partner_id int,
    application text,
    metric int,
    minute timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, application, metric), minute, playback_context)
) WITH CLUSTERING ORDER BY (minute DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_app_clst_playback_context (
    partner_id int,
    application text,
    metric int,
    year int,
    hour timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, application, metric, year), hour, playback_context)
) WITH CLUSTERING ORDER BY (hour DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_country_clst_os_browser (
    partner_id int,
    country text,
    metric int,
    tensecs timestamp,
    operating_system int,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric), tensecs, operating_system, browser)
) WITH CLUSTERING ORDER BY (tensecs DESC, operating_system ASC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv1_cv2 (
    partner_id int,
    custom_var1 text,
    custom_var2 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country (
    partner_id int,
    country text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_app (
    partner_id int,
    application text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_device_clst_os (
    partner_id int,
    device int,
    metric int,
    minute timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, device, metric), minute, operating_system)
) WITH CLUSTERING ORDER BY (minute DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_device_clst_os (
    partner_id int,
    device int,
    metric int,
    year int,
    hour timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, device, metric, year), hour, operating_system)
) WITH CLUSTERING ORDER BY (hour DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_playback_context (
    partner_id int,
    playback_context text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, playback_context, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country_clst_os (
    partner_id int,
    country text,
    metric int,
    minute timestamp,
    operating_system int,
    value bigint,
    year int,
    PRIMARY KEY ((partner_id, country, metric), minute, operating_system)
) WITH CLUSTERING ORDER BY (minute DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv1_clst_cv2 (
    partner_id int,
    custom_var1 text,
    year int,
    metric int,
    hour timestamp,
    custom_var2 text,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, year, metric), hour, custom_var2)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var2 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_country (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    country text,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, country)
) WITH CLUSTERING ORDER BY (hour DESC, country ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_clst_browser (
    partner_id int,
    country text,
    year int,
    metric int,
    hour timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, country, year, metric), hour, browser)
) WITH CLUSTERING ORDER BY (hour DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_clst_entry (
    partner_id int,
    day int,
    metric int,
    tensecs timestamp,
    entry_id text,
    value bigint,
    PRIMARY KEY ((partner_id, day, metric), tensecs, entry_id)
) WITH CLUSTERING ORDER BY (tensecs DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_os_clst_browser (
    partner_id int,
    country text,
    operating_system int,
    metric int,
    year int,
    hour timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, country, operating_system, metric, year), hour, browser)
) WITH CLUSTERING ORDER BY (hour DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv1 (
    partner_id int,
    custom_var1 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv3 (
    partner_id int,
    custom_var3 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var3, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv2 (
    partner_id int,
    custom_var2 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var2, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_cv1 (
    partner_id int,
    year int,
    metric int,
    hour timestamp,
    custom_var1 text,
    value bigint,
    PRIMARY KEY ((partner_id, year, metric), hour, custom_var1)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var1 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_cv3 (
    partner_id int,
    year int,
    metric int,
    hour timestamp,
    custom_var3 text,
    value bigint,
    PRIMARY KEY ((partner_id, year, metric), hour, custom_var3)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var3 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_cv2 (
    partner_id int,
    year int,
    metric int,
    hour timestamp,
    custom_var2 text,
    value bigint,
    PRIMARY KEY ((partner_id, year, metric), hour, custom_var2)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var2 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country_clst_city (
    partner_id int,
    country text,
    metric int,
    minute timestamp,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric), minute, city)
) WITH CLUSTERING ORDER BY (minute DESC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_referrer (
    partner_id int,
    referrer text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, referrer, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_os (
    partner_id int,
    operating_system int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_device (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    device int,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, device)
) WITH CLUSTERING ORDER BY (hour DESC, device ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_playbackcontext (
    partner_id int,
    playback_context text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, playback_context, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_referrer (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, referrer)
) WITH CLUSTERING ORDER BY (hour DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_domain (
    partner_id int,
    metric int,
    minute timestamp,
    domain text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, domain)
) WITH CLUSTERING ORDER BY (minute DESC, domain ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_os_clst_browser (
    partner_id int,
    operating_system int,
    metric int,
    minute timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, metric), minute, browser)
) WITH CLUSTERING ORDER BY (minute DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_referrer (
    partner_id int,
    referrer text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, referrer, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_playback_context (
    partner_id int,
    playback_context text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, playback_context, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv1_cv2_clst_cv3 (
    partner_id int,
    custom_var1 text,
    custom_var2 text,
    metric int,
    minute timestamp,
    custom_var3 text,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, metric), minute, custom_var3)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var3 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country_clst_os_browser (
    partner_id int,
    country text,
    metric int,
    minute timestamp,
    operating_system int,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric), minute, operating_system, browser)
) WITH CLUSTERING ORDER BY (minute DESC, operating_system ASC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_device_os (
    partner_id int,
    device int,
    operating_system int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, device, operating_system, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv1 (
    partner_id int,
    custom_var1 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_device (
    partner_id int,
    metric int,
    minute timestamp,
    device int,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, device)
) WITH CLUSTERING ORDER BY (minute DESC, device ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv3 (
    partner_id int,
    custom_var3 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var3, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv2 (
    partner_id int,
    custom_var2 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var2, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_os_browser (
    partner_id int,
    operating_system int,
    browser int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, browser, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_clst_os (
    partner_id int,
    country text,
    year int,
    metric int,
    hour timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, country, year, metric), hour, operating_system)
) WITH CLUSTERING ORDER BY (hour DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_browser (
    partner_id int,
    metric int,
    minute timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, browser)
) WITH CLUSTERING ORDER BY (minute DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv1_cv2_cv3 (
    partner_id int,
    custom_var1 text,
    custom_var2 text,
    custom_var3 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, custom_var3, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_entry (
    partner_id int,
    day int,
    metric int,
    minute timestamp,
    entry_id text,
    value bigint,
    PRIMARY KEY ((partner_id, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_os_browser (
    partner_id int,
    country text,
    operating_system int,
    browser int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, operating_system, browser, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_country (
    partner_id int,
    metric int,
    minute timestamp,
    country text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, country)
) WITH CLUSTERING ORDER BY (minute DESC, country ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_device_os (
    partner_id int,
    device int,
    operating_system int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, device, operating_system, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv1_cv2 (
    partner_id int,
    custom_var1 text,
    custom_var2 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_clst_os (
    partner_id int,
    metric int,
    tensecs timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, metric), tensecs, operating_system)
) WITH CLUSTERING ORDER BY (tensecs DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_browser (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, browser)
) WITH CLUSTERING ORDER BY (hour DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_domain (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    domain text,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, domain)
) WITH CLUSTERING ORDER BY (hour DESC, domain ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_clst_referrer (
    partner_id int,
    metric int,
    tensecs timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), tensecs, referrer)
) WITH CLUSTERING ORDER BY (tensecs DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_app (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    application text,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, application)
) WITH CLUSTERING ORDER BY (hour DESC, application ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country (
    partner_id int,
    country text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv1_cv2_cv3 (
    partner_id int,
    custom_var1 text,
    custom_var2 text,
    custom_var3 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, custom_var3, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_os_clst_browser (
    partner_id int,
    operating_system int,
    metric int,
    year int,
    hour timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, metric, year), hour, browser)
) WITH CLUSTERING ORDER BY (hour DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_country_city (
    partner_id int,
    country text,
    city text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, city, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv1_cv2_clst_cv3 (
    partner_id int,
    custom_var1 text,
    custom_var2 text,
    year int,
    metric int,
    hour timestamp,
    custom_var3 text,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, year, metric), hour, custom_var3)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var3 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_device (
    partner_id int,
    device int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, device, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_os (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, operating_system)
) WITH CLUSTERING ORDER BY (hour DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_entry (
    partner_id int,
    month int,
    metric int,
    hour timestamp,
    entry_id text,
    value bigint,
    PRIMARY KEY ((partner_id, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_playbackcontext (
    partner_id int,
    metric int,
    minute timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, playback_context)
) WITH CLUSTERING ORDER BY (minute DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country_clst_browser (
    partner_id int,
    country text,
    metric int,
    minute timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric), minute, browser)
) WITH CLUSTERING ORDER BY (minute DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_country_clst_city (
    partner_id int,
    country text,
    metric int,
    tensecs timestamp,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric), tensecs, city)
) WITH CLUSTERING ORDER BY (tensecs DESC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_app (
    partner_id int,
    metric int,
    minute timestamp,
    application text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, application)
) WITH CLUSTERING ORDER BY (minute DESC, application ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_clst_country_city (
    partner_id int,
    metric int,
    tensecs timestamp,
    country text,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), tensecs, country, city)
) WITH CLUSTERING ORDER BY (tensecs DESC, country ASC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_app_playback_context (
    partner_id int,
    application text,
    playback_context text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, playback_context, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry (
    partner_id int,
    entry_id text,
    day int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, day, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_os_browser (
    partner_id int,
    operating_system int,
    browser int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, browser, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_domain_clst_referrer (
    partner_id int,
    domain text,
    metric int,
    tensecs timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, domain, metric), tensecs, referrer)
) WITH CLUSTERING ORDER BY (tensecs ASC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_os (
    partner_id int,
    metric int,
    minute timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, operating_system)
) WITH CLUSTERING ORDER BY (minute DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_domain_clst_referrer (
    partner_id int,
    domain text,
    metric int,
    minute timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, domain, metric), minute, referrer)
) WITH CLUSTERING ORDER BY (minute DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_referrer (
    partner_id int,
    metric int,
    minute timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, referrer)
) WITH CLUSTERING ORDER BY (minute DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_playback_context (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, playback_context)
) WITH CLUSTERING ORDER BY (hour DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_clst_domain (
    partner_id int,
    metric int,
    tensecs timestamp,
    domain text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), tensecs, domain)
) WITH CLUSTERING ORDER BY (tensecs DESC, domain ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_app_playback_context (
    partner_id int,
    application text,
    playback_context text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, playback_context, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_playback_context (
    partner_id int,
    metric int,
    minute timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, playback_context)
) WITH CLUSTERING ORDER BY (minute DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_clst_os_browser (
    partner_id int,
    country text,
    metric int,
    year int,
    hour timestamp,
    operating_system int,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric, year), hour, operating_system, browser)
) WITH CLUSTERING ORDER BY (hour DESC, operating_system ASC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_app (
    partner_id int,
    application text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_device (
    partner_id int,
    device int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, device, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry (
    partner_id int,
    entry_id text,
    day int,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, day, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_app_clst_playbackcontext (
    partner_id int,
    application text,
    metric int,
    year int,
    hour timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, application, metric, year), hour, playback_context)
) WITH CLUSTERING ORDER BY (hour DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country_os_clst_browser (
    partner_id int,
    country text,
    operating_system int,
    metric int,
    minute timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, country, operating_system, metric), minute, browser)
) WITH CLUSTERING ORDER BY (minute DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_clst_country (
    partner_id int,
    metric int,
    tensecs timestamp,
    country text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), tensecs, country)
) WITH CLUSTERING ORDER BY (tensecs DESC, country ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_referrer (
    partner_id int,
    referrer text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, referrer, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_domain (
    partner_id int,
    domain text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, domain, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_browser (
    partner_id int,
    browser int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, browser, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_cv1 (
    partner_id int,
    metric int,
    minute timestamp,
    custom_var1 text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, custom_var1)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var1 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_cv3 (
    partner_id int,
    metric int,
    minute timestamp,
    custom_var3 text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, custom_var3)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var3 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_cv2 (
    partner_id int,
    metric int,
    minute timestamp,
    custom_var2 text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, custom_var2)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var2 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country_city (
    partner_id int,
    country text,
    city text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, city, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_os (
    partner_id int,
    operating_system int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_country_city (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    country text,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, country, city)
) WITH CLUSTERING ORDER BY (hour DESC, country ASC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_domain (
    partner_id int,
    domain text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, domain, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_clst_city (
    partner_id int,
    country text,
    metric int,
    year int,
    hour timestamp,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric, year), hour, city)
) WITH CLUSTERING ORDER BY (hour DESC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_browser (
    partner_id int,
    browser int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, browser, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_country (
    partner_id int,
    country text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_app (
    partner_id int,
    application text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua (
    partner_id int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);


CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_cv1_clst_cv2 (
    partner_id int,
    entry_id text, 
    custom_var1 text,
    metric int,
    minute timestamp,
    custom_var2 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, metric), minute, custom_var2)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var2 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_clst_app (
    partner_id int,
    entry_id text, 
    metric int,
    tensecs timestamp,
    application text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), tensecs, application)
) WITH CLUSTERING ORDER BY (tensecs DESC, application ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_country_city (
    partner_id int,
    entry_id text,
    country text,
    city text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, city, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_country_city (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    country text,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, country, city)
) WITH CLUSTERING ORDER BY (minute DESC, country ASC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_domain_clst_referrer (
    partner_id int,
    entry_id text,
    domain text,
    metric int,
    year int,
    hour timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, domain, metric, year), hour, referrer)
) WITH CLUSTERING ORDER BY (hour DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_domain (
    partner_id int,
    entry_id text, 
    domain text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, domain, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_os (
    partner_id int,
    entry_id text,
    operating_system int,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, operating_system, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_app_clst_playback_context (
    partner_id int,
    entry_id text,
    application text,
    metric int,
    minute timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, application, metric), minute, playback_context)
) WITH CLUSTERING ORDER BY (minute DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_app_clst_playback_context (
    partner_id int,
    entry_id text,
    application text,
    metric int,
    year int,
    hour timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, application, metric, year), hour, playback_context)
) WITH CLUSTERING ORDER BY (hour DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_country_clst_os_browser (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    tensecs timestamp,
    operating_system int,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, metric), tensecs, operating_system, browser)
) WITH CLUSTERING ORDER BY (tensecs DESC, operating_system ASC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_cv1_cv2 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, custom_var2, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_country (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_app (
    partner_id int,
    entry_id text,
    application text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, application, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_device_clst_os (
    partner_id int,
    entry_id text, 
    device int,
    metric int,
    minute timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, device, metric), minute, operating_system)
) WITH CLUSTERING ORDER BY (minute DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_device_clst_os (
    partner_id int,
    entry_id text,
    device int,
    metric int,
    year int,
    hour timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, device, metric, year), hour, operating_system)
) WITH CLUSTERING ORDER BY (hour DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_playback_context (
    partner_id int,
    entry_id text,
    playback_context text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, playback_context, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_country_clst_os (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    minute timestamp,
    operating_system int,
    value bigint,
    year int,
    PRIMARY KEY ((partner_id, entry_id, country, metric), minute, operating_system)
) WITH CLUSTERING ORDER BY (minute DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_cv1_clst_cv2 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    year int,
    metric int,
    hour timestamp,
    custom_var2 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, year, metric), hour, custom_var2)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var2 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_country (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    country text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, country)
) WITH CLUSTERING ORDER BY (hour DESC, country ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_cv1 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_cv3 (
    partner_id int,
    entry_id text,
    custom_var3 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var3, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_cv2 (
    partner_id int,
    entry_id text,
    custom_var2 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var2, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_cv1 (
    partner_id int,
    entry_id text,
    year int,
    metric int,
    hour timestamp,
    custom_var1 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, year, metric), hour, custom_var1)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var1 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_cv3 (
    partner_id int,
    entry_id text,
    year int,
    metric int,
    hour timestamp,
    custom_var3 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, year, metric), hour, custom_var3)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var3 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_cv2 (
    partner_id int,
    entry_id text,
    year int,
    metric int,
    hour timestamp,
    custom_var2 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, year, metric), hour, custom_var2)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var2 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_country_clst_city (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    minute timestamp,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, metric), minute, city)
) WITH CLUSTERING ORDER BY (minute DESC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_referrer (
    partner_id int,
    entry_id text,
    referrer text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, referrer, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_os (
    partner_id int,
    entry_id text,
    operating_system int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, operating_system, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_device (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    device int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, device)
) WITH CLUSTERING ORDER BY (hour DESC, device ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_playbackcontext (
    partner_id int,
    entry_id text,
    playback_context text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, playback_context, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_referrer (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, referrer)
) WITH CLUSTERING ORDER BY (hour DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_domain (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    domain text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, domain)
) WITH CLUSTERING ORDER BY (minute DESC, domain ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_os_clst_browser (
    partner_id int,
    entry_id text,
    operating_system int,
    metric int,
    minute timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, operating_system, metric), minute, browser)
) WITH CLUSTERING ORDER BY (minute DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_referrer (
    partner_id int,
    entry_id text, 
    referrer text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, referrer, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_playback_context (
    partner_id int,
    entry_id text,
    playback_context text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, playback_context, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_cv1_cv2_clst_cv3 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    metric int,
    minute timestamp,
    custom_var3 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, custom_var2, metric), minute, custom_var3)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var3 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_device_os (
    partner_id int,
    entry_id text,
    device int,
    operating_system int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, device, operating_system, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_cv1 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_device (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    device int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, device)
) WITH CLUSTERING ORDER BY (minute DESC, device ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_cv3 (
    partner_id int,
    entry_id text,
    custom_var3 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var3, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_cv2 (
    partner_id int,
    entry_id text,
    custom_var2 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var2, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_os_browser (
    partner_id int,
    entry_id text,
    operating_system int,
    browser int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, operating_system, browser, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_country_clst_os (
    partner_id int,
    entry_id text,
    country text,
    year int,
    metric int,
    hour timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, year, metric), hour, operating_system)
) WITH CLUSTERING ORDER BY (hour DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_browser (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, browser)
) WITH CLUSTERING ORDER BY (minute DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_cv1_cv2_cv3 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    custom_var3 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, custom_var2, custom_var3, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_country (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    country text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, country)
) WITH CLUSTERING ORDER BY (minute DESC, country ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_device_os (
    partner_id int,
    entry_id text,
    device int,
    operating_system int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, device, operating_system, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_cv1_cv2 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, custom_var2, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_clst_os (
    partner_id int,
    entry_id text,
    metric int,
    tensecs timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), tensecs, operating_system)
) WITH CLUSTERING ORDER BY (tensecs DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_browser (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, browser)
) WITH CLUSTERING ORDER BY (hour DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_domain (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    domain text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, domain)
) WITH CLUSTERING ORDER BY (hour DESC, domain ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_clst_referrer (
    partner_id int,
    entry_id text,
    metric int,
    tensecs timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), tensecs, referrer)
) WITH CLUSTERING ORDER BY (tensecs DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_app (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    application text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, application)
) WITH CLUSTERING ORDER BY (hour DESC, application ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_country (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_cv1_cv2_cv3 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    custom_var3 text,
    year int,
    metric int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, entry_id, custom_var2, custom_var3, year, metric), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_os_clst_browser (
    partner_id int,
    entry_id text,
    operating_system int,
    metric int,
    year int,
    hour timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, operating_system, metric, year), hour, browser)
) WITH CLUSTERING ORDER BY (hour DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_country_city (
    partner_id int,
    entry_id text,
    country text,
    city text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, city, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_cv1_cv2_clst_cv3 (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    year int,
    metric int,
    hour timestamp,
    custom_var3 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, custom_var1, custom_var2, year, metric), hour, custom_var3)
) WITH CLUSTERING ORDER BY (hour DESC, custom_var3 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_device (
    partner_id int,
    entry_id text,
    device int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, device, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_os (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, operating_system)
) WITH CLUSTERING ORDER BY (hour DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_playbackcontext (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, playback_context)
) WITH CLUSTERING ORDER BY (minute DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_country_clst_browser (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    minute timestamp,
    browser int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, metric), minute, browser)
) WITH CLUSTERING ORDER BY (minute DESC, browser ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_country_clst_city (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    tensecs timestamp,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, metric), tensecs, city)
) WITH CLUSTERING ORDER BY (tensecs DESC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_app (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    application text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, application)
) WITH CLUSTERING ORDER BY (minute DESC, application ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_clst_country_city (
    partner_id int,
    entry_id text,
    metric int,
    tensecs timestamp,
    country text,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), tensecs, country, city)
) WITH CLUSTERING ORDER BY (tensecs DESC, country ASC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_app_playback_context (
    partner_id int,
    entry_id text,
    application text,
    playback_context text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, application, playback_context, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_os_browser (
    partner_id int,
    entry_id text,
    operating_system int,
    browser int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, operating_system, browser, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_domain_clst_referrer (
    partner_id int,
    entry_id text,
    domain text,
    metric int,
    tensecs timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, domain, metric), tensecs, referrer)
) WITH CLUSTERING ORDER BY (tensecs ASC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_os (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    operating_system int,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, operating_system)
) WITH CLUSTERING ORDER BY (minute DESC, operating_system ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_domain_clst_referrer (
    partner_id int,
    entry_id text,
    domain text,
    metric int,
    minute timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, domain, metric), minute, referrer)
) WITH CLUSTERING ORDER BY (minute DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_referrer (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    referrer text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, referrer)
) WITH CLUSTERING ORDER BY (minute DESC, referrer ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_playback_context (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, playback_context)
) WITH CLUSTERING ORDER BY (hour DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_clst_domain (
    partner_id int,
    entry_id text,
    metric int,
    tensecs timestamp,
    domain text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), tensecs, domain)
) WITH CLUSTERING ORDER BY (tensecs DESC, domain ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_app_playback_context (
    partner_id int,
    entry_id text,
    application text,
    playback_context text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, application, playback_context, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_playback_context (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, playback_context)
) WITH CLUSTERING ORDER BY (minute DESC, playback_context ASC);


CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_app (
    partner_id int,
    entry_id text,
    application text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, application, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_device (
    partner_id int,
    entry_id text,
    device int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, device, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_app_clst_playbackcontext (
    partner_id int,
    entry_id text,
    application text,
    metric int,
    year int,
    hour timestamp,
    playback_context text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, application, metric, year), hour, playback_context)
) WITH CLUSTERING ORDER BY (hour DESC, playback_context ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_clst_country (
    partner_id int,
    entry_id text,
    metric int,
    tensecs timestamp,
    country text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), tensecs, country)
) WITH CLUSTERING ORDER BY (tensecs DESC, country ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_referrer (
    partner_id int,
    entry_id text,
    referrer text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, referrer, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_domain (
    partner_id int,
    entry_id text,
    domain text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, domain, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_browser (
    partner_id int,
    entry_id text,
    browser int,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, browser, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_cv1 (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    custom_var1 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, custom_var1)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var1 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_cv3 (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    custom_var3 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, custom_var3)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var3 ASC);
    
CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_clst_cv2 (
    partner_id int,
    entry_id text,
    metric int,
    minute timestamp,
    custom_var2 text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric), minute, custom_var2)
) WITH CLUSTERING ORDER BY (minute DESC, custom_var2 ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_entry_country_city (
    partner_id int,
    entry_id text,
    country text,
    city text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, city, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_os (
    partner_id int,
    entry_id text,
    operating_system int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, operating_system, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_clst_country_city (
    partner_id int,
    entry_id text,
    metric int,
    year int,
    hour timestamp,
    country text,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, metric, year), hour, country, city)
) WITH CLUSTERING ORDER BY (hour DESC, country ASC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_domain (
    partner_id int,
    entry_id text,
    domain text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, domain, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_country_clst_city (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    year int,
    hour timestamp,
    city text,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, metric, year), hour, city)
) WITH CLUSTERING ORDER BY (hour DESC, city ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_browser (
    partner_id int,
    entry_id text,
    browser int,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, browser, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_entry_country (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, country, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_entry_app (
    partner_id int,
    entry_id text,
    application text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, entry_id, application, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_category (
    partner_id int,
    category text,
    metric int,
    year int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, category, metric, year), hour)
) WITH CLUSTERING ORDER BY (hour DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_clst_category (
    partner_id int,
    metric int,
    year int,
    hour timestamp,
    category text,
    value bigint,
    PRIMARY KEY ((partner_id, metric, year), hour, category)
) WITH CLUSTERING ORDER BY (hour DESC, category ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_category (
    partner_id int,
    category text,
    metric int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, category, metric), minute)
) WITH CLUSTERING ORDER BY (minute DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_clst_category (
    partner_id int,
    metric int,
    minute timestamp,
    category text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), minute, category)
) WITH CLUSTERING ORDER BY (minute DESC, category ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_prtn_category (
    partner_id int,
    category text,
    metric int,
    tensecs timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, category, metric), tensecs)
) WITH CLUSTERING ORDER BY (tensecs DESC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.tensecs_ua_clst_category (
    partner_id int,
    metric int,
    tensecs timestamp,
    category text,
    value bigint,
    PRIMARY KEY ((partner_id, metric), tensecs, category)
) WITH CLUSTERING ORDER BY (tensecs DESC, category ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_category_clst_entry (
    partner_id int,
    entry_id text,
    category text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, category, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_category_clst_entry (
    partner_id int,
    entry_id text,
    category text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, category, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_app_clst_entry (
    partner_id int,
    entry_id text,
    application text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_app_clst_entry (
    partner_id int,
    entry_id text,
    application text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_app_playback_context_clst_entry (
    partner_id int,
    entry_id text,
    application text,
    playback_context text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, playback_context, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_app_playback_context_clst_entry (
    partner_id int,
    entry_id text,
    application text,
    playback_context text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, application, playback_context, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_browser_clst_entry (
    partner_id int,
    entry_id text,
    browser int,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, browser, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_browser_clst_entry (
    partner_id int,
    entry_id text,
    browser int,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, browser, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_clst_entry (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country_clst_entry (
    partner_id int,
    entry_id text,
    country text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_country_city_clst_entry (
    partner_id int,
    entry_id text,
    country text,
    city text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, city, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_country_city_clst_entry (
    partner_id int,
    entry_id text,
    country text,
    city text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, country, city, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv1_clst_entry (
    partner_id int,
    entry_id text,
    custom_var1 text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv1_clst_entry (
    partner_id int,
    entry_id text,
    custom_var1 text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv2_clst_entry (
    partner_id int,
    entry_id text,
    custom_var2 text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var2, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv2_clst_entry (
    partner_id int,
    entry_id text,
    custom_var2 text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var2, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv3_clst_entry (
    partner_id int,
    entry_id text,
    custom_var3 text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var3, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv3_clst_entry (
    partner_id int,
    entry_id text,
    custom_var3 text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var3, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv1_cv2_clst_entry (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv1_cv2_clst_entry (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_cv1_cv2_cv3_clst_entry (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    custom_var3 text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, custom_var3, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_cv1_cv2_cv3_clst_entry (
    partner_id int,
    entry_id text,
    custom_var1 text,
    custom_var2 text,
    custom_var3 text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, custom_var1, custom_var2, custom_var3, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_device_clst_entry (
    partner_id int,
    entry_id text,
    device int,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, device, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_device_clst_entry (
    partner_id int,
    entry_id text,
    device int,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, device, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_device_os_clst_entry (
    partner_id int,
    entry_id text,
    device text,
    operating_system text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, device, operating_system, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_device_os_clst_entry (
    partner_id int,
    entry_id text,
    device text,
    operating_system text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, device, operating_system, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_domain_clst_entry (
    partner_id int,
    entry_id text,
    domain text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, domain, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_domain_clst_entry (
    partner_id int,
    entry_id text,
    domain text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, domain, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_referrer_clst_entry (
    partner_id int,
    entry_id text,
    referrer text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, referrer, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_referrer_clst_entry (
    partner_id int,
    entry_id text,
    referrer text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, referrer, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_os_clst_entry (
    partner_id int,
    entry_id text,
    operating_system int,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_os_clst_entry (
    partner_id int,
    entry_id text,
    operating_system int,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_os_browser_clst_entry (
    partner_id int,
    entry_id text,
    operating_system text,
    browser text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, browser, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_os_browser_clst_entry (
    partner_id int,
    entry_id text,
    operating_system text,
    browser text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, operating_system, browser, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);


CREATE TABLE IF NOT EXISTS kanalony_user_activity.hourly_ua_prtn_playback_context_clst_entry (
    partner_id int,
    entry_id text,
    playback_context text,
    metric int,
    month int,
    hour timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, playback_context, month, metric), hour, entry_id)
) WITH CLUSTERING ORDER BY (hour DESC, entry_id ASC);

CREATE TABLE IF NOT EXISTS kanalony_user_activity.minutely_ua_prtn_playback_context_clst_entry (
    partner_id int,
    entry_id text,
    playback_context text,
    metric int,
    day int,
    minute timestamp,
    value bigint,
    PRIMARY KEY ((partner_id, playback_context, day, metric), minute, entry_id)
) WITH CLUSTERING ORDER BY (minute DESC, entry_id ASC);

