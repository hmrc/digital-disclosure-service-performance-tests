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
import uk.gov.hmrc.perftests.example.AddressLookupRequests.saveSubjectAddressLookupId
import uk.gov.hmrc.perftests.example.AuthLoginRequests._

object TrustRequests extends ServicesConfiguration {

  val postWhatIsDisclosureAboutPage_Trust: HttpRequestBuilder =
    http("POST What Is This Disclosure About Page")
      .post(baseUrl + s"$${whatIsThisDisclosureAbout}": String)
      .formParam("""value""", """trust""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/are-you-a-trustee").saveAs("areYouTrustee"))

  val getAreYouTrusteePage: HttpRequestBuilder =
    http("GET Are You Trustee Page")
      .get(baseUrl + s"$${areYouTrustee}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postAreYouTrusteePage: HttpRequestBuilder =
    http("POST Are You Trustee Page")
      .post(baseUrl + s"$${areYouTrustee}": String)
      .formParam("""value""", """false""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/representing-organisation").saveAs("areYouRepresentingAnOrg"))

  val postOnshoreLiabilitiesPage_Trust: HttpRequestBuilder =
    http("POST Onshore Liabilities Page")
      .post(baseUrl + s"$${onshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/trust-name").saveAs("trustName"))

  val getTrustNamePage: HttpRequestBuilder =
    http("GET Trust Name Page")
      .get(baseUrl + s"$${trustName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postTrustNamePage: HttpRequestBuilder =
    http("POST Trust Name Page")
      .post(baseUrl + s"$${trustName}": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/trust-address/lookup").saveAs("ddsLookup"))

  val getTrustAddressLookupPage: HttpRequestBuilder =
    http("GET Address Lookup Page")
      .get(baseUrl + s"$${ddsLookup}": String)
      .check(status.is(303))
      .check(saveSubjectAddressLookupId)
      .check(header("Location").is(alfeUrl + s"/$${subjectAddressLookupId}" + "/begin").saveAs("alfeBegin"))

  val postALFETrustConfirmPage: HttpRequestBuilder =
    http("POST ALFE Confirm Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeSubjectConfirm}": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSUrl/notification/trust-address/retrieve?id=$${subjectAddressLookupId}").saveAs("ddsRetrieveAddress"))

}
