import com.kaltura.model.entities.Metrics
import kanalony.storage.logic.queries.DailyCountQuery
import kanalony.storage.logic.{QueryLocator, QueryParams, Dimensions, IQuery}
import kanalony.storage.logic.generated.Queries
import kanalony.storage.logic.queries.model.{QueryDimensionDefinition, QueryConstraint}
import org.joda.time.{LocalDateTime, DateTime}

/**
  * Created by elad.benedict on 3/16/2016.
  */

case class QuerySpecification(dimensions : List[String], metrics : String, filters : String) {

  val origDimensionListString = dimensions.mkString(",")
  lazy val orderdDimensionListString = dimensions.sorted.mkString(",")

  override def toString() = {
    s"dimensions: ${origDimensionListString}\nfilters: ${filters}\n"
  }
}

object QueryEnumeratorDriver {

  def getQueryData(q: IQuery) : QuerySpecification = {
    val dimensions = q.dimensionInformation.map(_.dimension.toString)
    val metrics = q.supportedWellKnownMetrics.map(_.toString).mkString(",")
    val filters = q.dimensionInformation.filter(_.constraint.constraint == QueryConstraint.Equality).map(_.dimension.toString).mkString(",")
    QuerySpecification(dimensions, metrics, filters)
  }

  def printQuery: ((String, List[QuerySpecification])) => Unit = {
    kv => {
      val dimensions = kv._2.head.origDimensionListString
      val metrics = kv._2.head.metrics
      val filters = kv._2.map(_.filters).mkString("\n")
      println(s"dimensions:\n==================\n${dimensions}\n\nfilters:\n==================\n${filters}\n\n\n")
    }
  }

  def main(args: Array[String]) {
    var qMap = Queries.queries.map(getQueryData).groupBy(q => q.orderdDimensionListString)
    qMap.foreach(printQuery)

    qMap = Queries.queries.filter(q => q.dimensionInformation.find(_.dimension == Dimensions.hour).isDefined).map(q => {
      val qp = QueryParams(q.dimensionInformation.map(x => QueryDimensionDefinition(if (x.dimension == Dimensions.hour) { Dimensions.day} else { x.dimension}, x.constraint, false)), List(Metrics.play), new LocalDateTime(0), new LocalDateTime(0))
      getQueryData(new DailyCountQuery(qp, QueryLocator))
    }).groupBy(q => q.orderdDimensionListString)
    qMap.foreach(printQuery)
  }
}
