package entity.player

object HeroClass extends Enumeration {
  type HeroClass = Value
  val Knight, Ranger, Sentinel, Gatherer, Blacksmith, Alchemist: HeroClass.Value = Value

  def byString(emoji: String): HeroClass = emoji match {
    case "\uD83D\uDEE1" => Sentinel
    case "⚔" => Knight
    case "⚗" => Alchemist
    case "⚒" => Blacksmith
    case "📦" => Gatherer
    case "🏹" => Ranger
  }
}
