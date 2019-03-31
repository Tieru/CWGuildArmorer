package parsers

import entity.player.Castle.Castle
import entity.player.HeroClass.HeroClass
import entity.player.{Castle, Equipment, HeroClass, ItemInfo}
import entity.{GuildForwardAction, HeroForwardAction, OnGuildListAction, OnStartAction}
import org.parboiled2._

//noinspection CaseClassParam,TypeAnnotation
case class MessageParser(val input: ParserInput) extends Parser {

  def Input = rule {
    CommandExpression ~ EOI
  }

  def CommandExpression = rule {
    CommandRule | ForwardedMessage
  }

  def CommandRule = rule {
    ch('/') ~ CommandsRule
  }

  def CommandsRule = rule {
    Commands.START ~ push(new OnStartAction()) |
    Commands.GUILD_LIST ~ push(new OnGuildListAction())
  }

  def ForwardedMessage = rule {
    HeroForwardRule | GuildInfo
  }

  def HeroForwardRule = rule {
    CastleRule ~ optional(GuildTag) ~ Username ~
      HeroLevel ~
      SkipToRank ~
      optional(Achievements) ~
      HeroClassRule ~
      SkipToEquipment ~
      EquipmentTotal ~
      oneOrMore(ANY) ~> HeroForwardAction
  }

  def CastleRule: Rule1[Castle] = rule {
    "\uD83C\uDF46" ~ push(Castle.Farm) |
      "☘️" ~ push(Castle.Bastion) |
      "\uD83C\uDF39" ~ push(Castle.Dawn) |
      "\uD83D\uDDA4" ~ push(Castle.Rock) |
      "\uD83D\uDC22" ~ push(Castle.Tortuga) |
      "\uD83C\uDF41" ~ push(Castle.Amber) |
      "\uD83E\uDD87" ~ push(Castle.Night)
  }

  def GuildTag: Rule1[String] = rule {
    ch('[') ~ capture(ANY ~ ANY ~ ANY) ~ ch(']')
  }

  def Username: Rule1[String] = rule {
    capture(oneOrMore(!EOL ~ ANY)) ~ EOL
  }

  def HeroLevel: Rule1[Int] = rule {
    "🏅Уровень: " ~ capture(oneOrMore(CharPredicate.Digit)) ~> (_.toInt) ~ EOL
  }

  def HeroClassRule: Rule1[HeroClass] = rule {
    capture(oneOrMore(!"️К" ~ ANY)) ~> (HeroClass.byString(_)) ~ "️Класс: /class"
  }

  def SkipToRank = rule {
    oneOrMore(!"📚" ~ ANY) ~ oneOrMore(!EOL ~ ANY) ~ EOL
  }

  def Achievements = rule {
    "🎉Достижения: /ach\n"
  }

  def EquipmentTotal: Rule1[Seq[ItemInfo]] = rule {
    "\uD83C\uDFBDЭкипировка +" ~ oneOrMore(CharPredicate.Digit) ~ "⚔+" ~ oneOrMore(CharPredicate.Digit) ~ "🛡" ~ EOL ~
      EquipmentItems
  }

  def SkipToEquipment = rule {
    oneOrMore(!"\uD83C\uDFBD" ~ ANY)
  }

  def EquipmentItems: Rule1[Seq[ItemInfo]] = rule {
    oneOrMore(EquipmentItem) ~> ((items: Seq[Option[ItemInfo]]) => items.flatten)
  }

  def EquipmentItem: Rule1[Option[ItemInfo]] = rule {
    optional(EquipmentEnhancement) ~ EquipmentName ~ EOL ~> ((enhancement: Option[Int], equipment: Option[Equipment]) =>
      equipment match {
        case Some(item) => Option(ItemInfo(item, enhancement.getOrElse(0)))
        case None => None
      })
  }

  def EquipmentEnhancement: Rule1[Int] = rule {
    "⚡+" ~ capture(oneOrMore(CharPredicate.Digit)) ~> (_.toInt) ~ " "
  }

  def EquipmentName: Rule1[Option[Equipment]] = rule {
    capture(oneOrMore(!" +" ~ ANY)) ~ oneOrMore(!EOL ~ ANY) ~> (Equipment.byName(_))
  }

  // Guild

  def GuildInfo = rule {
    GuildName ~ GuildCommander ~ oneOrMore(ANY) ~> GuildForwardAction
  }

  def GuildName = rule {
    CastleRule ~ GuildTag ~ " " ~ capture(oneOrMore(!EOL ~ ANY)) ~ EOL
  }

  def GuildCommander = rule {
    "Commander: " ~ capture(oneOrMore(!EOL ~ ANY)) ~ EOL
  }

  // Commons

  def EOL = rule {
    "\r\n" | "\n"
  }

  def DoubleEOL = rule {
    EOL ~ EOL
  }

}
