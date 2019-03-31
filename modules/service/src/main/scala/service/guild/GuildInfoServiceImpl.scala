package service.guild

import dao.{GuildInfoRepository, UserRepository}
import entity.player.GuildPlayer
import entity.user.UserEntity
import javax.inject.Inject
import service.error.ErrorRecoverExtensions._
import service.error.{AppException, ErrorCode, ErrorInfo}

import scala.concurrent.{ExecutionContext, Future}

class GuildInfoServiceImpl @Inject()(private val userRepository: UserRepository,
                                     private val guildInfoRepository: GuildInfoRepository)(implicit ec: ExecutionContext) extends GuildInfoService {

  override def getGuildList(userId: Int): Future[Seq[GuildPlayer]] = {
    userRepository.getOrCreateUser(userId)
      .flatMap { user =>
        user.guild match {
          case Some(guild) => guildInfoRepository.getGuildMembers(guild.id)
          case None => Future.failed(AppException(ErrorInfo(ErrorCode.NotInGuild)))
        }
      }
  }

  override def getGuildMemberInfo(userId: Int, requestedUserId: Int): Future[UserEntity] = {
    doGetGuildMemberInfo(userId, requestedUserId)
      .recoverWithDefaultError()
  }

  private def doGetGuildMemberInfo(userId: Int, requestedUserId: Int): Future[UserEntity] = {
    for {
      user <- userRepository.getOrCreateUser(userId)
      requested <- userRepository.getOrCreateUser(requestedUserId)
      response <- processUserInfoRequest(user, requested)
    } yield response
  }

  private def processUserInfoRequest(user: UserEntity, requested: UserEntity): Future[UserEntity] = {
    if (user.guild.isEmpty) {
      Future.failed(AppException(ErrorInfo(ErrorCode.NotInGuild)))
    }
    else if (requested.guild.isEmpty || requested.guild.get.id != user.guild.get.id) {
      Future.failed(AppException(ErrorInfo(ErrorCode.NotInYourGuild)))
    }
    else if (requested.hero.isEmpty) {
      Future.failed(AppException(ErrorInfo(ErrorCode.NoHeroInfo)))
    } else {
      Future.successful(requested)
    }
  }
}
