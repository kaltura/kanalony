package queryGenerator

/**
 * Created by elad.benedict on 3/22/2016.
 */

case class YearPartitioner() extends IRowBreaker {
  override val partitionKeyParamName: String = "years"
  override val implementingTrait: String = "IYearlyPartitionedQueryParams"
}
