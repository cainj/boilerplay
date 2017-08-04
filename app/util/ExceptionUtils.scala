package util

import akka.actor.ActorRef
import models.ServerError

object ExceptionUtils extends scribe.Logging {
  def actorErrorFunction(out: ActorRef, key: String, t: Throwable) = {
    logger.warn(s"Error [$key] encountered of type [${t.getClass.getSimpleName}].")
    logger.warn(t)

    out ! ServerError(key, t.getMessage)
  }
}
