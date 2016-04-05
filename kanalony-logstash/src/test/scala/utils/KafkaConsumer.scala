package utils

import java.util.Properties
import java.util.concurrent.atomic.AtomicLong

import kafka.consumer.{Consumer, ConsumerConfig, Whitelist}
import kafka.serializer.DefaultDecoder
import org.joda.time.DateTime

/**
 * Created by elad.benedict on 3/31/2016.
 */

class KafkaConsumer {

  val topic = "player-events"
  val groupId = "1"
  val zookeeperConnect = "il-bigdata-2:2181"
  val readFromStartOfStream = true

  val props = new Properties()
  props.put("group.id", groupId)
  props.put("zookeeper.connect", zookeeperConnect)
  props.put("auto.offset.reset", if(readFromStartOfStream) "smallest" else "largest")
  props.put("mirror.consumer.numthreads", "10")

  val config = new ConsumerConfig(props)
  val connector = Consumer.create(config)
  val filterSpec = new Whitelist(topic)

  val messageNum : AtomicLong = new AtomicLong(0)
  var lastSecNum : Long = 0
  var lastSec = 0
  val start = DateTime.now()

  val stream = connector.createMessageStreamsByFilter(filterSpec, 1, new DefaultDecoder(), new DefaultDecoder()).toList.head

  def read(write: (String)=>Unit) = {
    for(messageAndTopic <- stream) {
      //write("messageAndTopic.offset.toString + " " + new String(messageAndTopic.message, "UTF-8"))
      val length = messageNum.incrementAndGet()
      val now = DateTime.now()
      if (now.getSecondOfMinute != lastSec)
      {
        synchronized {
          println(s"${now.toLocalTime} ::: ${length - lastSecNum}")
          lastSecNum = length
          lastSec = now.getSecondOfMinute
        }
      }
    }
  }

  def close() {
    connector.shutdown()
  }
}