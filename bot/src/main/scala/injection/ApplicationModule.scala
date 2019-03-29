package injection

import com.google.inject.AbstractModule
import handler.{GeneralMessageHandler, HandlerProvider, HandlerProviderImpl, ProfileMessageHandlerImpl}
import net.codingwell.scalaguice.ScalaModule
import response.profile.ProfileMessageHandler
import service.profile.{ProfileService, ProfileServiceImpl}

import scala.concurrent.ExecutionContext

class ApplicationModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {

    //probably shouldn't be here
    bind[ExecutionContext].toInstance(ExecutionContext.Implicits.global)

    bind[HandlerProvider].to[HandlerProviderImpl].asEagerSingleton()
    bind[GeneralMessageHandler].asEagerSingleton()

    bind[ProfileMessageHandler].to[ProfileMessageHandlerImpl].asEagerSingleton()

    bind[ProfileService].to[ProfileServiceImpl].asEagerSingleton()
  }
}
