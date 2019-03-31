package service.guild

import dao.{GuildInfoRepository, UserRepository}
import entity.player.GuildPlayer
import entity.user.UserEntity
import javax.inject.Inject
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

  override def getGuildMemberInfo(userId: Int, requestedUserId: Int): Future[UserEntity] = ???
}
