lazy val sparkVersion = "1.6.0"
lazy val json4sVersion = "3.2.10"

lazy val `kanalony-model` = RootProject(file("../kanalony-model"))
lazy val `kanalony-core` = RootProject(file("../kanalony-core"))

lazy val `kanalony-enhancement` = (project in file(".")).
settings(
  name := "kanalony-enhancement",
  version := "1.0",
  scalaVersion := "2.10.6",
  libraryDependencies ++= Seq(

    "org.apache.spark"      % "spark-core_2.10"                 % sparkVersion,
    "org.apache.spark"      % "spark-streaming_2.10"            % sparkVersion,
    "org.apache.spark"      % "spark-streaming-kafka_2.10"      % sparkVersion,
    "org.apache.spark"      % "spark-sql_2.10"                  % sparkVersion,
    "org.json4s"            % "json4s-jackson_2.10"             % json4sVersion,
    "org.json4s"            % "json4s-native_2.10"              % json4sVersion,
    "org.json4s"            % "json4s-ext_2.10"                 % json4sVersion,
    "com.datastax.spark"    % "spark-cassandra-connector_2.10"  % "1.5.0-M3",
    "io.dropwizard.metrics" % "metrics-core"                    % "3.1.2",
    "joda-time"             % "joda-time"                       % "2.8.2",
    "com.google.guava"      % "guava"                           % "18.0",
    // Test
    "org.scalatest"         % "scalatest_2.10"                  % "2.2.4"    % "test"
  )

).dependsOn(`kanalony-model`, `kanalony-core`)