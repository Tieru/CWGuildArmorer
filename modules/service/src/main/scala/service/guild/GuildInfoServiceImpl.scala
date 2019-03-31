package service.guild

import entity.player.GuildPlayer
import javax.inject.Inject

import scala.concurrent.{ExecutionContext, Future}

class GuildInfoServiceImpl @Inject()()(implicit ec: ExecutionContext) extends GuildInfoService {

  override def getGuildList(userId: Int): Future[Seq[GuildPlayer]] = ???
  
}
