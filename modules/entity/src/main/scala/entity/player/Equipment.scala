package entity.player

sealed class Equipment(val id: String, val name: String, val attack: Int = 0, val defence: Int = 0, val mana: Int = 0, val stamina: Int = 0, val storage: Int = 0)

case class ItemInfo(equipment: Equipment, enhancement: Int)

case object MithrilArmor extends Equipment("a05", "Mithril armor")
case object MithrilHelmet extends Equipment("a10", "Mithril helmet")
case object MithrilBoots extends Equipment("a15", "Mithril boots")
case object MithrilGauntlets extends Equipment("a20", "Mithril gauntlets")
case object MithrilShield extends Equipment("a25", "Mithril shield")

case object Saddlebag extends Equipment("a42", "Saddlebag", storage = 5)
case object Flask extends Equipment("a43", "Flask", stamina = 2)
case object RoyalGuardCape extends Equipment("a26", "Royal Guard Cape", 1, 1)

case object OrderArmor extends Equipment("a27", "Order Armor", 5, 25)
case object OrderHelmet extends Equipment("a28", "Order Helmet", 3, 15)
case object OrderBoots extends Equipment("a29", "Order Boots", 2, 10)
case object OrderGauntlets extends Equipment("a30", "Order Gauntlets", 2, 10)
case object OrderShield extends Equipment("a31", "Order Shield", 10)

case object HunterArmor extends Equipment("a32", "Hunter Armor", 8, 18)
case object HunterHelmet extends Equipment("a33", "Hunter Helmet", 5, 11)
case object HunterBoots extends Equipment("a34", "Hunter Boots", 3, 8)
case object HunterGloves extends Equipment("a35", "Hunter Gloves", 3, 8)

case object ClarityRobe extends Equipment("a36", "Clarity Robe", 4, 20, mana = 120)
case object ClarityCirclet extends Equipment("a37", "Clarity Circlet", 2, 12, mana = 80)
case object ClarityShoes extends Equipment("a38", "Clarity Shoes", 1, 9, mana = 40)
case object ClarityBracers extends Equipment("a39", "Clarity Bracers", 1, 9, mana = 40)

case object VeteransCloak extends Equipment("a44", "Veteran's Cloak")

case object CrusaderArmor extends Equipment("a45", "Crusader Armor", 10, 32)
case object CrusaderHelmet extends Equipment("a46", "Crusader Helmet", 6, 19)
case object CrusaderBoots extends Equipment("a47", "Crusader Boots", 4, 12)
case object CrusaderGauntlets extends Equipment("a48", "Crusader Gauntlets", 4, 12)
case object CrusaderShield extends Equipment("a49", "Crusader Shield", 1, 12)

case object RoyalArmor extends Equipment("a50", "Royal Armor", 8, 34)
case object RoyalHelmet extends Equipment("a51", "Royal Helmet", 5, 20)
case object RoyalBoots extends Equipment("a52", "Royal Boots", 3, 13)
case object RoyalGauntlets extends Equipment("a53", "Royal Gauntlets", 3, 13)
case object RoyalShield extends Equipment("a54", "Royal Shield", defence = 13)

case object GhostArmor extends Equipment("a55", "Ghost Armor", 12, 26)
case object GhostHelmet extends Equipment("a56", "Ghost Helmet", 7, 15)
case object GhostBoots extends Equipment("a57", "Ghost Boots", 5, 10)
case object GhostGloves extends Equipment("a58", "Ghost Gloves", 5, 10)

case object LionArmor extends Equipment("a59", "Lion Armor", 14, 24)
case object LionHelmet extends Equipment("a60", "Lion Helmet", 8, 14)
case object LionBoots extends Equipment("a61", "Lion Boots", 6, 9)
case object LionGloves extends Equipment("a62", "Lion Gloves", 6, 9)

case object DemonRobe extends Equipment("a63", "Demon Robe", 9, 25, mana = 165)
case object DemonCirclet extends Equipment("a64", "Demon Circlet", 5, 15, mana = 110)
case object DemonShoes extends Equipment("a65", "Demon Shoes", 4, 10, mana = 55)
case object DemonBracers extends Equipment("a66", "Demon Bracers", 4, 10, mana = 55)

case object DivineRobe extends Equipment("a67", "Divine Robe", 8, 26, mana = 165)
case object DivineCirclet extends Equipment("a68", "Divine Circlet", 4, 15, mana = 110)
case object DivineShoes extends Equipment("a69", "Divine Shoes", 3, 11, mana = 55)
case object DivineBracers extends Equipment("a70", "Divine Bracers", 3, 11, mana = 55)

