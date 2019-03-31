package handler

import com.bot4s.telegram.models.User
import com.osinka.i18n.{Lang, Messages}
import entity.{GuildForwardAction, HeroForwardAction}
import javax.inject.Inject
import response.MessageContext
import response.profile.ProfileMessageHandler
import service.error.ErrorCode
import service.error.ErrorRecoverExtensions._
import service.profile.ProfileService
import slogging.LazyLogging

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Try}

class ProfileMessageHandlerImpl @Inject()(service: ProfileService)
                                         (implicit ec: ExecutionContext) extends ProfileMessageHandler with LazyLogging {

  private implicit val lang: Lang = Lang.apply("ru")

  override def onStart(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]] = {
    service.onStart(userId)
      .map { user =>
        if (user.hero.isEmpty) {
          context.reply(Messages("start.welcome"))
        }

        Try(true)
      }
      .recoverFromAppError {
        case _ => invokeDefaultErrorHandling()
      }
  }

  override def onHeroInfo(userId: Int,
                          forwardFrom: Option[User],
                          forwardDate: Option[Int],
                          info: HeroForwardAction)(implicit context: MessageContext): Future[Try[Boolean]] = {
    val forwardFromId = userToId(forwardFrom)

    service.onProfileUpdate(userId, forwardFromId, forwardDate, info)
      .map(_ => context.reply(Messages("hero.update.success")))
      .map(_ => Try(true))
      .recoverFromAppError {
        case ErrorCode.NoDataAvailable =>
          context.reply(Messages("hero.update.fail"))
          Future.successful(Success(false))

        case ErrorCode.ForwardFromWrongUser =>
          context.reply(Messages("hero.update.fail_wrong_bot"))
          Future.successful(Success(false))

        case ErrorCode.NewerRecordAlreadyExists =>
          context.reply(Messages("hero.update.fail_old_report"))
          Future.successful(Success(false))

        case _ => invokeDefaultErrorHandling()
      }
  }

  override def onGuildInfo(userId: Int,
                           forwardFrom: Option[User],
                           forwardDate: Option[Int],
                           info: GuildForwardAction)(implicit context: MessageContext): Future[Try[Boolean]] = {
    val forwardFromId = userToId(forwardFrom)

    service.onGuildUpdate(userId, forwardFromId, forwardDate, info)
      .map(_ => context.reply(Messages("guild.update.success")))
      .map(_ => Try(true))
      .recoverFromAppError {
        case ErrorCode.ForwardFromWrongUser =>
          context.reply(Messages("hero.update.fail_wrong_bot"))
          Future.successful(Success(false))

        case _ => invokeDefaultErrorHandling()
      }
  }

  private def userToId(user: Option[User]) = user match {
    case Some(value) => Option(value.id)
    case None => None
  }

  private def invokeDefaultErrorHandling()(implicit context: MessageContext): Future[Try[Boolean]] = {
    logger.info("Profile command failed with unknown error")
    context.reply(Messages("error.unknown"))
    Future.successful(Success(false))
  }
}
