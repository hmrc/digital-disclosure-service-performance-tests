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

package uk.gov.hmrc.perftests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import AuthLoginRequests._

object YourInfoRequests extends ServicesConfiguration {

   val getYourFullNamePage: HttpRequestBuilder =
    http("GET Your Full Name Page")
      .get(DDSUrl + "/notification/your-full-name": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postYourFullNamePage: HttpRequestBuilder =
    http("POST Your Full Name Page")
      .post(DDSUrl + "/notification/your-full-name": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/your-telephone-number").saveAs("telNumber"))

  val getYourTelNumberPage: HttpRequestBuilder =
    http("GET Telephone Number Page")
      .get(baseUrl + s"$${telNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postYourTelNumberPage: HttpRequestBuilder =
    http("POST Telephone Number Page")
      .post(baseUrl + s"$${telNumber}": String)
      .formParam("""value""", """0123456789""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/contact-by-email").saveAs("doYouHaveEmail"))

  val getDoYouHaveEmailPage: HttpRequestBuilder =
    http("GET Do You Have Email Page")
      .get(baseUrl + s"$${doYouHaveEmail}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postDoYouHaveEmailPage: HttpRequestBuilder =
    http("POST Do You Have Email Page")
      .post(baseUrl + s"$${doYouHaveEmail}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/your-email-address").saveAs("yourEmail"))

  val getYourEmailPage: HttpRequestBuilder =
    http("GET Your Email Page")
      .get(baseUrl + s"$${yourEmail}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postYourEmailPage: HttpRequestBuilder =
    http("POST Your Email Page")
      .post(baseUrl + s"$${yourEmail}": String)
      .formParam("""value""", """test@example.com""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/your-address/lookup").saveAs("ddsYourAddressLookup"))

}