case object StormCloak extends Equipment("a71", "Storm Cloak", 4, 3)
case object DurableCloak extends Equipment("a72", "Durable Cloak", 3, 4)
case object BlessedCloak extends Equipment("a73", "Blessed Cloak", 2, 2, mana = 50)
case object HikingJar extends Equipment("a74", "Hiking Jar")
case object HikingBag extends Equipment("a75", "Hiking Bag")

case object CouncilArmor extends Equipment("a78", "Council Armor", 13, 42)
case object CouncilHelmet extends Equipment("a79", "Council Helmet", 8, 25)
case object CouncilBoots extends Equipment("a80", "Council Boots", 5, 15)
case object CouncilGauntlets extends Equipment("a81", "Council Gauntlets", 5, 15)
case object CouncilShield extends Equipment("a82", "Council Shield", 0, 15)

case object GriffinArmor extends Equipment("a83", "Griffin Armor", 16, 34)
case object GriffinHelmet extends Equipment("a84", "Griffin Helmet", 11, 18)
case object GriffinBoots extends Equipment("85", "Griffin Boots", 7, 12)
case object GriffinGloves extends Equipment("a86", "Griffin Gloves", 7, 12)

case object CelestialArmor extends Equipment("a87", "Celestial Armor", 11, 34)
case object CelestialHelmet extends Equipment("a88", "Celestial Helmet", 6, 20)
case object CelestialBoots extends Equipment("89", "Celestial Boots", 5, 13)
case object CelestialBracers extends Equipment("a90", "Celestial Bracers", 5, 13)

case object AssaultCape extends Equipment("a100", "Assault Cape", 6, 3)
case object CraftsmanApron extends Equipment("a101", "Craftsman Apron", 3, 3)
case object StoneskinCloak extends Equipment("a102", "Stoneskin Cloak", 3, 6)

