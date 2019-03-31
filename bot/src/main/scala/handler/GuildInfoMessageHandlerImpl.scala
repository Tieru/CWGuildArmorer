package handler

import com.osinka.i18n.{Lang, Messages}
import javax.inject.Inject
import response.MessageContext
import response.guild.GuildInfoMessageHandler
import service.error.ErrorCode
import service.error.ErrorRecoverExtensions._
import service.guild.GuildInfoService
import slogging.LazyLogging

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Try}

class GuildInfoMessageHandlerImpl @Inject()(private val service: GuildInfoService)
                                           (implicit ec: ExecutionContext) extends GuildInfoMessageHandler with LazyLogging {

  private implicit val lang: Lang = Lang.apply("ru")

  def showGuildList(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]] = {
    service.getGuildList(userId)
      .map { players =>
        var message = Messages("guild.list.title")
        players.foreach(player => message += s"\n/info_${player.id} -- ${player.username}")
        context.reply(message)
        Try(true)
      }
      .recoverFromAppError {
        case ErrorCode.NotInGuild =>
          context.reply(Messages("guild.list.fail_not_in_guild"))
          Future.successful(Try(false))

        case _ => invokeDefaultErrorHandling()
      }

  }

  override def showGuildUserInfo(userId: Int, requestedUserId: Int)(implicit context: MessageContext): Future[Try[Boolean]] = {
    service.getGuildMemberInfo(userId, requestedUserId)
      .map { user =>
        val hero = user.hero.get
        var message = hero.username + "\n"
        if (hero.equipment.isEmpty) {
          message += Messages("guild.member.no_equipment")
        } else {
          hero.equipment.foreach(item => message += s"⚡️${item.enhancement} - ${item.equipment.name}\n")
        }

        context.reply(message)
        Try(true)
      }
      .recoverFromAppError {
        case ErrorCode.NotInGuild =>
          context.reply(Messages("guild.list.fail_not_in_guild"))
          Future.successful(Try(false))

        case ErrorCode.NotInYourGuild =>
          context.reply(Messages("guild.member.fail_player_is_not_in_guild"))
          Future.successful(Try(false))

        case ErrorCode.NoHeroInfo =>
          context.reply(Messages("guild.member.fail_no_info"))
          Future.successful(Try(false))

        case _ => invokeDefaultErrorHandling()
      }
  }

  private def invokeDefaultErrorHandling()(implicit context: MessageContext): Future[Try[Boolean]] = {
    logger.info("Guild info request failed with unknown error")
    context.reply(Messages("error.unknown"))
    Future.successful(Success(false))
  }
}
