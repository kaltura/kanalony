lazy val sparkVersion = "1.6.1"
lazy val json4sVersion = "3.2.10"
lazy val `kanalony-model` = RootProject(file("../kanalony-model"))
lazy val `kanalony-core` = RootProject(file("../kanalony-core"))
lazy val `kanalony-partitions-indexer` = (project in file(".")).
  settings(
    name := "kanalony-partitions-indexer",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "org.apache.spark"      %% "spark-core"                 % sparkVersion,
      "org.apache.spark"      %% "spark-streaming"            % sparkVersion,
      "org.apache.spark"      %% "spark-streaming-kafka"      % sparkVersion,
      "org.json4s"            %% "json4s-jackson"             % json4sVersion,
      "org.json4s"            %% "json4s-native"              % json4sVersion,
      "org.json4s"            %% "json4s-ext"                 % json4sVersion,
      "io.dropwizard.metrics" % "metrics-core"                % "3.1.2",
      "joda-time"             % "joda-time"                   % "2.9.1",
      "com.google.guava"      % "guava"                       % "18.0",
      "org.apache.hadoop"     % "hadoop-aws"                  % "2.7.1",
      "com.amazonaws"         % "aws-java-sdk"                % "1.7.4",

      // Test
      "org.scalatest"         %% "scalatest"                  % "2.2.4"    % "test"
    )
  ).dependsOn(`kanalony-model`, `kanalony-core`)

//assemblyJarName := "kanalony-partitions-indexer.jar"
//assemblyOutputPath := file("../out/" + assemblyJarName)
    