case object ChampionSword extends Equipment("w28", "Champion Sword", 31)
case object Trident extends Equipment("w29", "Trident", 16, 14)
case object HunterBow extends Equipment("w30", "Hunter bow", 22, 8)
case object WarHammer extends Equipment("w31", "War Hammer", 15, 15)
case object HunterDagger extends Equipment("w32", "Hunter dagger", 10)
case object Eclipse extends Equipment("w35", "Eclipse", 37)
case object GuardsSpear extends Equipment("w36", "Guard's Spear", 18, 16)
case object KingsDefender extends Equipment("w37", "King’s Defender", 18, 17)
case object RagingLance extends Equipment("w38", "Raging Lance", 19, 16)
case object CompositeBow extends Equipment("w39", "Composite Bow", 25, 9)
case object LightningBow extends Equipment("w40", "Lightning Bow", 27, 8)
case object HailstormBow extends Equipment("w41", "Hailstorm Bow", 24, 11)
case object ImperialAxe extends Equipment("w42", "Imperial Axe", 17, 17)
case object SkullCrusher extends Equipment("w43", "Skull Crusher", 18, 17)
case object DragonMace extends Equipment("w44", "Dragon Mace", 17, 18)
case object GhostDagger extends Equipment("w45", "Ghost dagger", 13, 2)
case object LionKnife extends Equipment("w46", "Lion Knife", 13)
case object GriffinKnife extends Equipment("w91", "Griffin Knife", 15, 0)
case object MinotaurSword extends Equipment("w92", "Minotaur Sword", 41)
case object PhoenixSword extends Equipment("w93", "Phoenix Sword", 45, 0)
case object HeavyFauchard extends Equipment("w94", "Heavy Fauchard", 21, 19)
case object Guisarme extends Equipment("w95", "Guisarme", 23, 21)
case object MeteorBow extends Equipment("w96", "Meteor Bow", 29, 11)
case object NightfallBow extends Equipment("w97", "Nightfall Bow", 32, 12)
case object BlackMorningstar extends Equipment("w98", "Black Morningstar", 19, 21)
case object MaimingBulawa extends Equipment("w99", "Maiming Bulawa", 22, 22)

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
    "Council Shield" -> CouncilShield,
    "Council Helmet" -> CouncilHelmet,
    "Griffin Helmet" -> GriffinHelmet,
    "Celestial Helmet" -> CelestialHelmet,
    "Council Armor" -> CouncilArmor,
    "Griffin Armor" -> GriffinArmor,
    "Celestial Armor" -> CelestialArmor,
    "Council Gauntlets" -> CouncilGauntlets,
    "Griffin Gloves" -> GriffinGloves,
    "Celestial Bracers" -> CelestialBracers,
    "Council Boots" -> CouncilBoots,
    "Griffin Boots" -> GriffinBoots,
    "Celestial Boots" -> CelestialBoots,
    "Assault Cape" -> AssaultCape,
    "Craftsman Apron" -> CraftsmanApron,
    "Stoneskin Cloak" -> StoneskinCloak,
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
    "Minotaur Sword" -> MinotaurSword,
    "Phoenix Sword" -> PhoenixSword,
    "Heavy Fauchard" -> HeavyFauchard,
    "Guisarme" -> Guisarme,
    "Meteor Bow" -> MeteorBow,
    "Nightfall Bow" -> NightfallBow,
    "Black Morningstar" -> BlackMorningstar,
    "Maiming Bulawa" -> MaimingBulawa,
  )

  private val mapById = Map(
    "a6" -> Saddlebag,
    "a7" -> Flask,
    "a8" -> RoyalGuardCape,
    "a27" -> OrderArmor,
    "a28" -> OrderHelmet,
    "a29" -> OrderBoots,
    "a30" -> OrderGauntlets,
    "a31" -> OrderShield,
    "a32" -> HunterArmor,
    "a33" -> HunterHelmet,
    "a34" -> HunterBoots,
    "a35" -> HunterGloves,
    "a36" -> ClarityRobe,
    "a37" -> ClarityCirclet,
    "a38" -> ClarityShoes,
    "a39" -> ClarityBracers,
    "a44" -> VeteransCloak,
    "a45" -> CrusaderArmor,
    "a46" -> CrusaderHelmet,
    "a47" -> CrusaderBoots,
    "a48" -> CrusaderGauntlets,
    "a49" -> CrusaderShield,
    "a50" -> RoyalArmor,
    "a51" -> RoyalHelmet,
    "a52" -> RoyalBoots,
    "a53" -> RoyalGauntlets,
    "a54" -> RoyalShield,
    "a55" -> GhostArmor,
    "a56" -> GhostHelmet,
    "a57" -> GhostBoots,
    "a58" -> GhostGloves,
    "a59" -> LionArmor,
    "a60" -> LionHelmet,
    "a61" -> LionBoots,
    "a62" -> LionGloves,
    "a63" -> DemonRobe,
    "a64" -> DemonCirclet,
    "a65" -> DemonShoes,
    "a66" -> DemonBracers,
    "a67" -> DivineRobe,
    "a68" -> DivineCirclet,
    "a69" -> DivineShoes,
    "a70" -> DivineBracers,
    "a71" -> StormCloak,
    "a72" -> DurableCloak,
    "a73" -> BlessedCloak,
    "a74" -> HikingJar,
    "a75" -> HikingBag,
    "a82" -> CouncilShield,
    "a79" -> CouncilHelmet,
    "a84" -> GriffinHelmet,
    "a88" -> CelestialHelmet,
    "a78" -> CouncilArmor,
    "a83" -> GriffinArmor,
    "a87" -> CelestialArmor,
    "a81" -> CouncilGauntlets,
    "a86" -> GriffinGloves,
    "a90" -> CelestialBracers,
    "a80" -> CouncilBoots,
    "85" -> GriffinBoots,
    "89" -> CelestialBoots,
    "a100" -> AssaultCape,
    "a101" -> CraftsmanApron,
    "a102" -> StoneskinCloak,
    "w28" -> ChampionSword,
    "w29" -> Trident,
    "w30" -> HunterBow,
    "w31" -> WarHammer,
    "w32" -> HunterDagger,
    "w35" -> Eclipse,
    "w36" -> GuardsSpear,
    "w37" -> KingsDefender,
    "w38" -> RagingLance,
    "w39" -> CompositeBow,
    "w40" -> LightningBow,
    "w41" -> HailstormBow,
    "w42" -> ImperialAxe,
    "w43" -> SkullCrusher,
    "w44" -> DragonMace,
    "w45" -> GhostDagger,
    "w46" -> LionKnife,
    "w91" -> GriffinKnife,
    "w92" -> MinotaurSword,
    "w93" -> PhoenixSword,
    "w94" -> HeavyFauchard,
    "w95" -> Guisarme,
    "w96" -> MeteorBow,
    "w97" -> NightfallBow,
    "w98" -> BlackMorningstar,
    "w99" -> MaimingBulawa,
  )

  def byName(name: String): Option[Equipment] = mapByName.get(name)

  def byId(id: String): Option[Equipment] = mapById.get(id)
}
