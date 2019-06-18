package entity.util

import entity.player.Equipment
import entity.player.Equipment.byName

object EquipmentUtils {

  def byNameIgnoreEngravings(name: String): Option[Equipment] = {
    Equipment.byName(name) match {
      case Some(item) => Option(item)
      case None => byNameWithEngravings(name, leftEngravingReducer) match {
        case Some(item) => Option(item)
        case None => byNameWithEngravings(name, rightEngravingReducer)
      }
    }
  }

  def byNameWithEngravings(name: String, reducer: String => Option[String]): Option[Equipment] = {
    val newString = reducer(name)
    if (newString.isEmpty) {
      None
    } else {
      val newName = newString.get
      byName(newName) match {
        case Some(item) => Option(item)
        case None => byNameWithEngravings(newName, reducer)
      }
    }
  }

  def leftEngravingReducer(name: String): Option[String] = {
    val spaceIndex = name.indexOf(' ')
    if (spaceIndex > 0) {
      Option(name.substring(spaceIndex + 1))
    } else {
      None
    }
  }

  def rightEngravingReducer(name: String): Option[String] = {
    val spaceIndex = name.lastIndexOf(' ')
    if (spaceIndex > 0) {
      Option(name.substring(0, spaceIndex))
    } else {
      None
    }
  }
}
