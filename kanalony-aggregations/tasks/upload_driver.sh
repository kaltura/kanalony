#!/usr/bin/env bash
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-aggregations/target/scala-2.11/kanalony-aggregations.jar il-bigdata-5:/opt/kaltura/kanalony/.;
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-aggregations/target/scala-2.11/kanalony-aggregations.jar il-bigdata-6:/opt/kaltura/kanalony/.;
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-aggregations/target/scala-2.11/kanalony-aggregations.jar il-bigdata-7:/opt/kaltura/kanalony/.;
ssh il-bigdata-5 '/opt/kaltura/scripts/start_agg_driver.sh'
