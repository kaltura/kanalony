package utils

/**
 * Created by elad.benedict on 3/31/2016.
 */

object KafkaDriver {
  def main(args: Array[String]) {
    new KafkaConsumer().read(println)
  }
}
