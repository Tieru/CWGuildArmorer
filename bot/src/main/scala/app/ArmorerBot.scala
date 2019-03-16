package app

import java.net.{Authenticator, InetSocketAddress, PasswordAuthentication, Proxy}

import com.bot4s.telegram.api.{Polling, RequestHandler, TelegramBot}
import com.bot4s.telegram.api.declarative.{Callbacks, Commands}
import com.bot4s.telegram.clients.ScalajHttpClient
import com.bot4s.telegram.models.Message
import com.google.inject.{Guice, Injector}
import com.softwaremill.sttp.SttpBackend
import com.softwaremill.sttp.okhttp.OkHttpFutureBackend
import com.typesafe.config.ConfigFactory
import injection.ApplicationModule

import scala.concurrent.Future

object ArmorerBot extends TelegramBot
  with Polling
  with Commands
  with Callbacks {

  val injector: Injector = Guice.createInjector(new ApplicationModule())

  implicit val backend: SttpBackend[Future, Nothing] = OkHttpFutureBackend()

  override val client: RequestHandler = {
    val configs = ConfigFactory.load()
    val token = configs.getString("bot.token")

    if (configs.hasPath("bot.useProxy") && configs.getBoolean("bot.useProxy")) {
      new ScalajHttpClient(token, buildProxySettings())
    } else {
      new ScalajHttpClient(token)
    }
  }

  override def receiveMessage(msg: Message): Unit = {
    logger.info(s"Received Message: ${msg.text}")
  }


  private def buildProxySettings(): Proxy = {
    val configs = ConfigFactory.load()
    val host = configs.getString("bot.proxyHost")

    if (configs.hasPath("bot.proxyUser")) {
      val proxyUser = configs.getString("bot.proxyUser")
      val proxyPassword = configs.getString("bot.proxyPassword")

      val auth = new Authenticator() {
        override def getPasswordAuthentication: PasswordAuthentication =
          new PasswordAuthentication(proxyUser, proxyPassword.toCharArray)
      }
      Authenticator.setDefault(auth)
    }

    val port = configs.getInt("bot.proxyPort")
    new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(host, port))
  }

  logger.info("Bot started successfully")

}
