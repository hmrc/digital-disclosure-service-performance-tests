/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.example

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.example.AuthLoginRequests.{DDSUrl}

object IndividualRequests extends ServicesConfiguration {

//  val baseUrl: String = baseUrlFor("example-frontend")
//  val route: String   = "/check-your-vat-flat-rate"

  val getHomePage: HttpRequestBuilder =
    http("Navigate to Home Page")
      .get(DDSUrl)
      .check(status.is(200))

//  val postHomePage: HttpRequestBuilder =
//    http("Post Home Page")
//      .post(DDSUrl: String)
//      .check(status.is(303))
//      .check(header("Location").is("/notification/letter-from-hmrc").saveAs("letterFromHMRCPage"))

  val getLetterFromHMRCPage: HttpRequestBuilder =
  http("Get Letter From HMRC Page")
    .get(DDSUrl + "/notification/letter-from-hmrc": String)
    .check(status.is(200))
    .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLetterFromHMRCPage: HttpRequestBuilder =
    http("POST Letter From HMRC Page")
    .post(DDSUrl + "/notification/letter-from-hmrc": String)
    .formParam("""value""", """true""")
    .formParam("csrfToken", s"$${csrfToken}")
    .check(status.is(303))
    .check(header("Location").is(s"$DDSUrl/notification/hmrc-letter-reference").saveAs("letterReference"))
}
