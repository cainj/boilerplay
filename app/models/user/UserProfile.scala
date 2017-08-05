package models.user

import java.util.UUID

import models.template.Theme
import java.time.LocalDateTime

case class UserProfile(
  id: UUID,
  theme: Theme,
  created: LocalDateTime
)
