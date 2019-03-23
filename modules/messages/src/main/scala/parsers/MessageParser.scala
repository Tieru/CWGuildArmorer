package parsers

import entity.player.Castle.Castle
import entity.player.{Castle, Equipment, ItemInfo}
import entity.{GuildForwardAction, HeroForwardAction, OnStartAction}
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
    Commands.START ~ push(new OnStartAction())
  }

  def ForwardedMessage = rule {
    HeroForwardRule
  }

  def HeroForwardRule = rule {
    CastleRule ~ optional(GuildTag) ~ Username ~ SkipToEquipmentPart ~ EquipmentTotal ~ oneOrMore(ANY) ~> HeroForwardAction
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
    capture(oneOrMore(!EOL ~ ANY))
  }

  def SkipToEquipmentPart = rule {
    oneOrMore(!"\uD83C\uDFBD" ~ ANY)
  }

  def EquipmentTotal: Rule1[Seq[ItemInfo]] = rule {
    "\uD83C\uDFBDЭкипировка +" ~ oneOrMore(CharPredicate.Digit) ~ "⚔+" ~ oneOrMore(CharPredicate.Digit) ~ "🛡" ~ EOL ~
      EquipmentItems
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
