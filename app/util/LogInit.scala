package util

import scribe.formatter.FormatterBuilder
import scribe.writer.FileWriter
import scribe.{Level, LogHandler, Logger, Logging}

object LogInit extends Logging {
  private[this] var initialized = false

  private[this] val logDir = new java.io.File("./logs")

  private[this] val format = FormatterBuilder().date().string(" ").level.string(" ").positionAbbreviated.newLine.string(" - ").message.newLine

  private[this] val writer = FileWriter.daily(
    name = util.Config.projectId,
    directory = logDir
  )

  private[this] def handler(l: Level) = LogHandler(
    level = l,
    formatter = format,
    writer = writer
  )

  def init(debug: Boolean) = {
    if (initialized) {
      throw new IllegalStateException("Logging initialized twice!")
    }
    Logger.root.clearHandlers()
    val h = if (debug) { handler(Level.Debug) } else { handler(Level.Info) }
    Logger.root.addHandler(h)
    initialized = true
  }
}
