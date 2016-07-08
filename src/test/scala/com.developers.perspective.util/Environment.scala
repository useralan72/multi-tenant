/**
  * Created by dmumma on 11/19/15.
  */

package com.developers.perspective.util

import java.util

object Environemnt {
  val baseURL = scala.util.Properties.envOrElse("baseURL", "http://localhost:9001/meters")
  val users = scala.util.Properties.envOrElse("numberOfUsers", "100")
  val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "5000") // in milliseconds

}
