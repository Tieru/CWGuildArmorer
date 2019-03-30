package entity.player

import java.sql.Timestamp

import entity.player.Castle.Castle
import entity.player.HeroClass.HeroClass

case class HeroEntity
(
  castle: Castle,
  username: String,
  heroClass: HeroClass,
  level: Int,
  equipment: Seq[ItemInfo],
  lastUpdate: Timestamp,
)
