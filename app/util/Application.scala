package util

import java.util.TimeZone

import akka.actor.{ActorSystem, Props}
import com.codahale.metrics.SharedMetricRegistries
import play.api.Environment
import play.api.inject.ApplicationLifecycle
import util.FutureUtils.defaultContext
import play.api.libs.ws.WSClient
import services.database.{Database, MasterDdl}
import services.file.FileService
import services.settings.SettingsService
import services.supervisor.ActorSupervisor
import util.cache.CacheService
import util.metrics.Instrumented

import scala.concurrent.Future
import scala.util.control.NonFatal

object Application {
  var initialized = false
}

@javax.inject.Singleton
class Application @javax.inject.Inject() (
    val config: Configuration,
    val lifecycle: ApplicationLifecycle,
    val playEnv: Environment,
    val actorSystem: ActorSystem,
    val ws: WSClient
) extends scribe.Logging {
  logger.info(s"Starting [${util.Config.projectName}].")

  if (Application.initialized) {
    logger.info("Skipping initialization after failure.")
  } else {
    start()
  }

  val supervisor = actorSystem.actorOf(Props(classOf[ActorSupervisor], this), "supervisor")
  logger.debug(s"Actor Supervisor [${supervisor.path}] started for [${util.Config.projectId}].")

  private[this] def start() = try {
    LogInit.init(debug = false)

    logger.info(s"${Config.projectName} is starting.")
    Application.initialized = true

    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    System.setProperty("user.timezone", "UTC")

    SharedMetricRegistries.remove("default")
    SharedMetricRegistries.add("default", Instrumented.metricRegistry)

    lifecycle.addStopHook(() => Future.successful(stop()))

    FileService.setRootDir(config.dataDir)

    // BUG: When postgres-async calls netty, "java.lang.NullPointerException: name" is triggered.
    //Database.open(config.cnf)
    //MasterDdl.init().map { _ =>
    //  SettingsService.load()
    //}
  } catch {
    case NonFatal(x) => logger.error(new IllegalStateException("Cannot start application.", x))
  }

  private[this] def stop() = {
    //Database.close()
    CacheService.close()
    SharedMetricRegistries.remove("default")
  }
}
