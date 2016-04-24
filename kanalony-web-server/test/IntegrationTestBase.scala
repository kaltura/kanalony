/**
 * Created by elad.benedict on 3/29/2016.
 */

package kanalony.tests

import org.scalatest._
import unit.IntegrationTestBase
import collection.mutable.Stack



class DataApiIntegrationTests extends IntegrationTestBase {

  describe("suite1"){
    it("should pass") {
      val res = get("http://www.google.com")
      println(res)
    }
  }
}
