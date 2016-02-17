package kanalony.storage.utils

/**
 * Created by elad.benedict on 2/8/2016.
 */
object fs {
  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) } finally { p.close() }
  }
}
