package com.kaltura.core.utils

object CollectionOps {
  /**
   * Returns true if arr is null or empty
   * @param arr
   * @tparam A
   * @return
   */
  def isEmpty[A](arr: Array[A]): Boolean = arr == null || arr.length == 0

  /**
   * Returns true if str is null or empty
   * @param str
   * @tparam A
   * @return
   */
  def isEmpty[A](str: String): Boolean = str == null || str.length == 0

  val EMPTY_STRING = ""
}
