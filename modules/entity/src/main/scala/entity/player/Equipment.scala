package entity.player

case class ItemInfo(equipment: Equipment, enhancement: Int)

sealed abstract class Equipment(id: Int, name: String, attack: Int = 0, defence: Int = 0, stamina: Int = 0, storage: Int = 0)

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

  private val items = Map(
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

  def byName(name: String): Option[Equipment] = {
    items.get(name)
  }
}
