#!/usr/bin/env bash
scp /Users/ofirk/projects/kaltura/kanalony/kanalony-aggregations/target/scala-2.11/kanalony-aggregations.jar pa-front-stg1:~/kanalony/.;
ssh pa-front-stg1 '~/kanalony/scripts/deploy-full-aggregations-v0.2.sh'
