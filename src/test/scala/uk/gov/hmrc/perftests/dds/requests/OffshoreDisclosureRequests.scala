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

object OffshoreDisclosureRequests extends Configuration {

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute      = s"$digitalDisclosureRoute/notification"
  val onshoreRoute           = s"$digitalDisclosureRoute/onshore"
  val offshoreRoute          = s"$digitalDisclosureRoute/offshore"

  val navigateToReasonForDisclosureOffshore: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/reason-for-disclosure",
      s"$offshoreRoute/reason-for-disclosure"
    )

  def submitReasonForDisclosurePreferencesOffshore: HttpRequestBuilder =
    http(s"Submit reason for disclosure preferences")
      .post(s"$offshoreRoute/reason-for-disclosure")
      .formParam("value[0]", _ => "didNotNotifyHMRC")
      .formParam("value[1]", _ => "didNotFile")
      .formParam("value[2]", _ => "inaccurateReturn")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateWhyNotNotifyOffshore: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/reason-for-not-notify-tax-liability",
      s"$offshoreRoute/reason-for-not-notify-tax-liability"
    )

  def submitWhyNotNotifyOffshore: HttpRequestBuilder =
    submitPageWithFormParams(
      "reason for not notifying",
      s"$offshoreRoute/reason-for-not-notify-tax-liability",
      "value[2]",
      "deliberatelyDidNotNotify"
    )

  val navigateWhyNotFileOnTimeOffshore: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/reason-for-return-not-filed-on-time",
      s"$offshoreRoute/reason-for-return-not-filed-on-time"
    )

  def submitWhyNotFileOnTimeOffshore: HttpRequestBuilder =
    submitPageWithFormParams(
      "reason for not filing on time",
      s"$offshoreRoute/reason-for-return-not-filed-on-time",
      "value[2]",
      "deliberatelyWithheldInformation"
    )

  val navigateWhySubmittedInaccurateReturnOffshore: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/reason-for-inaccurate-return",
      s"$offshoreRoute/reason-for-inaccurate-return"
    )

  def submitWhySubmittedInaccurateReturnOffshore: HttpRequestBuilder =
    submitPageWithFormParams(
      "reason inaccurate return",
      s"$offshoreRoute/reason-for-inaccurate-return",
      "value[2]",
      "deliberatelyInaccurate"
    )

  val navigateToCDFOffshore: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/contractual-disclosure-facility",
      s"$offshoreRoute/contractual-disclosure-facility"
    )

  def submitCDFPreferencesOffshore(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$offshoreRoute/contractual-disclosure-facility"
    )

  val navigateToOffshoreYears: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/offshore-years",
      s"$offshoreRoute/offshore-years"
    )

  val submitOffshoreYearsSelection: HttpRequestBuilder =
    submitPageWithFormParams(
      "submit",
      s"$offshoreRoute/offshore-years",
      "value[3]",
      "2018"
    )

  val navigateToOffshoreLiabilitiesCountry: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/offshore-liabilities-country",
      s"$offshoreRoute/offshore-liabilities-country"
    )

  def submitOffshoreLiabilitiesCountryPreferences(value: String): HttpRequestBuilder =
    submitPageWithFormParams(
      s"Submit '$value'",
      s"$offshoreRoute/offshore-liabilities-country",
      "country",
      value
    )

  val navigateToCountriesOrTerritories: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/countries-or-territories",
      s"$offshoreRoute/countries-or-territories"
    )

  def submitCountriesOrTerritoriesPreferences(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$offshoreRoute/countries-or-territories"
    )

  val navigateToOffshoreLiabilities: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/offshore-liabilities/0",
      s"$offshoreRoute/offshore-liabilities/0"
    )

  val submitOffshoreLiabilities: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$offshoreRoute/offshore-liabilities/0")
      .formParam("income", _ => "2")
      .formParam("chargeableTransfers", _ => "2")
      .formParam("capitalGains", _ => "2")
      .formParam("unpaidTax", _ => "2")
      .formParam("interest", _ => "2")
      .formParam("penaltyRate", _ => "2")
      .formParam("penaltyRateReason", _ => "This is test")
      .formParam("undeclaredIncomeOrGain", _ => "This is test")
      .formParam("foreignTaxCredit", _ => "false")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateToLegalInterpretation: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/offshore-liabilities-interpretation",
      s"$offshoreRoute/offshore-liabilities-interpretation"
    )

  val submitLegalInterpretation: HttpRequestBuilder =
    submitPageWithFormParams(
      "submit",
      s"$offshoreRoute/offshore-liabilities-interpretation",
      "value[6]",
      "inheritanceTaxIssues"
    )

  val navigateToOffshoreLiabilitiesTaxNotIncluded: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/offshore-liabilities-tax-not-included",
      s"$offshoreRoute/offshore-liabilities-tax-not-included"
    )

  val submitOffshoreLiabilitiesTaxNotIncluded: HttpRequestBuilder =
    submitPage(
      "moreThanTenThousandLessThanOneLakh",
      s"$offshoreRoute/offshore-liabilities-tax-not-included"
    )

  val navigateToOffshoreMaxValueAssets: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/offshore-liabilities-maximum-value-of-assets",
      s"$offshoreRoute/offshore-liabilities-maximum-value-of-assets"
    )

  val submitOffshoreMaxValueAssets: HttpRequestBuilder =
    submitPage(
      "over1B",
      s"$offshoreRoute/offshore-liabilities-maximum-value-of-assets"
    )

  val navigateToOffshoreLiabilitiesSummary: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/offshore-liabilities-summary",
      s"$offshoreRoute/offshore-liabilities-summary"
    )

}
