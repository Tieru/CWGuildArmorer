package parsers

import entity.player._
import entity.{GuildForwardAction, HeroForwardAction, OnStartAction}
import org.scalatest.FlatSpec

class MessageParserTest extends FlatSpec {

  "Parser" should "recognize /onStart command" in {
    testInput[OnStartAction]("/start")
  }

  it should "parse hero level" in {
    val message = "🏅Уровень: 48\n"
    val result = MessageParser(message).HeroLevel.run().get
    assert(result == 48)
  }

  it should "parse hero class" in {
    val message = "⚔️Класс: /class\n"
    val result = MessageParser(message).HeroClassRule.run().get
    assert(result == HeroClass.Knight)
  }

  it should "parse hero equipment with enhancements" in {
    val message = "⚡+3 Champion Sword +37⚔\n"
    val result = MessageParser(message).EquipmentItem.run().get.get
    assert(result.equipment == ChampionSword)
    assert(result.enhancement == 3)
  }

  it should "parse hero equipment with no enhancements" in {
    val message = "Royal Guard Cape +1⚔ +1🛡\n"
    val result = MessageParser(message).EquipmentItem.run().get
    val item = result.get
    assert(item.equipment == RoyalGuardCape)
    assert(item.enhancement == 0)
  }

  it should "parse equipment type" in {
    val message = "Ghost dagger +13⚔ +2🛡\n"
    val result = MessageParser(message).EquipmentName.run().get
    assert(result.get == GhostDagger)
  }

  it should "parse all hero equipment" in {
    val message = "🎽Экипировка +75⚔+51🛡\n" +
      "⚡+3 Champion Sword +37⚔\n" +
      "Ghost dagger +13⚔ +2🛡\n" +
      "Hunter Helmet +5⚔ +11🛡\n" +
      "Ghost Gloves +5⚔ +10🛡\n" +
      "Hunter Armor +8⚔ +18🛡\n" +
      "Lion Boots +6⚔ +9🛡\n" +
      "Saddlebag +5🎒\n" +
      "Flask +2🔋\n" +
      "Royal Guard Cape +1⚔ +1🛡\n"

    val result = MessageParser(message).EquipmentTotal.run().get
    assert(result.size == 9)
  }

  it should "parse hero forward" in {
    val message = "🐢[RUМ]SlavikVoronov\n" +
      "🏅Уровень: 48\n" +
      "⚔Атака: 260 🛡Защита: 112\n" +
      "🔥Опыт: 405337/428501\n" +
      "🔋Выносливость: 6/9\n" +
      "💰89 👝78 💎60\n" +
      "📚Ранг: 📕📗📘📙\n" +
      "🎉Достижения: /ach\n" +
      "⚔️Класс: /class\n\n\n" +
      "🎽Экипировка +75⚔+51🛡\n" +
      "⚡+3 Champion Sword +37⚔\n" +
      "Ghost dagger +13⚔ +2🛡\n" +
      "Hunter Helmet +5⚔ +11🛡\n" +
      "Ghost Gloves +5⚔ +10🛡\n" +
      "Hunter Armor +8⚔ +18🛡\n" +
      "Lion Boots +6⚔ +9🛡\n" +
      "Saddlebag +5🎒\n" +
      "Flask +2🔋\n" +
      "Royal Guard Cape +1⚔ +1🛡\n\n" +
      "🎒Рюкзак: 3/20 /inv\n" +
      "📦Warehouse: 1063 /stock"
    val result = MessageParser(message).Input.run().get
    assert(result.isInstanceOf[HeroForwardAction])
    val info = result.asInstanceOf[HeroForwardAction]

    assert(info.castle == Castle.Tortuga)
    assert(info.guildTag.getOrElse("") == "RUМ")
    assert(info.username == "SlavikVoronov")
    assert(info.level == 48)
    assert(info.heroClass == HeroClass.Knight)

    assert(info.equipment.size == 9)
  }

  it should "parse guild castle, tag and name" in {
    val message = "🐢[RUМ] ORCA\n"
    val result = MessageParser(message).GuildName.run().get
    assert(result.head == Castle.Tortuga)
    assert(result.tail.head == "RUМ")
    assert(result.tail.tail.head == "ORCA")
  }

  it should "parse guild commander" in {
    val message = "Commander: devil_will_cry\n"
    val result = MessageParser(message).GuildCommander.run().get
    assert(result == "devil_will_cry")
  }

  it should "parse guild forward" in {
    val message = "🐢[RUМ] ORCA\n" +
      "Commander: devil_will_cry\n" +
      "🏅Level: 6 🎖Glory: 4795\n" +
      "👥 14/18\n" +
      "Advisers:\n" +
      " - Monk Raimundus, lvl.2 Strategist /adv_pt62\n"

    val result = MessageParser(message).Input.run().get
    assert(result.isInstanceOf[GuildForwardAction])
    val info = result.asInstanceOf[GuildForwardAction]

    assert(info.castle == Castle.Tortuga)
    assert(info.guildTag == "RUМ")
    assert(info.guildName == "ORCA")
    assert(info.commander == "devil_will_cry")
  }

  private def testInput[T](message: String) = {
    val result = MessageParser(message).Input.run().get
    assert(result.isInstanceOf[T])
  }

}
