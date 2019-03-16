package injection

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule

import scala.concurrent.ExecutionContext

class ApplicationModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {

    //probably shouldn't be here
    bind[ExecutionContext].toInstance(ExecutionContext.Implicits.global)

  }
}
