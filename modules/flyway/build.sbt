libraryDependencies += "org.flywaydb" % "flyway-core" % "5.2.0"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"
libraryDependencies += "com.typesafe" % "config" % "1.2.1"

TaskKey[Unit]("migrate") := (runMain in Compile).toTask(" migrations.MigrationsLauncher").value

