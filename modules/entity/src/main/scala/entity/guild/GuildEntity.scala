package entity.guild

import entity.player.Castle.Castle

case class GuildEntity
(
  id: Int,
  castle: Castle,
  name: Option[String],
  tag: String,
  commander: Option[String],
)
