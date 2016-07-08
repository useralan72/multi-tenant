package com.developers.perspective.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by dmumma on 11/20/15.
  */
object GetMeterById {

  val getMetersByIdHttp = http("get meter by id")
    .get("/meters")
    .pathParam(1000)
    .check(status.is(200))

  val getUserByLastName = scenario("Get meter by id")
     .exec(
       getMetersByIdHttp
     )
}
