package queryGenerator

/**
 * Created by elad.benedict on 3/22/2016.
 */

case class DayPartitioner() extends IRowBreaker {
  override val partitionKeyParamName: String = "days"
  override val implementingTrait: String = "IDailyPartitionedQueryParams"
}
