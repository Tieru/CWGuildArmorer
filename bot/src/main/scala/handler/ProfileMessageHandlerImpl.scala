package handler

import com.bot4s.telegram.models.User
import com.osinka.i18n.{Lang, Messages}
import entity.{GuildForwardAction, HeroForwardAction}
import javax.inject.Inject
import response.MessageContext
import response.profile.ProfileMessageHandler
import service.profile.ProfileService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Try}

class ProfileMessageHandlerImpl @Inject()(service: ProfileService)(implicit ec: ExecutionContext) extends ProfileMessageHandler {

  private implicit val lang: Lang = Lang.apply("ru")

  override def onStart(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]] = {
    service.onStart(userId)
      .map(user => user.hero match {
        case Some(_) => context.reply(Messages("start.welcome"))
        case None =>
      })
      .map(_ => Success(true))
  }

  override def onHeroInfo(userId: Int,
                          forwardFrom: Option[User],
                          forwardDate: Option[Int],
                          info: HeroForwardAction)(implicit context: MessageContext): Future[Try[Boolean]] = {
    val forwardFromId = userToId(forwardFrom)

    service.onProfileUpdate(userId, forwardFromId, forwardDate, info)
      .map(_ => context.reply(Messages("hero.update.success")))
      .map(_ => Success(true))
  }

  override def onGuildInfo(userId: Int,
                           forwardFrom: Option[User],
                           forwardDate: Option[Int],
                           info: GuildForwardAction)(implicit context: MessageContext): Future[Try[Boolean]] = {
    val forwardFromId = userToId(forwardFrom)

    service.onGuildUpdate(userId, forwardFromId, forwardDate, info)
      .map(_ => context.reply(Messages("guild.update.success")))
      .map(_ =>
        Success(true))
  }

  private def userToId(user: Option[User]) = user match {
    case Some(value) => Option(value.id)
    case None => None
  }
}
