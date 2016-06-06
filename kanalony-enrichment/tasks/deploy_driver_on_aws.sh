#!/usr/bin/env bash
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-enrichment/target/scala-2.11/kanalony-enrichment.jar pa-front-stg1:~/kanalony/.;
ssh pa-front-stg1 '~/kanalony/scripts/deploy-full-enrichment-prod.sh'
