package util

import play.api.{Environment, Mode}
import util.metrics.MetricsConfig

@javax.inject.Singleton
class Configuration @javax.inject.Inject() (val cnf: play.api.Configuration, env: Environment) {
  val debug = env.mode == Mode.Dev
  val dataDir = new java.io.File(cnf.get[Option[String]]("data.directory").getOrElse("./data"))

  val metrics: MetricsConfig = MetricsConfig(
    jmxEnabled = cnf.get[Option[Boolean]]("metrics.jmx.enabled").getOrElse(true),
    graphiteEnabled = cnf.get[Option[Boolean]]("metrics.graphite.enabled").getOrElse(false),
    graphiteServer = cnf.get[Option[String]]("metrics.graphite.server").getOrElse("127.0.0.1"),
    graphitePort = cnf.get[Option[Int]]("metrics.graphite.port").getOrElse(2003),
    servletEnabled = cnf.get[Option[Boolean]]("metrics.servlet.enabled").getOrElse(true),
    servletPort = cnf.get[Option[Int]]("metrics.servlet.port").getOrElse(9001)
  )
}
