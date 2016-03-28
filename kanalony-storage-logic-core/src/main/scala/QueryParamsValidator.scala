package kanalony.storage.logic

import kanalony.storage.logic.queries.model.DimensionEqualityConstraint

/**
 * Created by elad.benedict on 2/11/2016.
 */

class QueryParamterException(message : String) extends IllegalArgumentException(message)

case object QueryParamsValidator {

  private def extractEqualityConstraintParam[T](dimension : Dimensions.Value, queryParams : QueryParams): List[T] = {
    val dimDef = queryParams.dimensionDefinitions.find(_.dimension == dimension)
    try {
      dimDef match {
        case Some(eqCons) => {
          eqCons.constraint.asInstanceOf[DimensionEqualityConstraint[T]].values.toList
        }
        case _ => {
          throw new IllegalArgumentException("Equality constraint for dimension " + dimension.toString + " not found")
        }
      }
    }
    catch {
      case e: ClassCastException => throw new QueryParamterException("Param " + dimension + " has the wrong type")
    }
  }

  def extractEqualityConstraintParams[T1, T2, T3, T4, T5, T6, T7](paramNames: (Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value), input: QueryParams): (List[T1], List[T2], List[T3], List[T4], List[T5], List[T6], List[T7]) = paramNames match {
    case (p1, p2, p3, p4, p5, p6, p7) => {
      val typed_p7 = extractEqualityConstraintParam[T7](p7, input)
      val others = extractEqualityConstraintParams[T1,T2,T3,T4,T5,T6]((p1, p2, p3, p4, p5, p6), input)
      (others._1, others._2, others._3, others._4, others._5, others._6, typed_p7)
    }
  }

  def extractEqualityConstraintParams[T1, T2, T3, T4, T5, T6](paramNames: (Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value), input: QueryParams): (List[T1], List[T2], List[T3], List[T4], List[T5], List[T6]) = paramNames match {
    case (p1, p2, p3, p4, p5, p6) => {
      val typed_p6 = extractEqualityConstraintParam[T6](p6, input)
      val others = extractEqualityConstraintParams[T1,T2,T3,T4,T5]((p1, p2, p3, p4, p5), input)
      (others._1, others._2, others._3, others._4, others._5, typed_p6)
    }
  }

  def extractEqualityConstraintParams[T1, T2, T3, T4, T5](paramNames: (Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value), input: QueryParams): (List[T1], List[T2], List[T3], List[T4], List[T5]) = paramNames match {
    case (p1, p2, p3, p4, p5) => {
      val typed_p5 = extractEqualityConstraintParam[T5](p5, input)
      val others = extractEqualityConstraintParams[T1,T2,T3,T4]((p1, p2, p3, p4), input)
      (others._1, others._2, others._3, others._4, typed_p5)
    }
  }

  def extractEqualityConstraintParams[T1, T2, T3, T4](paramNames: (Dimensions.Value, Dimensions.Value, Dimensions.Value, Dimensions.Value), input: QueryParams): (List[T1], List[T2], List[T3], List[T4]) = paramNames match {
    case (p1, p2, p3, p4) => {
      val typed_p4 = extractEqualityConstraintParam[T4](p4, input)
      val others = extractEqualityConstraintParams[T1,T2,T3]((p1, p2, p3), input)
      (others._1, others._2, others._3, typed_p4)
    }
  }
  
  def extractEqualityConstraintParams[T1, T2, T3](paramNames: (Dimensions.Value, Dimensions.Value, Dimensions.Value), input: QueryParams): (List[T1], List[T2], List[T3]) = paramNames match {
    case (p1, p2, p3) => {
      val typed_p3 = extractEqualityConstraintParam[T3](p3, input)
      val others = extractEqualityConstraintParams[T1,T2]((p1, p2), input)
      (others._1, others._2, typed_p3)
    }
  }

  def extractEqualityConstraintParams[T1, T2](paramNames: (Dimensions.Value, Dimensions.Value), input: QueryParams): (List[T1], List[T2]) = paramNames match {
    case (p1, p2) => {
      val typed_p2 = extractEqualityConstraintParam[T2](p2, input)
      val typed_p1 = extractEqualityConstraintParam[T1](p1, input)
      (typed_p1, typed_p2)
    }
  }

  def extractEqualityConstraintParams[T](dimension : Dimensions.Value, input: QueryParams): List[T] = {
    extractEqualityConstraintParam(dimension, input)
  }
}