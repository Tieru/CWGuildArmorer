package response.guild

import response.MessageContext

import scala.concurrent.Future
import scala.util.Try

trait GuildInfoMessageHandler {

  def showGuildList(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]]

}
