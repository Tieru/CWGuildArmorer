package handler

import com.osinka.i18n.{Lang, Messages}
import javax.inject.Inject
import response.MessageContext
import response.registration.ProfileMessageHandler

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Try}

class ProfileMessageHandlerImpl @Inject()()(implicit ec: ExecutionContext) extends ProfileMessageHandler {

  private implicit val lang: Lang = Lang.apply("ru")

  override def onStart(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]] = {
    context.reply(Messages("start.welcome"))
    Future.successful(Success(true))
  }
}
