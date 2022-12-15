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

package uk.gov.hmrc.perftests.dds.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.dds.Configuration

object AddressLookupRequests extends Configuration {

  val addressLookupRoute = s"$addressLookupUrl/lookup-address"

  val navigateToBegin: HttpRequestBuilder =
    http("Navigate to /begin")
      .get(addressLookupRoute + "/${addressLookupJourneyId}/begin")
      .check(status.is(303))

  val navigateToCountryPicker: HttpRequestBuilder =
    http("Navigate to /country-picker")
      .get(addressLookupRoute + "/${addressLookupJourneyId}/country-picker")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitCountryPicker(country: String, countryCode: String): HttpRequestBuilder =
    http(s"Submit '$country ($countryCode)'")
      .post(addressLookupRoute + "/${addressLookupJourneyId}/country-picker")
      .formParam("countryCodeAutocomplete", country)
      .formParam("countryCode", countryCode)
      .formParam("continue", "")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToLookup: HttpRequestBuilder =
    http("Navigate to /lookup")
      .get(addressLookupRoute + "/${addressLookupJourneyId}/lookup")
      .check(status.is(200))
      .check(saveCsrfToken)

  val navigateToEdit: HttpRequestBuilder =
    http("Navigate to /edit")
      .get(addressLookupRoute + "/${addressLookupJourneyId}/edit")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitEdit(
    addressLineOne: String,
    addressLineTwo: String,
    town: String,
    postcode: String,
    countryCode: String
  ): HttpRequestBuilder =
    http(s"Submit '$addressLineOne, $addressLineTwo, $town, $postcode, $countryCode'")
      .post(addressLookupRoute + "/${addressLookupJourneyId}/edit")
      .formParam("organisation", "")
      .formParam("line1", addressLineOne)
      .formParam("line2", addressLineTwo)
      .formParam("line3", "")
      .formParam("town", town)
      .formParam("postcode", postcode)
      .formParam("countryCode", countryCode)
      .formParam("continue", "")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToConfirm: HttpRequestBuilder =
    http("Navigate to /confirm")
      .get(addressLookupRoute + "/${addressLookupJourneyId}/confirm")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitConfirm(): HttpRequestBuilder =
    http(s"Submit confirm")
      .post(addressLookupRoute + "/${addressLookupJourneyId}/confirm")
      .formParam("continue", "")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

}
