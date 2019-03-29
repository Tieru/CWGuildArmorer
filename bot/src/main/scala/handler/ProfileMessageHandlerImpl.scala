package handler

import com.bot4s.telegram.models.User
import com.osinka.i18n.{Lang, Messages}
import entity.HeroForwardAction
import javax.inject.Inject
import response.MessageContext
import response.profile.ProfileMessageHandler

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Try}

class ProfileMessageHandlerImpl @Inject()()(implicit ec: ExecutionContext) extends ProfileMessageHandler {

  private implicit val lang: Lang = Lang.apply("ru")

  override def onStart(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]] = {
    context.reply(Messages("start.welcome"))
    Future.successful(Success(true))
  }


  override def onHeroInfo(userId: Int,
                          forwardFrom: Option[User],
                          forwardDate: Option[Int],
                          info: HeroForwardAction)(implicit context: MessageContext): Future[Try[Boolean]] = {
    Future.successful(Success(true))
  }
}
