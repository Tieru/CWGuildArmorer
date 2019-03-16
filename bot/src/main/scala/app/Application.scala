package app

import slogging.{LazyLogging, LogLevel, LoggerConfig, PrintLoggerFactory}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Application extends LazyLogging {

  def main(args: Array[String]): Unit = {

    LoggerConfig.factory = PrintLoggerFactory()
    LoggerConfig.level = LogLevel.TRACE

    logger.info("Starting bot...")

    val eol = ArmorerBot.run()

    println("Press [ENTER] to shutdown the bot, it may take a few seconds...")
    scala.io.StdIn.readLine()
    ArmorerBot.shutdown()

    Await.result(eol, Duration.Inf)
  }

}
