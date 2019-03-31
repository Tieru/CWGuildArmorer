package service.profile

import dao.UserRepository
import entity.guild.GuildEntity
import entity.player.HeroEntity
import entity.user.UserEntity
import entity.{GuildForwardAction, HeroForwardAction}
import javax.inject.Inject
import service.error.ErrorRecoverExtensions._
import service.error.{AppException, ErrorCode, ErrorInfo}

import scala.concurrent.{ExecutionContext, Future}

class ProfileServiceImpl @Inject()(userRepository: UserRepository)(implicit ec: ExecutionContext) extends ProfileService {

  private val BOT_ID = 265204902

  override def onStart(userId: Int): Future[UserEntity] = {
    userRepository.getOrCreateUser(userId)
      .recoverWithDefaultError()
  }

  override def onProfileUpdate(userId: Int,
                               forwardFrom: Option[Int],
                               forwardDate: Option[Int],
                               info: HeroForwardAction): Future[HeroEntity] = {
    validateIsChatWarsBot(forwardFrom)

    userRepository.getOrCreateUser(userId)
      .flatMap { user =>
        var lastUpdate: Option[Long] = None
        if (user.hero.isDefined) {
          lastUpdate = Option(user.hero.get.lastUpdate.getTime)
        }

        val updateTime = validateForwardDate(forwardDate, lastUpdate)
        userRepository.updateProfile(userId, info.castle, info.guildTag, info.username, info.level, info.heroClass, info.equipment, updateTime)
      }
      .map(user => user.hero.getOrElse(throw AppException(ErrorInfo(ErrorCode.NoDataAvailable))))
      .recoverWithDefaultError()
  }

  override def onGuildUpdate(userId: Int, forwardFrom: Option[Int], forwardDate: Option[Int], info: GuildForwardAction): Future[GuildEntity] = {
    validateIsChatWarsBot(forwardFrom)

    userRepository.getOrCreateGuild(info.castle, Option(info.guildName), info.guildTag, Option(info.commander))
      .flatMap(guild => userRepository.updateGuild(userId, guild.id))
      .recoverWithDefaultError()
  }

  private def validateIsChatWarsBot(forwardFrom: Option[Int]): Int = {
    val id = forwardFrom.getOrElse(-1)

    if (id != BOT_ID) {
      throw AppException(ErrorInfo(ErrorCode.ForwardFromWrongUser))
    }

    id
  }

  private def validateForwardDate(forwardDate: Option[Int], storedDate: Option[Long]): Long = {
    forwardDate match {
      case None => throw AppException(ErrorInfo(ErrorCode.Unknown))
      case Some(value) =>
        val forward = value.toLong * 1000
        checkForwardDateIsNewerThanStored(forward, storedDate)
        forward
    }
  }

  private def checkForwardDateIsNewerThanStored(forward: Long, storedDate: Option[Long]): Unit = {
    storedDate match {
      case Some(stored) => if (forward < stored) {
        throw AppException(ErrorInfo(ErrorCode.NewerRecordAlreadyExists))
      }
      case None => Unit
    }
  }
}
