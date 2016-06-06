lazy val sparkVersion = "1.6.1"
lazy val json4sVersion = "3.2.10"
lazy val `kanalony-core` = RootProject(file("../kanalony-core"))
lazy val `kanalony-model` = (project in file(".")).
  settings(
    name := "kanalony-model",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
        "org.scalatest"           %% "scalatest"                  % "2.2.4"       % "test",
        "org.apache.spark"        %% "spark-core"                 % sparkVersion,
        "org.json4s"              %% "json4s-jackson"             % json4sVersion,
        "org.json4s"              %% "json4s-native"              % json4sVersion,
        "org.json4s"              %% "json4s-ext"                 % json4sVersion,
        "joda-time"               %  "joda-time"                  % "2.8.1",
        "com.datastax.cassandra"  %  "cassandra-driver-core"      % "3.0.0",
        "org.scalikejdbc"         %% "scalikejdbc"                % "2.3.4",
        "mysql"                   %  "mysql-connector-java"       % "5.1.38",
        "com.kaltura"             % "kalturaClient"               % "3.2.1"
    )
).dependsOn(`kanalony-core`)