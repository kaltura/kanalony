import play.sbt.PlayImport._

lazy val `kanalony-storage-logic` = RootProject(file("../kanalony-storage-logic"))
lazy val `kanalony-web-server` = (project in file(".")).enablePlugins(PlayScala).
  settings(
    name := "kanalony-web-server",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "joda-time"     %  "joda-time"                     % "2.8.1",
      cache,
      ws,
      specs2 % Test,
      "io.argonaut" %% "argonaut" % "6.0.4"
    ),
    resolvers ++= Seq("scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"),
    routesGenerator := InjectedRoutesGenerator
  ).dependsOn(`kanalony-storage-logic`)