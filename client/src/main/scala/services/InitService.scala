package services

import models.RequestMessage
import scribe.Logging
import util.{LogHelper, NetworkMessage}

object InitService extends Logging {
  def init(sendMessage: (RequestMessage) => Unit, connect: () => Unit, debug: Boolean) = {
    LogHelper.init(debug)

    NetworkMessage.register(sendMessage)

    logger.debug(util.Config.projectName + " has started.")
    connect()
  }
}
