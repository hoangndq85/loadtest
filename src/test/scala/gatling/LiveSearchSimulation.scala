package gatling

import io.gatling.core.Predef.{incrementConcurrentUsers, heavisideUsers, _}
import io.gatling.http.Predef._
import io.gatling.http.Predef.http
import io.gatling.jsonpath.JsonPath
import utils.LiveSearchParamsGenerator
import scala.concurrent.duration._

class LiveSearchSimulation extends BaseSimulation{
    val generator = new LiveSearchParamsGenerator()
    val feeder = Iterator.continually(Map("queryString" -> generator.generateSearchParams()))

    val serpRequest = http("GetProperties").get("/api/search").check(status is 200)

    val scn = scenario("Search Function").feed(feeder).exec(serpRequest).pause(defaultWaitTime)

    setUp(
    scn.inject(
      incrementConcurrentUsers(500)
      .times(20)
      .eachLevelLasting(10.seconds)
      .separatedByRampsLasting(5.seconds)
      .startingFrom(10)
    )
  ).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(defaultRequestTimeout),
      forAll.failedRequests.percent.lte(5)
    )
}