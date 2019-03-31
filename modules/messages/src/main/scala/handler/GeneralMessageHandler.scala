package handler

import com.bot4s.telegram.api.declarative.Callbacks
import com.bot4s.telegram.models.{CallbackQuery, Message}
import entity._
import javax.inject.Inject
import org.parboiled2.ParseError
import parsers.MessageParser
import response.guild.GuildInfoMessageHandler
import response.profile.ProfileMessageHandler
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
      case _: OnGuildListAction => onGuildListRequest(msg)
      case profile: HeroForwardAction => onHeroForward(msg, profile)
      case guild: GuildForwardAction => onGuildInfoForward(msg, guild)
    }
  }

  private def processInlineCommand(action: UserAction, cbq: CallbackQuery)(implicit context: RequestContext, callback: Callbacks): Unit = {
  }

  private def onStart(msg: Message)(implicit context: MessageContext): Unit = {
    val handler = handlerProvider.provide[ProfileMessageHandler]()
    handler.onStart(msg.from.get.id)
  }

  private def onHeroForward(msg: Message, profile: HeroForwardAction)(implicit context: MessageContext): Unit = {
    val handler = handlerProvider.provide[ProfileMessageHandler]()
    handler.onHeroInfo(msg.from.get.id, msg.forwardFrom, msg.forwardDate, profile)
  }

  private def onGuildInfoForward(msg: Message, guild: GuildForwardAction)(implicit context: MessageContext): Unit = {
    val handler = handlerProvider.provide[ProfileMessageHandler]()
    handler.onGuildInfo(msg.from.get.id, msg.forwardFrom, msg.forwardDate, guild)
  }

  private def onGuildListRequest(msg: Message)(implicit context: MessageContext): Unit = {
    val handler = handlerProvider.provide[GuildInfoMessageHandler]()
    handler.showGuildList(msg.from.get.id)
  }
}
