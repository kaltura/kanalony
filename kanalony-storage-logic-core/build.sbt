lazy val `kanalony-model` = RootProject(file("../kanalony-model"))
lazy val `kanalony-storage-access` = RootProject(file("../kanalony-storage-access"))
lazy val `kanalony-storage-access-generated` = RootProject(file("../kanalony-storage-access-generated"))
lazy val `kanalony-storage-logic-core` = (project in file(".")).
  settings(
    name := "kanalony-storage-logic-core",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "com.websudos"      %% "phantom-dsl"                   % "1.22.0",
      "com.websudos"      %% "phantom-testkit"               % "1.12.2",
      "joda-time"         %  "joda-time"                     % "2.8.1",
      "com.google.guava"  %  "guava"                         % "12.0"
    )
  ).dependsOn(`kanalony-storage-access`,`kanalony-storage-access-generated`,`kanalony-model`)