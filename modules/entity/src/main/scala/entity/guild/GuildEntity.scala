package entity.guild

import entity.player.Castle.Castle

case class GuildEntity
(
  id: String,
  castle: Castle,
  name: String,
  tag: String,
  commanderId: String,
)
