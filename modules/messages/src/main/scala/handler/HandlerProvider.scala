package handler

import scala.reflect.runtime.universe._

trait HandlerProvider {

  def provide[T: TypeTag](): T

}
