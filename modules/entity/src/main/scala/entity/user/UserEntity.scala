package entity.user

import entity.guild.GuildEntity
import entity.player.HeroEntity

case class UserEntity
(
  id: String,
  hero: Option[HeroEntity],
  guild: Option[GuildEntity],
)
