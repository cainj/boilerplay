package models.settings

import enumeratum._

sealed abstract class SettingKey(val id: String, val title: String, val description: String, val default: String) extends EnumEntry {
  override def toString = id
}

object SettingKey extends Enum[SettingKey] {

  override val values = findValues
}
