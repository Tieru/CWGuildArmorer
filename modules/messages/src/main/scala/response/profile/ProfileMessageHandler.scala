package response.profile

import com.bot4s.telegram.models.User
import entity.{GuildForwardAction, HeroForwardAction}
import response.MessageContext

import scala.concurrent.Future
import scala.util.Try

trait ProfileMessageHandler {

  def onStart(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]]

  def onHeroInfo(userId: Int,
                 forwardFrom: Option[User],
                 forwardDate: Option[Int],
                 info: HeroForwardAction)(implicit context: MessageContext): Future[Try[Boolean]]

  def onGuildInfo(userId: Int,
                  forwardFrom: Option[User],
                  forwardDate: Option[Int],
                  info: GuildForwardAction)(implicit context: MessageContext): Future[Try[Boolean]]

}
