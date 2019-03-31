package service.error

import service.error.ErrorCode.ErrorCode

object ErrorCode extends Enumeration {
  type ErrorCode = Value

  val Unregistered,
  Unknown,
  ForwardFromWrongUser,
  NewerRecordAlreadyExists,
  NoDataAvailable,
  NotInGuild,
  NoHeroInfo,
  NotInYourGuild
  : ErrorCode.Value = Value
}

case class ErrorInfo(errorCode: ErrorCode)

case class AppException(data: ErrorInfo) extends RuntimeException

