package controllers.admin

import java.util.UUID

import controllers.BaseController
import util.Application
import util.FutureUtils.defaultContext

@javax.inject.Singleton
class MetricsController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def showMetrics = withAdminSession("admin-metrics") { implicit request =>
    val url = "http://localhost:2001/metrics?pretty=true"
    val call = app.ws.url(url).withHttpHeaders("Accept" -> "application/json")
    val f = call.get()

    f.map { json =>
      Ok(views.html.admin.metrics(UUID.randomUUID, json.body))
    }
  }
}
