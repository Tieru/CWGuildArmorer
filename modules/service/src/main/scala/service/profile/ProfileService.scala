package service.profile

import entity.guild.GuildEntity
import entity.player.HeroEntity
import entity.user.UserEntity
import entity.{GuildForwardAction, HeroForwardAction}

import scala.concurrent.Future

trait ProfileService {

  def onStart(userId: Int): Future[UserEntity]

  def onProfileUpdate(userId: Int,
                      forwardFrom: Option[Int],
                      forwardDate: Option[Int],
                      info: HeroForwardAction): Future[HeroEntity]

  def onGuildUpdate(userId: Int,
                    forwardFrom: Option[Int],
                    forwardDate: Option[Int],
                    info: GuildForwardAction): Future[GuildEntity]
}
