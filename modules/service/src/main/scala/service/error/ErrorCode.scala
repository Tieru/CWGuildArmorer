package service.error

import service.error.ErrorCode.ErrorCode

object ErrorCode extends Enumeration {
  type ErrorCode = Value

  val Unknown,
  ForwardFromWrongUser,
  NewerRecordAlreadyExists,
  NoDataAvailable,
  NotInGuild,
  NoHeroInfo,
  NotInYourGuild
  : ErrorCode.Value = Value
}

case class ErrorInfo(errorCode: ErrorCode)

case class AppException(data: ErrorInfo, cause: Option[Throwable] = None) extends RuntimeException(cause.orNull)

