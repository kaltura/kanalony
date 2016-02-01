import com.kaltura.model.dao.PartnerDAO
import com.kaltura.model.entities.Partner
import org.scalatest.{BeforeAndAfterAll, Matchers, FlatSpec}
import scala.math.pow
/**
 * Created by ofirk on 28/01/2016.
 */
class DAOTests extends FlatSpec with Matchers with BeforeAndAfterAll  {

  "PartnerDAO.getById" should "not be empty" in {
    val partner: Option[Partner] = PartnerDAO.getById(1091)
    partner.isEmpty shouldBe false

    time {
      Range(1, 50000).foreach { i =>
        PartnerDAO.getById(i)
      }
    }
  }

  "PartnerDAO.findById" should "not be empty" in {
    val partner: Option[Partner] = PartnerDAO.findById(1234)
    partner.isEmpty shouldBe false

    time {
      Range(1, 50000).foreach { i =>
        PartnerDAO.findById(i)
        //partner.isEmpty shouldBe false
      }
    }

  }

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0)/pow(10,9) + "s")
    result
  }

}