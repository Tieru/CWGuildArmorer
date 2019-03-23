package entity.player

object Castle extends Enumeration {
  type Castle = Value
  val Farm, Bastion, Dawn, Rock, Tortuga, Amber, Night: Castle.Value = Value

  def byString(emoji: String): Option[Castle] = emoji match {
    case "\uD83C\uDF46" => Option(Farm)
    case "☘️" => Option(Bastion)
    case "\uD83C\uDF39" => Option(Dawn)
    case "\uD83D\uDDA4" => Option(Rock)
    case "\uD83D\uDC22" => Option(Tortuga)
    case "\uD83C\uDF41" => Option(Amber)
    case "\uD83E\uDD87" => Option(Night)
  }
}
