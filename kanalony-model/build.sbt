lazy val sparkVersion = "1.6.0"
lazy val json4sVersion = "3.2.10"
lazy val `kanalony-core` = RootProject(file("../kanalony-core"))
lazy val `kanalony-model` = (project in file(".")).
  settings(
    name := "kanalony-model",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
        "org.scalatest"          % "scalatest_2.11"                  % "2.2.4"       % "test",
        "org.apache.spark"       % "spark-core_2.11"                 % sparkVersion,
        "org.json4s"             % "json4s-jackson_2.11"             % json4sVersion,
        "org.json4s"             % "json4s-native_2.11"              % json4sVersion,
        "org.json4s"             % "json4s-ext_2.11"                 % json4sVersion,
        "joda-time"              % "joda-time"                       % "2.8.1",
        "com.datastax.cassandra" % "cassandra-driver-core"           % "2.1.9",
        "org.scalikejdbc"        %% "scalikejdbc"                    % "2.3.4",
        "mysql"                  % "mysql-connector-java"            % "5.1.38"
    )
).dependsOn(`kanalony-core`)