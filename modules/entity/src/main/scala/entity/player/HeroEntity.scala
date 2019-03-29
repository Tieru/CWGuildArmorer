package entity.player

import java.sql.Timestamp

import entity.guild.GuildEntity
import entity.player.Castle.Castle
import entity.player.HeroClass.HeroClass

case class HeroEntity
(
  castle: Castle,
  username: String,
  heroClass: HeroClass,
  level: Int,
  guild: Option[GuildEntity],
  equipment: Seq[ItemInfo],
  lastUpdate: Timestamp,
)
