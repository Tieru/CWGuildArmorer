package entity.player

sealed class Equipment(val id: Int, val name: String, val attack: Int = 0, val defence: Int = 0, val mana: Int = 0, val stamina: Int = 0, val storage: Int = 0)

case class ItemInfo(equipment: Equipment, enhancement: Int)


case object Saddlebag extends Equipment(6, "Saddlebag", storage = 5)

case object Flask extends Equipment(7, "Flask", stamina = 2)

case object RoyalGuardCape extends Equipment(8, "Royal Guard Cape", 1, 1)

case object OrderArmor extends Equipment(27, "Order Armor", 5, 25)

case object OrderHelmet extends Equipment(28, "Order Helmet", 3, 15)

case object OrderBoots extends Equipment(29, "Order Boots", 2, 10)

case object OrderGauntlets extends Equipment(30, "Order Gauntlets", 2, 10)

case object OrderShield extends Equipment(31, "Order Shield", defence = 10)

case object HunterArmor extends Equipment(32, "Hunter Armor", 8, 18)

case object HunterHelmet extends Equipment(33, "Hunter Helmet", 5, 11)

case object HunterBoots extends Equipment(34, "Hunter Boots", 3, 8)

case object HunterGloves extends Equipment(35, "Hunter Gloves", 3, 8)

case object ClarityRobe extends Equipment(36, "Clarity Robe", 4, 20, mana = 120)

case object ClarityCirclet extends Equipment(37, "Clarity Circlet", 2, 12, mana = 80)

case object ClarityShoes extends Equipment(38, "Clarity Shoes", 1, 9, mana = 40)

case object ClarityBracers extends Equipment(39, "Clarity Bracers", 1, 9, mana = 40)

case object VeteransCloak extends Equipment(44, "Veteran's Cloak")

case object CrusaderArmor extends Equipment(45, "Crusader Armor", 10, 32)

case object CrusaderHelmet extends Equipment(46, "Crusader Helmet", 6, 19)

case object CrusaderBoots extends Equipment(47, "Crusader Boots", 4, 12)

case object CrusaderGauntlets extends Equipment(48, "Crusader Gauntlets", 4, 12)

case object CrusaderShield extends Equipment(49, "Crusader Shield", 1, 12)

case object RoyalArmor extends Equipment(50, "Royal Armor", 8, 34)

case object RoyalHelmet extends Equipment(51, "Royal Helmet", 5, 20)

case object RoyalBoots extends Equipment(52, "Royal Boots", 3, 13)

case object RoyalGauntlets extends Equipment(53, "Royal Gauntlets", 3, 13)

case object RoyalShield extends Equipment(54, "Royal Shield", defence = 13)

case object GhostArmor extends Equipment(55, "Ghost Armor", 12, 26)

case object GhostHelmet extends Equipment(56, "Ghost Helmet", 7, 15)

case object GhostBoots extends Equipment(57, "Ghost Boots", 5, 10)

case object GhostGloves extends Equipment(58, "Ghost Gloves", 5, 10)

case object LionArmor extends Equipment(59, "Lion Armor", 14, 24)

case object LionHelmet extends Equipment(60, "Lion Helmet", 8, 14)

case object LionBoots extends Equipment(61, "Lion Boots", 6, 9)

case object LionGloves extends Equipment(62, "Lion Gloves", 6, 9)

case object DemonRobe extends Equipment(63, "Demon Robe", 9, 25, mana = 165)

case object DemonCirclet extends Equipment(64, "Demon Circlet", 5, 15, mana = 110)

case object DemonShoes extends Equipment(65, "Demon Shoes", 4, 10, mana = 55)

case object DemonBracers extends Equipment(66, "Demon Bracers", 4, 10, mana = 55)

case object DivineRobe extends Equipment(67, "Divine Robe", 8, 26, mana = 165)

case object DivineCirclet extends Equipment(68, "Divine Circlet", 4, 15, mana = 110)

