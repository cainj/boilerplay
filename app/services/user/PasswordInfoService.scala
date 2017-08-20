package services.user

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import models.queries.auth.PasswordInfoQueries
import util.FutureUtils.databaseContext
import services.database.Database
import util.tracing.TracingService

import scala.concurrent.Future

@javax.inject.Singleton
class PasswordInfoService @javax.inject.Inject() (tracingService: TracingService) extends DelegableAuthInfoDAO[PasswordInfo] {

  override def find(loginInfo: LoginInfo) = tracingService.topLevelTrace("password.find") { implicit td =>
    Database.query(PasswordInfoQueries.getByPrimaryKey(Seq(loginInfo.providerID, loginInfo.providerKey)))
  }

  override def add(loginInfo: LoginInfo, authInfo: PasswordInfo) = tracingService.topLevelTrace("password.add") { implicit td =>
    Database.execute(PasswordInfoQueries.CreatePasswordInfo(loginInfo, authInfo)).map { _ => authInfo }
  }

  override def update(loginInfo: LoginInfo, authInfo: PasswordInfo): Future[PasswordInfo] = tracingService.topLevelTrace("password.update") { implicit td =>
    Database.execute(PasswordInfoQueries.UpdatePasswordInfo(loginInfo, authInfo)).map { _ => authInfo }
  }

  override def save(loginInfo: LoginInfo, authInfo: PasswordInfo) = tracingService.topLevelTrace("password.save") { implicit td =>
    Database.transaction { conn =>
      Database.execute(PasswordInfoQueries.UpdatePasswordInfo(loginInfo, authInfo), Some(conn)).flatMap { rowsAffected =>
        if (rowsAffected == 0) {
          Database.execute(PasswordInfoQueries.CreatePasswordInfo(loginInfo, authInfo), Some(conn)).map { _ => authInfo }
        } else {
          Future.successful(authInfo)
        }
      }
    }
  }

  override def remove(loginInfo: LoginInfo) = tracingService.topLevelTrace("password.remove") { implicit td =>
    Database.execute(PasswordInfoQueries.removeByPrimaryKey(Seq(loginInfo.providerID, loginInfo.providerKey))).map(_ => Unit)
  }
}
