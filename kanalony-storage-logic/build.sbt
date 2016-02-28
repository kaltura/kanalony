lazy val `kanalony-storage-api` = RootProject(file("../kanalony-storage-api"))
lazy val `kanalony-storage-logic` = (project in file(".")).
  settings(
    name := "kanalony-storage-logic",
    version := "1.0",
    scalaVersion := "2.10.6",
    libraryDependencies ++= Seq(
      "com.websudos"  %% "phantom-dsl"                   % "1.12.2",
      "com.websudos"  %% "phantom-testkit"               % "1.12.2",
      "joda-time"     %  "joda-time"                     % "2.8.1"
    )
  ).dependsOn(`kanalony-storage-api`)