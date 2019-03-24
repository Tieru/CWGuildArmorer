package entity

import entity.player.Castle.Castle
import entity.player.HeroClass.HeroClass
import entity.player.ItemInfo


sealed trait UserAction

sealed trait UserCommandAction extends UserAction

class OnStartAction extends UserCommandAction

sealed trait UserForwardAction extends UserAction

case class HeroForwardAction
(
  castle: Castle,
  guildTag: Option[String],
  username: String,
  level: Int,
  heroClass: HeroClass,
  equipment: Seq[ItemInfo],
) extends UserForwardAction

case class GuildForwardAction
(
  castle: Castle,
  guildTag: String,
  guildName: String,
  commander: String,
) extends UserForwardAction
