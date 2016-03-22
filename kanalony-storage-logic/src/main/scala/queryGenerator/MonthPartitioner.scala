package queryGenerator

/**
 * Created by elad.benedict on 3/22/2016.
 */

case class MonthPartitioner() extends IRowBreaker {
  override val partitionKeyParamName: String = "months"
  override val implementingTrait: String = "IMonthlyPartitionedQueryParams"
}
