package controllers

/**
 * Created by elad.benedict on 2/22/2016.
 */

class InvalidDimensionException(s: String) extends Exception("Dimension " + s + " not supported") {}
