package handler

import com.bot4s.telegram.api.declarative.Callbacks
import com.bot4s.telegram.models.{CallbackQuery, Message}
import entity.{OnStartAction, UserAction}
import javax.inject.Inject
import org.parboiled2.ParseError
import parsers.MessageParser
import response.registration.ProfileMessageHandler
import response.{MessageContext, RequestContext}
import slogging.LazyLogging

import scala.util.{Failure, Success}

class GeneralMessageHandler @Inject()(private val handlerProvider: HandlerProvider) extends LazyLogging {

  def message(message: Message)(implicit context: MessageContext): Unit = {
    val text = message.text.getOrElse("")
    MessageParser(text).Input.run() match {
      case Success(action) => processMessage(action, message)
      case Failure(e: ParseError) => logger.debug(s"On command parsing error: $e")
      case Failure(t) => logger.debug(s"On command handling error: $t")
    }
  }

  def query(cbq: CallbackQuery)(implicit context: RequestContext, callback: Callbacks): Unit = {
    val text = cbq.data.getOrElse("")
    MessageParser(text).Input.run() match {
      case Success(action) => processInlineCommand(action, cbq)
      case Failure(e: ParseError) => logger.debug(s"On cb query parsing error: $e")
      case Failure(t) => logger.debug(s"On cb query handling error: $t")
    }
  }

  private def processMessage(action: UserAction, msg: Message)(implicit context: MessageContext): Unit = {
    action match {
      case _: OnStartAction => onStart(msg)
    }
  }

  private def processInlineCommand(action: UserAction, cbq: CallbackQuery)(implicit context: RequestContext, callback: Callbacks): Unit = {
  }

  private def onStart(msg: Message)(implicit context: MessageContext): Unit = {
    val handler = handlerProvider.provide[ProfileMessageHandler]()
    handler.onStart(msg.from.get.id)
  }
}
