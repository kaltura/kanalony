package com.kaltura.enhancement

import com.kaltura.core.sessions.{KSParserBase}
import com.kaltura.model.dao.PartnerDAO
import com.kaltura.model.entities.Partner
import com.kaltura.model.events.RawPlayerEvent
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import com.datastax.spark.connector._
import java.util.concurrent.ConcurrentHashMap
import scala.collection.convert.decorateAsScala._


/**
 * Created by ofirk on 31/01/2016.
 */
object KSParser extends KSParserBase{

  var localCache: scala.collection.concurrent.Map[Int, Partner] = new ConcurrentHashMap[Int, Partner]().asScala
  localCache.putIfAbsent(-1, Partner(-1, None))

  def parseKS(playerEvents:RDD[RawPlayerEvent]):RDD[RawPlayerEvent] = {
    val partnersCache = playerEvents.sparkContext.cassandraTable[Partner]("schema_tests","dim_partners").map(partner => (partner.id,partner))
    playerEvents.map(rawPlayerEvent => (rawPlayerEvent.params.getOrElse("event:partnerId","-1").toInt,rawPlayerEvent)).leftOuterJoin(partnersCache)
      .map(joinedEventPartner => {
        val currentRow: RawPlayerEvent = joinedEventPartner._2._1
        val partnerId = currentRow.params.getOrElse("event:partnerId","-1").toInt
        val ks = currentRow.params.getOrElse("ks","")
        if(!localCache.contains(partnerId)) {
          localCache.putIfAbsent(partnerId,{
            println("Cache miss!")
            joinedEventPartner._2._2.getOrElse(PartnerDAO.getById(partnerId).getOrElse(Partner(partnerId)))
          })
        }
        val ksData = parse(ks).getOrElse(KSData(partnerId))
        currentRow.copy(params = currentRow.params + ("userId" -> ksData.userId))
      })
  }

  override def getPartnerSecret(partnerId:Int) = localCache.getOrElse(partnerId,Partner(partnerId)).secret.getOrElse("")

}
