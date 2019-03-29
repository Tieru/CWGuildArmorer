package entity.user

import entity.player.HeroEntity

case class UserEntity
(
  id: String,
  hero: Option[HeroEntity],
)
