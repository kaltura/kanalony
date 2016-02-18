package kanalony.storage.logic

import kanalony.storage.logic.queries._

/**
 * Created by elad.benedict on 2/11/2016.
 */
object Queries {
  object HourlyUserActivityPrtnEntryClstCountry extends HourlyUserActivityPrtnEntryClstCountryQuery
  object HourlyUserActivityPrtnEntryQuery extends HourlyUserActivityPrtnEntryQuery
  object HourlyUserActivityClstEntryQuery extends HourlyUserActivityClstEntryQuery
  object DailyUserActivityPrtnEntryQuery extends DailyUserActivityPrtnEntryQuery
  val queries : List[IQuery] = List(
    HourlyUserActivityPrtnEntryClstCountry,
    HourlyUserActivityPrtnEntryQuery,
    HourlyUserActivityClstEntryQuery,
    DailyUserActivityPrtnEntryQuery
  )
}
