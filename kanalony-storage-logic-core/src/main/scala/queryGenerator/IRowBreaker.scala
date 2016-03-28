package queryGenerator

/**
 * Created by elad.benedict on 2/29/2016.
 */

trait IRowBreaker {
  val partitionKeyParamName : String
  val implementingTrait : String
}
