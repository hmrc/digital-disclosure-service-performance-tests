/*
 * Copyright 2023 HM Revenue & Customs
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

object OffshoreDisclosureRequests extends Configuration{

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute = s"$digitalDisclosureRoute/notification"
  val onshoreRoute = s"$digitalDisclosureRoute/onshore"
  val offshoreRoute = s"$digitalDisclosureRoute/offshore"


  val navigateToReasonForDisclosureOffshore: HttpRequestBuilder =
    http("Navigate to /reason-for-disclosure")
      .get(s"$offshoreRoute/reason-for-disclosure")
      .check(status.is(200))

  def submitReasonForDisclosurePreferencesOffshore: HttpRequestBuilder =
    http(s"Submit reason for disclosure preferences")
      .post(s"$offshoreRoute/reason-for-disclosure")
      .formParam("value[5]", "deliberatelyDidNotNotify")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToCDFOffshore: HttpRequestBuilder =
    http("Navigate to /contractual-disclosure-facility")
      .get(s"$offshoreRoute/contractual-disclosure-facility")
      .check(status.is(200))

  def submitCDFPreferencesOffshore(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$offshoreRoute/contractual-disclosure-facility")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOffshoreYears: HttpRequestBuilder =
    http("Navigate to /offshore-years")
      .get(s"$offshoreRoute/offshore-years")
      .check(status.is(200))

  val submitOffshoreYearsSelection: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$offshoreRoute/offshore-years")
      .formParam("value[3]", "2018")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOffshoreLiabilitiesCountry: HttpRequestBuilder =
    http("Navigate to /offshore-liabilities-country")
      .get(s"$offshoreRoute/offshore-liabilities-country")
      .check(status.is(200))

  def submitOffshoreLiabilitiesCountryPreferences(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$offshoreRoute/offshore-liabilities-country")
      .formParam("country", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToCountriesOrTerritories: HttpRequestBuilder =
    http("Navigate to /countries-or-territories")
      .get(s"$offshoreRoute/countries-or-territories")
      .check(status.is(200))

  def submitCountriesOrTerritoriesPreferences(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$offshoreRoute/countries-or-territories")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOffshoreLiabilities: HttpRequestBuilder =
    http("Navigate to /offshore-liabilities/0")
      .get(s"$offshoreRoute/offshore-liabilities/0")
      .check(status.is(200))

  val submitOffshoreLiabilities: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$offshoreRoute/offshore-liabilities/0")
      .formParam("income", "2")
      .formParam("chargeableTransfers", "2")
      .formParam("capitalGains", "2")
      .formParam("unpaidTax", "2")
      .formParam("interest", "2")
      .formParam("penaltyRate", "2")
      .formParam("penaltyRateReason", "This is test")
      .formParam("undeclaredIncomeOrGain", "This is test")
      .formParam("foreignTaxCredit", "false")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToLegalInterpretation: HttpRequestBuilder =
    http("Navigate to /offshore-liabilities-interpretation")
      .get(s"$offshoreRoute/offshore-liabilities-interpretation")
      .check(status.is(200))

  val submitLegalInterpretation: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$offshoreRoute/offshore-liabilities-interpretation")
      .formParam("value[6]", "inheritanceTaxIssues")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOffshoreLiabilitiesTaxNotIncluded: HttpRequestBuilder =
    http("Navigate to /offshore-liabilities-tax-not-included")
      .get(s"$offshoreRoute/offshore-liabilities-tax-not-included")
      .check(status.is(200))

  val submitOffshoreLiabilitiesTaxNotIncluded: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$offshoreRoute/offshore-liabilities-tax-not-included")
      .formParam("value", "moreThanTenThousandLessThanOneLakh")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOffshoreMaxValueAssets: HttpRequestBuilder =
    http("Navigate to /offshore-liabilities-maximum-value-of-assets")
      .get(s"$offshoreRoute/offshore-liabilities-maximum-value-of-assets")
      .check(status.is(200))

  val submitOffshoreMaxValueAssets: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$offshoreRoute/offshore-liabilities-maximum-value-of-assets")
      .formParam("value", "over1B")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOffshoreLiabilitiesSummary: HttpRequestBuilder =
    http("Navigate to /offshore-liabilities-summary")
      .get(s"$offshoreRoute/offshore-liabilities-summary")
      .check(status.is(200))


}
