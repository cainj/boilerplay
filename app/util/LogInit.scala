package util

import scribe.formatter.FormatterBuilder
import scribe.writer.{ConsoleWriter, FileWriter}
import scribe.{Level, LogHandler, Logger, Logging}

object LogInit extends Logging {
  private[this] var initialized = false

  private[this] val logDir = new java.io.File("./logs")

  private[this] val format = FormatterBuilder().date().string(" ").level.string(" ").classNameAbbreviated.string(":").lineNumber.string(" - ").message.newLine

  private[this] val fileWriter = FileWriter.daily(
    name = util.Config.projectId,
    directory = logDir
  )

  private[this] def handlers(l: Level) = Seq(
    LogHandler(level = l, formatter = format, writer = fileWriter),
    LogHandler(level = l, formatter = format, writer = ConsoleWriter)
  )

  def init(debug: Boolean) = {
    if (initialized) {
      throw new IllegalStateException("Logging initialized twice!")
    }
    Logger.root.clearHandlers()
    val h = if (debug) { handlers(Level.Debug) } else { handlers(Level.Info) }
    h.foreach(Logger.root.addHandler)

    Logger.root.info("Logging inititalized.")
    initialized = true
  }
}
