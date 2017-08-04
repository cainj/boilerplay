package services

import models.RequestMessage
import scribe.Logger
import util.{LogHelper, NetworkMessage}

object InitService extends Logger {
  def init(sendMessage: (RequestMessage) => Unit, connect: () => Unit) = {
    LogHelper.installErrorHandler()
    NetworkMessage.register(sendMessage)

    debug(util.Config.projectName + " has started.")
    connect()
  }
}
