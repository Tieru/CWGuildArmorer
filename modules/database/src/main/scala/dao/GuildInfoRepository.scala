package dao

import entity.player.GuildPlayer

import scala.concurrent.Future

trait GuildInfoRepository {

  def getGuildMembers(guildId: Int): Future[Seq[GuildPlayer]]

}