case object DivineShoes extends Equipment(69, "Divine Shoes", 3, 11, mana = 55)

case object DivineBracers extends Equipment(70, "Divine Bracers", 3, 11, mana = 55)

case object StormCloak extends Equipment(71, "Storm Cloak", 4, 3)

case object DurableCloak extends Equipment(72, "Durable Cloak", 3, 4)

case object BlessedCloak extends Equipment(73, "Blessed Cloak", 2, 2, mana = 50)

case object HikingJar extends Equipment(74, "Hiking Jar")

case object HikingBag extends Equipment(75, "Hiking Bag")

case object ChampionSword extends Equipment(128, "Champion Sword", 31)

case object Trident extends Equipment(129, "Trident", 16, 14)

case object HunterBow extends Equipment(130, "Hunter bow", 22, 8)

case object WarHammer extends Equipment(131, "War Hammer", 15, 15)

case object HunterDagger extends Equipment(132, "Hunter dagger", 10)

case object Eclipse extends Equipment(135, "Eclipse", 37)

case object GuardsSpear extends Equipment(136, "Guard's Spear", 18, 16)

case object KingsDefender extends Equipment(137, "King’s Defender", 18, 17)

case object RagingLance extends Equipment(138, "Raging Lance", 19, 16)

case object CompositeBow extends Equipment(139, "Composite Bow", 25, 9)

case object LightningBow extends Equipment(140, "Lightning Bow", 27, 8)

case object HailstormBow extends Equipment(141, "Hailstorm Bow", 24, 11)

case object ImperialAxe extends Equipment(142, "Imperial Axe", 17, 17)

case object SkullCrusher extends Equipment(143, "Skull Crusher", 18, 17)

case object DragonMace extends Equipment(144, "Dragon Mace", 17, 18)

case object GhostDagger extends Equipment(145, "Ghost dagger", 13, 2)

case object LionKnife extends Equipment(146, "Lion Knife", 13)


