package com.kaltura.enrichment

import com.kaltura.core.sessions.KSParserBase
import com.kaltura.model.cache.PartnerCache
import com.kaltura.model.entities.Partner
import com.kaltura.model.events.RawPlayerEvent
import org.apache.spark.rdd.RDD

/**
 * Created by ofirk on 31/01/2016.
 */
object EnrichByPartner extends IEnrich[Integer,Partner] with KSParserBase {

  override def loadEntity(partnerId: Integer) : Partner = PartnerCache.getById(partnerId)

  def enrich(playerEvents:RDD[RawPlayerEvent]):RDD[RawPlayerEvent] = {
    playerEvents.mapPartitionsWithIndex { (index,eventsPart) =>
      eventsPart.map { currentRow: RawPlayerEvent =>
        val partnerId = currentRow.params.getOrElse("event:partnerId","0").toInt // TODO: Potential NumberFormatException
        if (partnerId > 0) {
          val ks = currentRow.params.getOrElse("ks","")
          val ksData = parse(ks).getOrElse(KSData(partnerId))
          currentRow.copy(params = currentRow.params + ("userId" -> ksData.userId))
        }
        else currentRow
      }
    }
  }

  override def getPartnerSecret(partnerId:Int) = localCache.get(partnerId).secret.getOrElse("")

}
