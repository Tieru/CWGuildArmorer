package service.error

import scala.concurrent.{ExecutionContext, Future}

object ErrorRecoverExtensions {

  implicit class FutureRecover[T](val future: Future[T])(implicit ec: ExecutionContext) {

    def recoverWithDefaultError(): Future[T] = {
      future.recoverWith {
        case e: AppException => throw e
        case _ => throw AppException(ErrorInfo(ErrorCode.Unknown))
      }
    }
  }

}
