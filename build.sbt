name := "CWGuildArmorer"

version := "0.1"

scalaVersion := "2.12.8"

lazy val global = project
  .in(file("."))
  .settings(Common.projectSettings)
  .aggregate(
    flyway,
    database,
    service,
    messages,
    entity,
    bot
  )

lazy val flyway = (project in file("modules/flyway"))
  .settings(Common.projectSettings)

lazy val database = (project in file("modules/database"))
  .settings(Common.projectSettings)
  .dependsOn(
    flyway,
    entity
  )
  .enablePlugins(CodegenPlugin)

lazy val service = project
  .in(file("./modules/service"))
  .settings(Common.projectSettings)
  .dependsOn(
    database,
    entity
  )

lazy val localization = project
  .in(file("./modules/localization"))
  .settings(Common.projectSettings)

lazy val messages = project
  .in(file("./modules/messages"))
  .settings(Common.projectSettings)
  .dependsOn(
    localization,
    entity
  )

lazy val entity = project
  .in(file("./modules/entity"))
  .settings(Common.projectSettings)

lazy val bot = project
  .in(file("./bot"))
  .settings(Common.projectSettings)
  .dependsOn(
    service,
    messages,
    localization,
    entity
  )
