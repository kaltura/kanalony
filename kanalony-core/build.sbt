lazy val sparkVersion = "1.6.0"
lazy val `kanalony-core` = (project in file(".")).
  settings(
    name := "kanalony-core",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "org.scalatest"           %%  "scalatest"                   % "2.2.4"       % "test",
      "org.slf4j"               %   "slf4j-api"                   % "1.7.12",
      "org.slf4j"               %   "slf4j-simple"                % "1.7.12",
      "org.apache.spark"        %%  "spark-core"                  % sparkVersion,
      "org.apache.spark"        %%  "spark-streaming"             % sparkVersion,
      "org.apache.spark"        %%  "spark-streaming-kafka"       % sparkVersion,
      "eu.bitwalker"            %   "UserAgentUtils"              % "1.18",
      "com.datastax.cassandra"  %   "cassandra-driver-core"       % "2.1.9",
      "com.datastax.spark"      %%  "spark-cassandra-connector"   % "1.4.1",
      "commons-validator"       % "commons-validator"             % "1.5.0",
      "com.kaltura"             % "kalturaClient"                 % "3.2.1",
      "com.google.guava"        % "guava"                         % "18.0"
    )
  )


