
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "org.postgresql" % "postgresql" % "42.2.5",
  "com.github.tminglei" %% "slick-pg" % "0.16.3",
  "com.typesafe" % "config" % "1.2.1",
  "com.novocode" % "junit-interface" % "0.10" % Test,
)

lazy val databaseUrl = sys.props.getOrElse("DB_DEFAULT_URL", "'Url is not set'")
lazy val databaseUser = sys.props.getOrElse("DB_DEFAULT_USER", "'User is not set'")
lazy val databasePassword = sys.props.getOrElse("DB_DEFAULT_PASSWORD", "")


slickCodegenDatabaseUrl := databaseUrl
slickCodegenDatabaseUser := databaseUser
slickCodegenDatabasePassword := databasePassword
slickCodegenDriver := slick.jdbc.PostgresProfile
slickCodegenJdbcDriver := "org.postgresql.Driver"
slickCodegenOutputPackage := "schema.data"
slickCodegenExcludedTables := Seq("flyway_schema_history")

sourceGenerators in Compile += slickCodegen.taskValue
