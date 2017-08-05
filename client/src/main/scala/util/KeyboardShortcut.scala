package util

import java.util.UUID

import enumeratum._

sealed abstract class KeyboardShortcut(val pattern: String, val key: String, val call: (Option[UUID]) => Unit, val isGlobal: Boolean = true) extends EnumEntry

object KeyboardShortcut extends Enum[KeyboardShortcut] with scribe.Logging {
  case object Help extends KeyboardShortcut("?", "help", { _ =>
    logger.info("Help!")
  })

  override val values = findValues
}
