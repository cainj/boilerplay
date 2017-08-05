package services.socket

import java.util.UUID

import akka.actor.{ActorRef, Props}
import models._
import scribe.Logging
import util.metrics.InstrumentedActor

object SocketService {
  def props(id: Option[UUID], supervisor: ActorRef, user: UUID, out: ActorRef, sourceAddress: String) = {
    Props(SocketService(id.getOrElse(UUID.randomUUID), supervisor, user, out, sourceAddress))
  }
}

case class SocketService(
    id: UUID, supervisor: ActorRef, user: UUID, out: ActorRef, sourceAddress: String
) extends InstrumentedActor with RequestMessageHelper with Logging {

  override def preStart() = {
    logger.info(s"Starting connection for user [$user].")
    supervisor ! SocketStarted(user, id, self)
    out ! UserSettings(user)
  }

  override def postStop() = {
    supervisor ! SocketStopped(id)
  }
}
