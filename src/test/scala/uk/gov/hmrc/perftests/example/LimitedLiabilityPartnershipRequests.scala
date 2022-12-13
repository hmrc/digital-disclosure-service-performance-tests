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

object LimitedLiabilityPartnershipRequests extends ServicesConfiguration {

  val postWhatIsDisclosureAboutPage_LLP: HttpRequestBuilder =
    http("POST What Is This Disclosure About Page")
      .post(baseUrl + s"$${whatIsThisDisclosureAbout}": String)
      .formParam("""value""", """llp""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/are-you-a-designated-member").saveAs("areYouDesignatedMember"))

  val getAreYouDesignatedMemberPage: HttpRequestBuilder =
    http("GET Are You A Designated Member Page")
      .get(baseUrl + s"$${areYouDesignatedMember}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postAreYouDesignatedMemberPage: HttpRequestBuilder =
    http("POST Are You A Designated Member Page")
      .post(baseUrl + s"$${areYouDesignatedMember}": String)
      .formParam("""value""", """false""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/representing-organisation").saveAs("areYouRepresentingAnOrg"))

  val postOnshoreLiabilitiesPage_LLP: HttpRequestBuilder =
    http("POST Onshore Liabilities Page")
      .post(baseUrl + s"$${onshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/llp-name").saveAs("llpName"))

  val getLLPNamePage: HttpRequestBuilder =
    http("GET LLP Name Page")
      .get(baseUrl + s"$${llpName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPNamePage: HttpRequestBuilder =
    http("POST LLP Name Page")
      .post(baseUrl + s"$${llpName}": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/llp-address/lookup").saveAs("ddsLookup"))

  val getLLPAddressLookupPage: HttpRequestBuilder =
    http("GET Address Lookup Page")
      .get(baseUrl + s"$${ddsLookup}": String)
      .check(status.is(303))
      .check(saveSubjectAddressLookupId)
      .check(header("Location").is(alfeUrl + s"/$${subjectAddressLookupId}" + "/begin").saveAs("alfeBegin"))

  val postALFELLPConfirmPage: HttpRequestBuilder =
    http("POST ALFE Confirm Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeSubjectConfirm}": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSUrl/notification/llp-address/retrieve?id=$${subjectAddressLookupId}").saveAs("ddsRetrieveAddress"))

}
