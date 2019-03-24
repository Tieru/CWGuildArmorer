package response.registration

import response.MessageContext

import scala.concurrent.Future
import scala.util.Try

trait ProfileMessageHandler {

  def onStart(userId: Int)(implicit context: MessageContext): Future[Try[Boolean]]

}
