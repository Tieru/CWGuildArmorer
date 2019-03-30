package dao

import entity.guild.GuildEntity
import entity.player.Castle.Castle
import entity.player.HeroClass.HeroClass
import entity.player.ItemInfo
import entity.user.UserEntity

import scala.concurrent.Future

trait UserRepository {

  def getOrCreateUser(userId: Int): Future[UserEntity]

  def updateProfile(userId: Int,
                    castle: Castle,
                    guildTag: Option[String],
                    username: String,
                    level: Int,
                    heroClass: HeroClass,
                    equipment: Seq[ItemInfo],
                    updateTime: Long): Future[UserEntity]

  def getOrCreateGuild(castle: Castle,
                       guildName: Option[String],
                       guildTag: String,
                       commander: Option[String]): Future[GuildEntity]

  def updateGuild(userId: Int,
                  guildId: Int): Future[GuildEntity]
}
