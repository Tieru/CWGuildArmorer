package dao

import entity.player.GuildPlayer
import javax.inject.Inject
import slick.jdbc.PostgresProfile.api._
import schema.data.Tables

import scala.concurrent.{ExecutionContext, Future}

class GuildInfoRepositoryImpl @Inject()(db: Database)(implicit ec: ExecutionContext) extends GuildInfoRepository with Tables {

  override val profile = slick.jdbc.PostgresProfile

  override def getGuildMembers(guildId: Int): Future[Seq[GuildPlayer]] = {
    val query = Users.filter(_.guildId === guildId).result
    db.run(query)
      .map(_.map(user => GuildPlayer(user.id, user.playerName)))
  }

}
