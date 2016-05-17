package controllers

/**
 * Created by elad.benedict on 2/22/2016.
 */

class InvalidDimensionException(val dimensionName : String) extends Exception("Dimension " + dimensionName + " not supported") {}
