package kanalony.storage.logic.queries

import com.kaltura.model.entities.{Metric, Metrics}
import com.websudos.phantom.dsl.ResultSet
import kanalony.storage.DbClientFactory.HourlyAggPrtnEntryClstCountryTableAccessor
import kanalony.storage.generated.HourlyAggPrtnEntryClstCountryRow
import org.joda.time.{Hours, DateTime}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.collection.immutable.IndexedSeq
import scala.concurrent.Future
import scala.util.Random

/**
 * Created by elad.benedict on 3/30/2016.
 */

class DataPopulator(numberOfPartners : Int, numberOfEntriesPerPartner : Int, metrics : Set[Metric], countries : List[String], startTime : DateTime, endTime : DateTime, maxValue : Int)  {

  val availableMetrics = metrics.toList
  val numOfHours = Hours.hoursBetween(startTime, endTime).getHours
  val numOfCountries = countries.length

  def generateRow(): HourlyAggPrtnEntryClstCountryRow = {

    val partnerId = Random.nextInt(numberOfPartners)
    val entryNum = Random.nextInt(numberOfEntriesPerPartner)
    val entryId = s"${partnerId}_Entry_${entryNum}"
    val metricIndex = Random.nextInt(availableMetrics.length)
    val hour = startTime.plusHours(Random.nextInt(numOfHours))
    val year = hour.getYear
    val country = countries(Random.nextInt(numOfCountries))
    val value = Random.nextInt(maxValue)
    HourlyAggPrtnEntryClstCountryRow(partnerId,entryId,availableMetrics(metricIndex).name,year,hour,country,value)
  }

  def populate(numOfInserts : Int) : Future[IndexedSeq[ResultSet]] = {
    val res = for {
      i <- 1 to numOfInserts
    } yield HourlyAggPrtnEntryClstCountryTableAccessor.store(generateRow())
    Future.sequence(res)
  }
}
