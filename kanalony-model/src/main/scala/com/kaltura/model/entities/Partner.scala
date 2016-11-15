package com.kaltura.model.entities

case class Partner (
                      id: Int,
                      secret: Option[String]=None,
                      crmId: Option[String]=None,
                      packageId: Option[Int]=None
                    )