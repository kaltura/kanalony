lazy val sparkVersion = "1.6.0"
lazy val json4sVersion = "3.2.10"

lazy val `kanalony-model` = RootProject(file("../kanalony-model"))
lazy val `kanalony-core` = RootProject(file("../kanalony-core"))

lazy val `kanalony-enrichment` = (project in file(".")).
settings(
  name := "kanalony-enrichment",
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
    "io.dropwizard.metrics" % "metrics-core"                    % "3.1.2",
    "joda-time"             % "joda-time"                       % "2.9.1",
    "com.google.guava"      % "guava"                           % "18.0",
    // Test
    "org.scalatest"         % "scalatest_2.10"                  % "2.2.4"    % "test"
  )

).dependsOn(`kanalony-model`, `kanalony-core`)



