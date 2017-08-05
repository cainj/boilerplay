import sbt._

object Dependencies {
  object Play {
    private[this] val version = "2.6.2"
    val lib = "com.typesafe.play" %% "play" % version
    val filters = play.sbt.PlayImport.filters
    val ws = play.sbt.PlayImport.ws
    val guice = play.sbt.PlayImport.guice
    val cache = play.sbt.PlayImport.ehcache
    val json = "com.typesafe.play" %% "play-json" % "2.6.2"
    val test = "com.typesafe.play" %% "play-test" % version % "test"
  }

  object Akka {
    private[this] val version = "2.5.3"
    val actor = "com.typesafe.akka" %% "akka-actor" % version
    val remote = "com.typesafe.akka" %% "akka-remote" % version
    val logging = "com.typesafe.akka" %% "akka-slf4j" % version
    val cluster = "com.typesafe.akka" %% "akka-cluster" % version
    val clusterMetrics = "com.typesafe.akka" %% "akka-cluster-metrics" % version
    val clusterTools = "com.typesafe.akka" %% "akka-cluster-tools" % version
    val testkit = "com.typesafe.akka" %% "akka-testkit" % version % "test"
  }

  object Database {
    val postgres = "com.github.mauricio" %% "postgresql-async" % "0.2.21"
  }

  object GraphQL {
    val sangria = "org.sangria-graphql" %% "sangria" % "1.2.2"
    val playJson = "org.sangria-graphql" %% "sangria-play-json" % "1.0.3"
    val circe = "org.sangria-graphql" %% "sangria-circe" % "1.1.0"
  }

  object Serialization {
    val circeVersion = "0.8.0"
  }

  object WebJars {
    val fontAwesome = "org.webjars" % "font-awesome" % "4.7.0"
    val jquery = "org.webjars" % "jquery" % "2.2.4"
    val materialize = "org.webjars" % "materializecss" % "0.99.0"
    val moment = "org.webjars" % "momentjs" % "2.18.1"
    val mousetrap = "org.webjars" % "mousetrap" % "1.6.0"
  }

  object Metrics {
    val version = "3.2.4"
    val metrics = "nl.grons" %% "metrics-scala" % "3.5.9"
    val jvm = "io.dropwizard.metrics" % "metrics-jvm" % version
    val ehcache = "io.dropwizard.metrics" % "metrics-ehcache" % version intransitive()
    val healthChecks = "io.dropwizard.metrics" % "metrics-healthchecks" % version intransitive()
    val json = "io.dropwizard.metrics" % "metrics-json" % version
    val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % "9.4.6.v20170531"
    val servlets = "io.dropwizard.metrics" % "metrics-servlets" % version intransitive()
    val graphite = "io.dropwizard.metrics" % "metrics-graphite" % version intransitive()
  }

  object ScalaJS {
    val jQueryVersion = "0.9.2"
    val scalaTagsVersion = "0.6.5"
  }

  object Utils {
    val scapegoatVersion = "1.3.1"
    val enumeratumVersion = "1.5.14"

    val scribeVersion = "1.4.3"
    val scribeSlf4j = "com.outr" %% "scribe-slf4j" % scribeVersion
    val logbackCore = "ch.qos.logback" % "logback-core" % "1.2.3"

    val commonsIo = "commons-io" % "commons-io" % "2.5"
    val scalaGuice = "net.codingwell" %% "scala-guice" % "4.1.0"
  }

  object Testing {
    val scalaTest = "org.scalatest" %% "scalatest" % "3.0.3" % "test"
    val gatlingCore = "io.gatling" % "gatling-test-framework" % "2.1.7" % "test"
    val gatlingCharts = "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.7" % "test"
  }
}
