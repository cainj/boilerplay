package controllers.admin

import java.util.UUID

import controllers.BaseController
import play.twirl.api.Html
import util.Application
import util.FutureUtils.defaultContext

import scala.concurrent.Future

@javax.inject.Singleton
class SearchController @javax.inject.Inject() (override val app: Application) extends BaseController {
  def search(q: String) = withAdminSession("admin.search") { implicit request =>
    val resultF = try {
      searchInt(q, q.toInt)
    } catch {
      case _: NumberFormatException => try {
        searchUuid(q, UUID.fromString(q))
      } catch {
        case _: IllegalArgumentException => searchString(q)
      }
    }

    resultF.map { results =>
      Ok(views.html.admin.explore.searchResults(q, results, UUID.randomUUID))
    }
  }

  private[this] def searchUuid(q: String, id: UUID) = Future.successful(Seq.empty[Html])

  private[this] def searchInt(q: String, id: Int) = Future.successful(Seq.empty[Html])

  private[this] def searchString(q: String) = Future.successful(Seq.empty[Html])
}
