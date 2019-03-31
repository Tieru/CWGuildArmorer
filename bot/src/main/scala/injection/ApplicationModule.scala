package injection

import com.google.inject.AbstractModule
import dao.{UserRepository, UserRepositoryImpl}
import handler.{GeneralMessageHandler, HandlerProvider, HandlerProviderImpl, ProfileMessageHandlerImpl}
import net.codingwell.scalaguice.ScalaModule
import provider.DatabaseProvider
import response.profile.ProfileMessageHandler
import service.guild.{GuildInfoService, GuildInfoServiceImpl}
import service.profile.{ProfileService, ProfileServiceImpl}
import slick.jdbc.JdbcBackend.Database

import scala.concurrent.ExecutionContext

class ApplicationModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {

    //probably shouldn't be here
    bind[ExecutionContext].toInstance(ExecutionContext.Implicits.global)

    bind[HandlerProvider].to[HandlerProviderImpl].asEagerSingleton()
    bind[GeneralMessageHandler].asEagerSingleton()

    bind[ProfileMessageHandler].to[ProfileMessageHandlerImpl].asEagerSingleton()

    bind[ProfileService].to[ProfileServiceImpl].asEagerSingleton()
    bind[GuildInfoService].to[GuildInfoServiceImpl].asEagerSingleton()

    bind(classOf[Database]).toProvider(classOf[DatabaseProvider])
    bind[UserRepository].to[UserRepositoryImpl].asEagerSingleton()
  }
}
