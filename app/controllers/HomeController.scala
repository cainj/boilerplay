package controllers

import java.util.UUID

import akka.actor.ActorSystem
import akka.stream.Materializer
import models.{RequestMessage, ResponseMessage}
import util.FutureUtils.defaultContext
import play.api.libs.streams.ActorFlow
import play.api.mvc.{AnyContentAsEmpty, Request, WebSocket}
import services.socket.SocketService
import util.Application
import util.web.MessageFrameFormatter

import scala.concurrent.Future

@javax.inject.Singleton
class HomeController @javax.inject.Inject() (
    override val app: Application,
    implicit val system: ActorSystem,
    implicit val materializer: Materializer
) extends BaseController {
  private[this] implicit val t = new MessageFrameFormatter(app.config.debug).transformer

  def home() = withSession("home") { implicit request =>
    Future.successful(Ok(views.html.index(UUID.randomUUID(), app.config.debug)))
  }

  def connect() = WebSocket.acceptOrResult[RequestMessage, ResponseMessage] { request =>
    implicit val req = Request(request, AnyContentAsEmpty)
    val user = UUID.randomUUID()
    Future.successful(Right(ActorFlow.actorRef { out =>
      SocketService.props(None, app.supervisor, user, out, request.remoteAddress)
    }))
  }

  def untrail(path: String) = Action.async {
    Future.successful(MovedPermanently(s"/$path"))
  }

  def externalLink(url: String) = withSession("external.link") { implicit request =>
    Future.successful(Redirect(if (url.startsWith("http")) { url } else { "http://" + url }))
  }

  def ping(timestamp: Long) = withSession("ping") { implicit request =>
    Future.successful(Ok(timestamp.toString))
  }

  def robots() = withSession("robots") { implicit request =>
    Future.successful(Ok("User-agent: *\nDisallow: /"))
  }
}
