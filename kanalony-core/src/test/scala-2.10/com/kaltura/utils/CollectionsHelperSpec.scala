package com.kaltura.utils

import com.kaltura.core.utils.CollectionOps
import org.scalatest._

class CollectionsHelperSpec extends FlatSpec with Matchers with BeforeAndAfterAll  {

  "null array" should "be empty" in {
    val arr: Array[Int] = null
    CollectionOps.isEmpty(arr) shouldBe true
  }

  "null string" should "be empty" in {
    val str: String = null
    CollectionOps.isEmpty(str) shouldBe true
  }

  "a string with some characters" should "not be empty" in {
    val str: String = "string"
    CollectionOps.isEmpty(str) shouldBe false
  }

  "an array with some elements" should "not be empty" in {
    val arr = Array(1,2,3)
    CollectionOps.isEmpty(arr) shouldBe false
  }


}