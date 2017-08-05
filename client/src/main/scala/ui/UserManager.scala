package ui

import java.util.UUID

import models.UserSettings

object UserManager {
  var userId: Option[UUID] = None
  val rowsReturned = 100

  def onUserSettings(us: UserSettings) = {
    userId = Some(us.userId)
  }
}
