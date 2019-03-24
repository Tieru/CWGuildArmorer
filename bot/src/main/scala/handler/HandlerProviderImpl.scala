package handler

import com.google.inject.Injector
import javax.inject.Inject
import net.codingwell.scalaguice.InjectorExtensions._

import scala.reflect.runtime.universe._

class HandlerProviderImpl @Inject()(private val injector: Injector) extends HandlerProvider {

  def provide[T: TypeTag](): T = injector.instance[T]

}
