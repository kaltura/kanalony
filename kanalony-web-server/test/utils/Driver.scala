import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.DataPopulator
import org.joda.time.DateTime

/**
 * Created by elad.benedict on 3/30/2016.
 */
object Driver {
  def main(args: Array[String]) {
    val dp = new DataPopulator(10,20,Metrics.values,List("Israel","England"),DateTime.now().minusDays(10), DateTime.now(),20)
    dp.populate(100000)
  }
}
