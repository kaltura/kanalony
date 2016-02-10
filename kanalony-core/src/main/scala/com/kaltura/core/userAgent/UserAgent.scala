package com.kaltura.core.userAgent

import com.kaltura.core.userAgent.enums.{OperatingSystem,Browser,Device}

/**
 * Created by ofirk on 20/10/2015.
 */
case class UserAgent (browser:Browser.Value,
                      operatingSystem: OperatingSystem.Value,
                      device: Device.Value)
