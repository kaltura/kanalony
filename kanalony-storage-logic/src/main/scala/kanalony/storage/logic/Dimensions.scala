package kanalony.storage.logic

import com.datastax.spark.connector.ColumnName
import kanalony.storage.generator.ColumnNames

object Dimensions extends Enumeration {
  val partner,entry,knownUserId,device,operatingSystem,browser,
  country,city,syndicationDomain,syndicationURL,application,
  category,playbackContext,month,day,hour,minute,tenSeconds,streamingProtocol,
  expectedQuality,uiConfID,metric,cf1,cf2,cf3,referrer = Value

  val computedDimensions = Set(day)

  implicit def fromColumnName(name : ColumnNames.Value) : Dimensions.Value = name match {
    case ColumnNames.application => Dimensions.application
    case ColumnNames.browser => Dimensions.browser
    case ColumnNames.cf1 => Dimensions.cf1
    case ColumnNames.cf2 => Dimensions.cf2
    case ColumnNames.cf3 => Dimensions.cf3
    case ColumnNames.city => Dimensions.city
    case ColumnNames.country => Dimensions.country
    case ColumnNames.device => Dimensions.device
    case ColumnNames.domain => Dimensions.syndicationDomain
    case ColumnNames.entry_id => Dimensions.entry
    case ColumnNames.hour => Dimensions.hour
    case ColumnNames.metric => Dimensions.metric
    case ColumnNames.minute => Dimensions.minute
    case ColumnNames.month => Dimensions.month
    case ColumnNames.`operating_system` => Dimensions.operatingSystem
    case ColumnNames.partner_id => Dimensions.partner
    case ColumnNames.playbackContext=> Dimensions.playbackContext
    case ColumnNames.referrer => Dimensions.referrer
    case other => throw new IllegalArgumentException(s"column ${other} has no corresponding dimension")
  }
}
