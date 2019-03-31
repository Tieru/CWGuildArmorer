package service.guild

import entity.player.GuildPlayer
import entity.user.UserEntity

import scala.concurrent.Future

trait GuildInfoService {

  def getGuildList(userId: Int): Future[Seq[GuildPlayer]]

  def getGuildMemberInfo(userId: Int, requestedUserId: Int): Future[UserEntity]
}
