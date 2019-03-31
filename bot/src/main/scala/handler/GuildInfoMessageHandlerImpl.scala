package handler

import com.osinka.i18n.{Lang, Messages}
import javax.inject.Inject
import response.MessageContext
import response.guild.GuildInfoMessageHandler
import service.guild.GuildInfoService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Try}

class GuildInfoMessageHandlerImpl @Inject()(private val service: GuildInfoService)(implicit ec: ExecutionContext) extends GuildInfoMessageHandler{

  private implicit val lang: Lang = Lang.apply("ru")

  def showGuildList(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]] = {
    service.getGuildList(userId)
      .map { players =>
        var message = Messages("guild.list.title")
        players.foreach(player => message += s"\n/info_${player.id} -- ${player.username}")
        context.reply(message)
        Success(true)
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
        Success(true)
      }
  }

}
