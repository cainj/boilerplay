import models._
import ui.UserManager
import util.{DateUtils, NetworkMessage}

trait ResponseMessageHelper { this: Boilerplay =>
  protected[this] def handleMessage(rm: ResponseMessage) = rm match {
    case p: Pong => NetworkMessage.latencyMs = Some((System.currentTimeMillis - DateUtils.toMillis(p.timestamp)).toInt)
    case us: UserSettings => UserManager.onUserSettings(us)

    case se: ServerError => handleServerError(se.reason, se.content)
    case _ => logger.warn(s"Received unknown message of type [${rm.getClass.getSimpleName}].")
  }
}
