lazy val `kanalony-logstash` = (project in file(".")).
  settings(
    name := "kanalony-web-server",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "joda-time"               %  "joda-time"                % "2.8.1",
      "org.scalatest"          %% "scalatest"                 % "2.2.6"    % "test",
      "io.gatling.highcharts"   % "gatling-charts-highcharts" % "2.1.7"    % "test",
      "org.apache.kafka"        % "kafka_2.11"                % "0.9.0.1"  % "test"
    ),
    resolvers ++= Seq("scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
                      "Artima Maven Repository" at "http://repo.artima.com/releases")
  )