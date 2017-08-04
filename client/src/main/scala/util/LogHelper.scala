package util

import org.scalajs.dom
import org.scalajs.dom.raw.Event
import scribe.Logger

import scala.scalajs.js.Dynamic.global

object LogHelper extends Logger {
  def logJs(o: scalajs.js.Any) = {
    global.window.debug = o
    global.console.log(o)
  }

  def installErrorHandler() = {
    dom.window.onerror = (e: Event, source: String, lineno: Int, colno: Int) => {
      info(s"Script error [$e] encountered in [$source:$lineno:$colno]")
      error(e.toString)
    }
  }
}
