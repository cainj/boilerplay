package util

import java.io.File
import java.net.URL

import org.slf4j.impl.StaticLoggerBinder
import play.api
import play.api.{Environment, LoggerConfigurator, Mode}

class ScribeLoggerConfigurator extends LoggerConfigurator {
  override def init(rootPath: File, mode: Mode) = configure(Map.empty, None)

  override def shutdown() = {}

  override def configure(env: Environment) = configure(Map.empty, None)

  override def configure(properties: Map[String, String], config: Option[URL]) = {}

  override def configure(env: Environment, configuration: api.Configuration, optionalProperties: Map[String, String]) = {}

  override def loggerFactory = StaticLoggerBinder.getSingleton.getLoggerFactory
}
