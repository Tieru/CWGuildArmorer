package entity.player

sealed class Equipment(val id: Int, val name: String, val attack: Int = 0, val defence: Int = 0, val stamina: Int = 0, val storage: Int = 0)

case class ItemInfo(equipment: Equipment, enhancement: Int)

case object ChampionSword extends Equipment(1, "Champion Sword", 31)

case object GhostDagger extends Equipment(1, "Ghost dagger", 13, 2)

case object HunterHelmet extends Equipment(2, "Hunter Helmet", 5, 11)

case object GhostGloves extends Equipment(3, "Ghost Gloves", 5, 10)

case object HunterArmor extends Equipment(4, "Hunter Armor", 8, 18)

case object LionBoots extends Equipment(5, "Lion Boots", 6, 9)

case object Saddlebag extends Equipment(6, "Saddlebag", storage = 5)

case object Flask extends Equipment(7, "Flask", stamina = 2)

case object RoyalGuardCape extends Equipment(8, "Royal Guard Cape", 1, 1)

object Equipment {

  private val mapByName = Map(
    "Champion Sword" -> ChampionSword,
    "Ghost dagger" -> GhostDagger,
    "Hunter Helmet" -> HunterHelmet,
    "Ghost Gloves" -> GhostGloves,
    "Hunter Armor" -> HunterArmor,
    "Lion Boots" -> LionBoots,
    "Saddlebag" -> Saddlebag,
    "Flask" -> Flask,
    "Royal Guard Cape" -> RoyalGuardCape,
  )

  private val mapById = Map(
    1 -> ChampionSword,
    2 -> GhostDagger,
    3 -> HunterHelmet,
    4 -> GhostGloves,
    5 -> HunterArmor,
    6 -> LionBoots,
    7 -> Saddlebag,
    8 -> Flask,
    9 -> RoyalGuardCape,
  )

  def byName(name: String): Option[Equipment] = mapByName.get(name)

  def byId(id: Int): Option[Equipment] = mapById.get(id)
}
