package entity.player

object HeroClass extends Enumeration {
  type HeroClass = Value
  val Knight, Ranger, Sentinel, Gatherer, Blacksmith, Alchemist: HeroClass.Value = Value

  def byString(emoji: String): HeroClass = emoji match {
    case "⚔" => Knight
    case "🛡" => Sentinel
    case "🏹" => Ranger
    case "📦" => Gatherer
    case "⚒" => Blacksmith
    case "⚗" => Alchemist
  }
}