//A76 Stick of Wisdom
//cw1 ⚡️+5 Kitchen knife
//e147 🧟‍♂ Manwolf Knife
//e148 🧟‍♂ Fleder Scimitar +34⚔️


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

    "Saddlebag" -> Saddlebag,
    "Flask" -> Flask,
    "Royal Guard Cape" -> RoyalGuardCape,
    "Order Armor" -> OrderArmor,
    "Order Helmet" -> OrderHelmet,
    "Order Boots" -> OrderBoots,
    "Order Gauntlets" -> OrderGauntlets,
    "Order Shield" -> OrderShield,
    "Hunter Armor" -> HunterArmor,
    "Hunter Helmet" -> HunterHelmet,
    "Hunter Boots" -> HunterBoots,
    "Hunter Gloves" -> HunterGloves,
    "Clarity Robe" -> ClarityRobe,
    "Clarity Circlet" -> ClarityCirclet,
    "Clarity Shoes" -> ClarityShoes,
    "Clarity Bracers" -> ClarityBracers,
    "Veteran's Cloak" -> VeteransCloak,
    "Crusader Armor" -> CrusaderArmor,
    "Crusader Helmet" -> CrusaderHelmet,
    "Crusader Boots" -> CrusaderBoots,
    "Crusader Gauntlets" -> CrusaderGauntlets,
    "Crusader Shield" -> CrusaderShield,
    "Royal Armor" -> RoyalArmor,
    "Royal Helmet" -> RoyalHelmet,
    "Royal Boots" -> RoyalBoots,
    "Royal Gauntlets" -> RoyalGauntlets,
    "Royal Shield" -> RoyalShield,
    "Ghost Armor" -> GhostArmor,
    "Ghost Helmet" -> GhostHelmet,
    "Ghost Boots" -> GhostBoots,
    "Ghost Gloves" -> GhostGloves,
    "Lion Armor" -> LionArmor,
    "Lion Helmet" -> LionHelmet,
    "Lion Boots" -> LionBoots,
    "Lion Gloves" -> LionGloves,
    "Demon Robe" -> DemonRobe,
    "Demon Circlet" -> DemonCirclet,
    "Demon Shoes" -> DemonShoes,
    "Demon Bracers" -> DemonBracers,
    "Divine Robe" -> DivineRobe,
    "Divine Circlet" -> DivineCirclet,
    "Divine Shoes" -> DivineShoes,
    "Divine Bracers" -> DivineBracers,
    "Storm Cloak" -> StormCloak,
    "Durable Cloak" -> DurableCloak,
    "Blessed Cloak" -> BlessedCloak,
    "Hiking Jar" -> HikingJar,
    "Hiking Bag" -> HikingBag,
    "Champion Sword" -> ChampionSword,
    "Trident" -> Trident,
    "Hunter bow" -> HunterBow,
    "War Hammer" -> WarHammer,
    "Hunter dagger" -> HunterDagger,
    "Eclipse" -> Eclipse,
    "Guard's Spear" -> GuardsSpear,
    "King’s Defender" -> KingsDefender,
    "Raging Lance" -> RagingLance,
    "Composite Bow" -> CompositeBow,
    "Lightning Bow" -> LightningBow,
    "Hailstorm Bow" -> HailstormBow,
    "Imperial Axe" -> ImperialAxe,
    "Skull Crusher" -> SkullCrusher,
    "Dragon Mace" -> DragonMace,
    "Ghost dagger" -> GhostDagger,
    "Lion Knife" -> LionKnife,
  )

  private val mapById = Map(
    6 -> Saddlebag,
    7 -> Flask,
    8 -> RoyalGuardCape,
    27 -> OrderArmor,
    28 -> OrderHelmet,
    29 -> OrderBoots,
    30 -> OrderGauntlets,
    31 -> OrderShield,
    32 -> HunterArmor,
    33 -> HunterHelmet,
    34 -> HunterBoots,
    35 -> HunterGloves,
    36 -> ClarityRobe,
    37 -> ClarityCirclet,
    38 -> ClarityShoes,
    39 -> ClarityBracers,
    44 -> VeteransCloak,
    45 -> CrusaderArmor,
    46 -> CrusaderHelmet,
    47 -> CrusaderBoots,
    48 -> CrusaderGauntlets,
    49 -> CrusaderShield,
    50 -> RoyalArmor,
    51 -> RoyalHelmet,
    52 -> RoyalBoots,
    53 -> RoyalGauntlets,
    54 -> RoyalShield,
    55 -> GhostArmor,
    56 -> GhostHelmet,
    57 -> GhostBoots,
    58 -> GhostGloves,
    59 -> LionArmor,
    60 -> LionHelmet,
    61 -> LionBoots,
    62 -> LionGloves,
    63 -> DemonRobe,
    64 -> DemonCirclet,
    65 -> DemonShoes,
    66 -> DemonBracers,
    67 -> DivineRobe,
    68 -> DivineCirclet,
    69 -> DivineShoes,
    70 -> DivineBracers,
    71 -> StormCloak,
    72 -> DurableCloak,
    73 -> BlessedCloak,
    74 -> HikingJar,
    75 -> HikingBag,
    128 -> ChampionSword,
    129 -> Trident,
    130 -> HunterBow,
    131 -> WarHammer,
    132 -> HunterDagger,
    135 -> Eclipse,
    136 -> GuardsSpear,
    137 -> KingsDefender,
    138 -> RagingLance,
    139 -> CompositeBow,
    140 -> LightningBow,
    141 -> HailstormBow,
    142 -> ImperialAxe,
    143 -> SkullCrusher,
    144 -> DragonMace,
    145 -> GhostDagger,
    146 -> LionKnife,
  )

  def byName(name: String): Option[Equipment] = mapByName.get(name)

  def byId(id: Int): Option[Equipment] = mapById.get(id)
}
