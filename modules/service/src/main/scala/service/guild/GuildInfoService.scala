package service.guild

import entity.player.GuildPlayer

import scala.concurrent.Future

trait GuildInfoService {

  def getGuildList(userId: Int): Future[Seq[GuildPlayer]]

}
