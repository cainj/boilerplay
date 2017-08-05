package controllers

import play.api.mvc._
import scribe.Logging
import util.metrics.Instrumented
import util.Application

import util.FutureUtils.defaultContext

import scala.concurrent.Future

abstract class BaseController() extends InjectedController with Instrumented with Logging {
  def app: Application

  def withAdminSession(action: String)(block: Request[AnyContent] => Future[Result]) = Action.async { implicit request =>
    metrics.timer(action).timeFuture {
      block(request)
    }
  }

  def withoutSession(action: String)(block: Request[AnyContent] => Future[Result]) = Action.async { implicit request =>
    metrics.timer(action).timeFuture {
      block(request)
    }
  }

  def withSession(action: String)(block: Request[AnyContent] => Future[Result]) = Action.async { implicit request =>
    metrics.timer(action).timeFuture {
      block(request)
    }
  }
}
