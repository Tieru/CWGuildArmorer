package dao

import java.sql.Timestamp

import entity.guild.GuildEntity
import entity.player.Castle.Castle
import entity.player.HeroClass.HeroClass
import entity.player._
import slick.jdbc.PostgresProfile.api._
import entity.user.UserEntity
import javax.inject.Inject
import schema.data.Tables

import scala.concurrent.{ExecutionContext, Future}

class UserRepositoryImpl @Inject()(db: Database)(implicit ec: ExecutionContext) extends UserRepository with Tables {

  override val profile = slick.jdbc.PostgresProfile

  override def getOrCreateUser(userId: Int): Future[UserEntity] = {
    val query = Users.filter(user => user.id === userId).result.headOption

    db.run(query)
      .flatMap {
        case Some(existing) =>
          for {
            guild <- getGuild(existing.guildId)
            equipment <- getEquipment(existing.id)
          } yield UserEntity(existing.id.toString, makeHeroEntity(existing, equipment), guild)

        case None => createUser(userId)
      }
  }

  private def createUser(userId: Int): Future[UserEntity] = db.run {
    (Users returning Users.map(_.id) into ((_, id) => UserEntity(id.toString, None, None))) += UsersRow(userId)
  }

  override def updateProfile(userId: Int,
                             castle: Castle,
                             guildTag: Option[String],
                             username: String,
                             level: Int,
                             heroClass: HeroClass,
                             equipment: Seq[ItemInfo],
                             updateTime: Long): Future[UserEntity] = {
    val update = new Timestamp(updateTime)
    getGuildIdByTag(guildTag, castle)
      .flatMap { guildId =>
        val query = Users.filter(_.id === userId)
          .map(user => (user.castle, user.guildId, user.playerName, user.level, user.heroClass, user.lastUpdate))
          .update((Option(castle.toString), guildId, username, level, Option(heroClass.toString), Option(update)))

        db.run(query)
      }
      .flatMap(_ => updateEquipment(userId, equipment))
      .flatMap(_ => getOrCreateUser(userId))
  }

  override def getOrCreateGuild(castle: Castle,
                                guildName: Option[String] = None,
                                guildTag: String,
                                commander: Option[String] = None): Future[GuildEntity] = {
    val query = Guilds.filter(guild => guild.tag === guildTag).result.headOption
    db.run(query)
      .flatMap {
        case Some(guild) => updateGuildIfRequired(guild, guildName, commander)
        case None => createGuild(castle, guildName, guildTag, commander)
      }
  }

  override def updateGuild(userId: Int, guildId: Int): Future[GuildEntity] = {
    val query = Users.map(_.guildId).update(Option(guildId))
    db.run(query)
      .flatMap(_ => getGuild(guildId))
  }

  private def getGuild(guildId: Option[Int]): Future[Option[GuildEntity]] = {
    guildId match {
      case Some(id) => getGuild(id).map(guild => Option(guild))
      case None => Future.successful(None)
    }
  }

  private def getGuild(guildId: Int): Future[GuildEntity] = {
    val query = Guilds.filter(guild => guild.id === guildId).result.head
    db.run(query)
      .map(guild => GuildEntity(guild.id, Castle.withName(guild.castle), guild.name, guild.tag, guild.commander))
  }

  private def getGuildIdByTag(guildTag: Option[String], castle: Castle): Future[Option[Int]] = {
    guildTag match {
      case None => Future.successful(None)
      case Some(tag) => getOrCreateGuild(castle, None, tag, None).map(guild => Option(guild.id))
    }
  }

  private def updateGuildIfRequired(stored: GuildsRow, guildName: Option[String],
                                    commanderOpt: Option[String]): Future[GuildEntity] = {
    if (stored.commander == commanderOpt && stored.name == guildName) {
      val guild = GuildEntity(stored.id, Castle.withName(stored.castle), stored.name, stored.tag, stored.commander)
      Future.successful(guild)
    } else {
      val query = Guilds.filter(guild => guild.id === stored.id)
        .map(guild => (guild.name, guild.commander))
        .update((guildName, commanderOpt))
      db.run(query)
        .map(_ => GuildEntity(stored.id, Castle.withName(stored.castle), guildName, stored.tag, commanderOpt))
    }
  }

  private def createGuild(castle: Castle, guildName: Option[String], guildTag: String,
                          commander: Option[String]): Future[GuildEntity] = db.run {
    (Guilds.map(g => (g.castle, g.name, g.tag, g.commander))
      returning Guilds.map(_.id)
      into ((args, id) => GuildEntity(id, castle, args._2, args._3, args._4))
      ) += ((castle.toString, guildName, guildTag, commander))
  }

  private def updateEquipment(userId: Int, items: Seq[ItemInfo]): Future[Seq[ItemInfo]] = {
    db.run(PlayerItems.filter(user => user.userId === userId).delete)
      .flatMap { _ =>
        val data = items.map(item => PlayerItemsRow(userId, item.equipment.id, item.enhancement))
        val query = PlayerItems ++= data
        db.run(query)
          .map(_ => items)
      }
  }

  private def getEquipment(userId: Int): Future[Seq[ItemInfo]] = {
    val query = PlayerItems.filter(item => item.userId === userId).result
    db.run(query)
      .map(items => items.flatMap(item => Equipment.byId(item.itemId) match {
        case Some(equipment) => Option(ItemInfo(equipment, item.enchantment))
        case None => None
      }))
  }

  private def makeHeroEntity(userRow: UsersRow, equipment: Seq[ItemInfo]): Option[HeroEntity] = {
    userRow.lastUpdate match {
      case None => None
      case Some(update) =>
        val heroEntity = makeHeroEntity(userRow.castle.get,
          userRow.playerName,
          userRow.heroClass.get,
          userRow.level,
          equipment,
          update,
        )
        Option(heroEntity)
    }
  }

  private def makeHeroEntity(castle: String,
                             username: String,
                             heroClass: String,
                             level: Int,
                             equipment: Seq[ItemInfo],
                             lastUpdate: Timestamp): HeroEntity = {
    HeroEntity(Castle.withName(castle), username, HeroClass.withName(heroClass), level, equipment, lastUpdate)
  }
}
