package com.kaltura.model.entities

/**
 * Created by ofirk on 29/03/2016.
 */
object PlayerEventTypes extends Enumeration {
  val unknown =           Value(0,"unknown")
  val playerImpression =  Value(1,"playerImpression")
  val playRequested =     Value(2,"playRequested")
  val play =              Value(3,"play")
  val resume =            Value(4,"resume")
  val playReached25 =     Value(11,"playThrough25")
  val playReached50 =     Value(12,"playThrough50")
  val playReached75 =     Value(13,"playThrough75")
  val playReached100 =    Value(14,"playThrough100")
  val shareClicked =      Value(21,"shareClicked")
  val shared =            Value(22,"shared")
  val downloadClicked =   Value(23,"downloadClicked")
  val reportClicked =     Value(24,"reportClicked")
  val reportSubmitted	=   Value(25,"reportSubmitted")
  val enterFullscreen =   Value(31,"enterFullscreen")
  val exitFullscreen =    Value(32,"exitFullscreen")
  val pauseClicked =      Value(33,"pauseClicked")
  val replay =            Value(34,"replay")
  val seek =              Value(35,"seek")
  val relatedClicked =    Value(36,"relatedClicked")
  val relatedSelected =   Value(37,"relatedSelected")
  val captions =          Value(38,"captions")
  val sourceSelected =    Value(39,"sourceSelected")
  val info =              Value(40,"info")
  val speed =             Value(41,"speed")
  val view =              Value(99,"view")

  def apply(eventType:String): PlayerEventTypes.Value = {
    val eventTypeInt =
      try {
        Some(eventType.toInt)
      } catch {
        case e: Exception => None
      }
    PlayerEventTypes.values.find(_.id == eventTypeInt.getOrElse(0)).getOrElse(PlayerEventTypes.unknown)
  }
}
