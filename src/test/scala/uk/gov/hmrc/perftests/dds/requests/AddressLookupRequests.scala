/*
 * Copyright 2026 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.dds.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.dds.Configuration
import uk.gov.hmrc.perftests.dds.requests.BaseRequests._

object AddressLookupRequests extends Configuration {

  val addressLookupRoute = s"$addressLookupUrl/lookup-address"

  val navigateToBegin: HttpRequestBuilder =
    http("Navigate to /begin")
      .get(addressLookupRoute + "/#{addressLookupJourneyId}/begin")
      .check(status.is(303))

  val navigateToCountryPicker: HttpRequestBuilder =
    navigateToPage(
      "/country-picker",
      addressLookupRoute + "/#{addressLookupJourneyId}/country-picker"
    )

  def submitCountryPicker(country: String, countryCode: String): HttpRequestBuilder =
    http(s"Submit '$country ($countryCode)'")
      .post(addressLookupRoute + "/#{addressLookupJourneyId}/country-picker")
      .formParam("countryCodeAutocomplete", _ => country)
      .formParam("countryCode", _ => countryCode)
      .formParam("continue", _ => "")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateToLookup: HttpRequestBuilder =
    navigateToPage(
      "/lookup",
      addressLookupRoute + "/#{addressLookupJourneyId}/lookup"
    )

  val navigateToEdit: HttpRequestBuilder =
    navigateToPage(
      "/edit",
      addressLookupRoute + "/#{addressLookupJourneyId}/edit"
    )

  def submitEdit(
    addressLineOne: String,
    addressLineTwo: String,
    town: String,
    postcode: String,
    countryCode: String
  ): HttpRequestBuilder =
    http(s"Submit '$addressLineOne, $addressLineTwo, $town, $postcode, $countryCode'")
      .post(addressLookupRoute + "/#{addressLookupJourneyId}/edit")
      .formParam("organisation", _ => "")
      .formParam("line1", _ => addressLineOne)
      .formParam("line2", _ => addressLineTwo)
      .formParam("line3", _ => "")
      .formParam("town", _ => town)
      .formParam("postcode", _ => postcode)
      .formParam("countryCode", _ => countryCode)
      .formParam("continue", _ => "")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateToConfirm: HttpRequestBuilder =
    navigateToPage(
      "/confirm",
      addressLookupRoute + "/#{addressLookupJourneyId}/confirm"
    )

  def submitConfirm(): HttpRequestBuilder =
    submitPageWithFormParams(
      "/confirm",
      addressLookupRoute + "/#{addressLookupJourneyId}/confirm",
      "continue",
      ""
    )

}
