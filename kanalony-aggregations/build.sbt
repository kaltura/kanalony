lazy val sparkVersion = "1.6.0"

lazy val `kanalony-model` = RootProject(file("../kanalony-model"))
lazy val `kanalony-core` = RootProject(file("../kanalony-core"))

lazy val `kanalony-aggregations` = (project in file(".")).
  settings(
    name := "kanalony-aggregations",
    version := "1.0",
    scalaVersion := "2.10.6",
    libraryDependencies ++= Seq(

      "org.apache.spark"      % "spark-core_2.10"                 % sparkVersion,
      "org.apache.spark"      % "spark-streaming_2.10"            % sparkVersion,
      "org.apache.spark"      % "spark-streaming-kafka_2.10"      % sparkVersion,
      "com.datastax.spark"    % "spark-cassandra-connector_2.10"  % "1.4.0",
      "joda-time"             % "joda-time"                       % "2.8.2",
      // Test
      "org.scalatest"         % "scalatest_2.10"                  % "2.2.4"    % "test"
    )

  ).dependsOn(`kanalony-model`, `kanalony-core`)





