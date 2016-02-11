package kanalony.storage.logic

/**
 * Created by elad.benedict on 2/11/2016.
 */

class QueryParamterException(message : String) extends IllegalArgumentException(message)

case object QueryParamsValidator {

  private def validateParam[T](name: String, input: Map[String, Any]): T = {
    try {
      val typedValue = input(name).asInstanceOf[T]
      typedValue
    }
    catch {
      case e: ClassCastException => throw new QueryParamterException("Param " + name + " has the wrong type")
    }
  }

  def validate[T1, T2, T3, T4, T5, T6, T7](paramNames: (String, String, String, String, String, String, String), input: Map[String, Any]): (T1, T2, T3, T4, T5, T6, T7) = paramNames match {
    case (p1, p2, p3, p4, p5, p6, p7) => {
      val typed_p7 = validateParam(p7, input)
      val others = validate[T1,T2,T3,T4,T5,T6]((p1, p2, p3, p4, p5, p6), input)
      (others._1, others._2, others._3, others._4, others._5, others._6, typed_p7)
    }
  }

  def validate[T1, T2, T3, T4, T5, T6](paramNames: (String, String, String, String, String, String), input: Map[String, Any]): (T1, T2, T3, T4, T5, T6) = paramNames match {
    case (p1, p2, p3, p4, p5, p6) => {
      val typed_p6 = validateParam(p6, input)
      val others = validate[T1,T2,T3,T4,T5]((p1, p2, p3, p4, p5), input)
      (others._1, others._2, others._3, others._4, others._5, typed_p6)
    }
  }

  def validate[T1, T2, T3, T4, T5](paramNames: (String, String, String, String, String), input: Map[String, Any]): (T1, T2, T3, T4, T5) = paramNames match {
    case (p1, p2, p3, p4, p5) => {
      val typed_p5 = validateParam[T5](p5, input)
      val others = validate[T1,T2,T3,T4]((p1, p2, p3, p4), input)
      (others._1, others._2, others._3, others._4, typed_p5)
    }
  }

  def validate[T1, T2, T3, T4](paramNames: (String, String, String, String), input: Map[String, Any]): (T1, T2, T3, T4) = paramNames match {
    case (p1, p2, p3, p4) => {
      val typed_p4 = validateParam[T4](p4, input)
      val others = validate[T1,T2,T3]((p1, p2, p3), input)
      (others._1, others._2, others._3, typed_p4)
    }
  }

  def validate[T1, T2, T3](paramNames: (String, String, String), input: Map[String, Any]): (T1, T2, T3) = paramNames match {
    case (p1, p2, p3) => {
      val typed_p3 = validateParam[T3](p3, input)
      val others = validate[T1,T2]((p1, p2), input)
      (others._1, others._2, typed_p3)
    }
  }

  def validate[T1, T2](paramNames: (String, String), input: Map[String, Any]): (T1, T2) = paramNames match {
    case (p1, p2) => {
      val typed_p2 = validateParam[T2](p2, input)
      val typed_p1 = validate[T1](p1, input)
      (typed_p1, typed_p2)
    }
  }

  def validate[T1](paramName: String, input: Map[String, Any]): T1 = {
    val res = validateParam[T1](paramName, input)
    res
  }
}