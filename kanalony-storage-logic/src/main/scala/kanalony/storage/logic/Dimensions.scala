package kanalony.storage.logic

import com.datastax.spark.connector.ColumnName
import kanalony.storage.generator.ColumnNames

object Dimensions extends Enumeration {
  val partner,entry,knownUserId,device,operatingSystem,browser,
  country,city,syndicationDomain,syndicationURL,application,
  category,playbackContext,month,day,hour,minute,tenSeconds,streamingProtocol,
  expectedQuality,uiConfID,metric,cf1,cf2,cf3,referrer = Value

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
    case ColumnNames.os => Dimensions.operatingSystem
    case ColumnNames.partner => Dimensions.partner
    case ColumnNames.playbackContext=> Dimensions.playbackContext
    case ColumnNames.referrer => Dimensions.referrer
    case _ => throw new IllegalArgumentException(s"column ${_} has no corresponding dimension")
  }
}
