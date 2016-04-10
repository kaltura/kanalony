#!/usr/bin/env bash
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-enrichment/target/scala-2.11/kanalony-enrichment.jar il-bigdata-5:/opt/kaltura/kanalony/.;
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-enrichment/target/scala-2.11/kanalony-enrichment.jar il-bigdata-6:/opt/kaltura/kanalony/.;
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-enrichment/target/scala-2.11/kanalony-enrichment.jar il-bigdata-7:/opt/kaltura/kanalony/.;
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-enrichment/target/scala-2.11/kanalony-enrichment.jar il-bigdata-8:/opt/kaltura/kanalony/.;
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-enrichment/target/scala-2.11/kanalony-enrichment.jar il-bigdata-9:/opt/kaltura/kanalony/.;
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-enrichment/target/scala-2.11/kanalony-enrichment.jar il-bigdata-10:/opt/kaltura/kanalony/.;
ssh il-bigdata-5 '/opt/kaltura/scripts/start_enr_driver.sh'
