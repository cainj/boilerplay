package util.web

import models.ResponseMessage
import play.api.mvc.WebSocket.MessageFlowTransformer
import util.JsonSerializers

class MessageFrameFormatter(debug: Boolean) {
  val transformer = MessageFlowTransformer.stringMessageFlowTransformer.map { s =>
    JsonSerializers.readRequestMessage(s)
  }.contramap { m: ResponseMessage => JsonSerializers.writeResponseMessage(m, debug) }
}
