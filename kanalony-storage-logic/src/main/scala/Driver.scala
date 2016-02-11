package kanalony.storage.logic

import org.joda.time.DateTime
import scala.util.{Success,Failure}
import scala.collection.immutable.Map
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by elad.benedict on 2/11/2016.
 */

object Driver {
    def main(args: Array[String]) {
        val h = Queries.HourlyUserActivityPrtnEntryClstCountry
        val queryResult = h.execute(Map("entryId" -> "123", "partnerId" -> 123, "start" -> new DateTime(2016,1,2,3,2), "end"->new DateTime(2017,1,2,3,2), "metric"->5))
        queryResult onComplete {
            case Success(data) => println(data)
            case Failure(e) => println(e)
        }
    }
}
