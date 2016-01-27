lazy val sparkVersion = "1.6.0"

lazy val `kanalony-model` = RootProject(file("../kanalony-model"))
lazy val `kanalony-core` = RootProject(file("../kanalony-core"))

lazy val `kanalony-aggregations` = (project in file(".")).
  settings(
    name := "kanalony-aggregations",
    version := "1.0",
    scalaVersion := "2.11.7",
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(

      "org.clapper"           % "classutil_2.11"                  % "1.0.6",
      "org.apache.spark"      % "spark-core_2.11"                 % sparkVersion,
      "org.apache.spark"      % "spark-streaming_2.11"            % sparkVersion,
      "org.apache.spark"      % "spark-streaming-kafka_2.11"      % sparkVersion,
      "com.datastax.spark"    % "spark-cassandra-connector_2.11"  % "1.4.0",
      "joda-time"             % "joda-time"                       % "2.8.2",
      // Test
      "org.scalatest"         % "scalatest_2.11"                  % "2.2.4"    % "test"
    )

  ).dependsOn(`kanalony-model`, `kanalony-core`)


