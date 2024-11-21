package gatling

import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory
import utils.PropertiesReader

abstract class BaseSimulation extends Simulation {
    val propertiesReader = new PropertiesReader("properties-from-pom.properties")
    def getBaseUrl(): String = propertiesReader.getProperty("endpoint")
    // Extracting a map of headers allows you to reuse these in several requests
    val defaultRequestTimeout = 10000
    val defaultWaitTime = 5

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Set to TRACE for all requests
    context.getLogger("io.gatling.http").setLevel(Level.OFF)

    val httpProtocol = http
        .baseUrl(getBaseUrl())
        .acceptHeader("application/json, text/plain, */*")
        .header("content-type", "application/json")
        .header("appVersion", "3.1.3.3.29")
        .doNotTrackHeader("1")
        .acceptLanguageHeader("en-US,en;q=0.9")
        .acceptEncodingHeader("gzip, deflate, br")
        .userAgentHeader("Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.61 Mobile Safari/537.36")
}
