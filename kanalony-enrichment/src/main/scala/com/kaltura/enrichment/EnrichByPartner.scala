package com.kaltura.enrichment

import java.util.concurrent.ConcurrentHashMap

import com.kaltura.core.sessions.KSParserBase
import com.kaltura.model.cache.PartnerCache
import com.kaltura.model.entities.Partner
import com.kaltura.model.events.RawPlayerEvent
import org.apache.spark.rdd.RDD

import scala.collection.convert.decorateAsScala._

/**
 * Created by ofirk on 31/01/2016.
 */
object EnrichByPartner extends IEnrich with KSParserBase {
  type IDType = Int
  type EnrichType = Partner
  override def createLocalCache = new ConcurrentHashMap[Int, Partner]().asScala
  localCache.put(-1, Partner(-1))

  def enrich(playerEvents:RDD[RawPlayerEvent]):RDD[RawPlayerEvent] = {
    playerEvents.mapPartitionsWithIndex { (index,eventsPart) =>
      eventsPart.map { currentRow: RawPlayerEvent =>
        val partnerId = currentRow.params.getOrElse("event:partnerId","-1").toInt
        val ks = currentRow.params.getOrElse("ks","")
        if(!localCache.contains(partnerId)) {
          localCache.putIfAbsent(partnerId,{
            println(s"Local cache miss, partition $index")
            PartnerCache.getById(partnerId)
          })
        }
        val ksData = parse(ks).getOrElse(KSData(partnerId))
        currentRow.copy(params = currentRow.params + ("userId" -> ksData.userId))
      }
    }
  }

  override def getPartnerSecret(partnerId:Int) = localCache.getOrElse(partnerId,Partner(partnerId)).secret.getOrElse("")

}
