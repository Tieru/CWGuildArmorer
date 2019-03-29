package migrations

import com.typesafe.config.ConfigFactory
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.configuration.ClassicConfiguration
import org.postgresql.ds.PGSimpleDataSource

object MigrationsLauncher {

  def main(args: Array[String]): Unit = {

    val dbUrlConfigPath = sys.env.getOrElse("dbconfig", throw new IllegalArgumentException())
    val dataSource = new PGSimpleDataSource()
    dataSource.setUrl(ConfigFactory.load().getConfig(dbUrlConfigPath).getString("url"))

    val config = new ClassicConfiguration()
    config.setDataSource(dataSource)

    Flyway.configure()
      .configuration(config)
      .load()
      .migrate()
  }
}
