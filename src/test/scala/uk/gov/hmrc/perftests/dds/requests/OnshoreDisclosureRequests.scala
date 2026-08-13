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

object OnshoreDisclosureRequests extends Configuration {

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute = s"$digitalDisclosureRoute/notification"
  val onshoreRoute = s"$digitalDisclosureRoute/onshore"

  val navigateToReasonForDisclosure: HttpRequestBuilder = {
    navigateToPageWithoutCsrf(
      "/reason-for-disclosure",
      s"$onshoreRoute/reason-for-disclosure"
    )
  }

  def submitReasonForDisclosurePreferences: HttpRequestBuilder =
    http(s"Submit reason for disclosure preferences")
      .post(s"$onshoreRoute/reason-for-disclosure")
      .formParam("value[0]", _ => "didNotNotifyHMRC")
      .formParam("value[1]", _ => "didNotFile")
      .formParam("value[2]", _ => "inaccurateReturn")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateWhyNotNotify: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/reason-for-not-notify-tax-liability",
      s"$onshoreRoute/reason-for-not-notify-tax-liability"
    )

  def submitWhyNotNotify: HttpRequestBuilder = {
    submitPageWithFormParams(
      "reason for not notifying",
      s"$onshoreRoute/reason-for-not-notify-tax-liability",
      "value[0]",
      "deliberatelyDidNotNotify"
    )
  }

  val navigateWhyNotFileOnTime: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/reason-for-return-not-filed-on-time",
      s"$onshoreRoute/reason-for-return-not-filed-on-time"
    )

  def submitWhyNotFileOnTime: HttpRequestBuilder =
    submitPageWithFormParams(
      "reason for not filing on time",
      s"$onshoreRoute/reason-for-return-not-filed-on-time",
      "value[2]",
      "deliberatelyWithheldInformation"
    )

  val navigateWhySubmittedInaccurateReturn: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/reason-for-inaccurate-return",
      s"$onshoreRoute/reason-for-inaccurate-return"
    )

  def submitWhySubmittedInaccurateReturn: HttpRequestBuilder =
    submitPageWithFormParams(
      "reason inaccurate return",
      s"$onshoreRoute/reason-for-inaccurate-return",
      "value[2]",
      "deliberatelyInaccurate"
    )

  val navigateToCDF: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/contractual-disclosure-facility",
      s"$onshoreRoute/contractual-disclosure-facility"
    )

  def submitCDFPreferences(value: String): HttpRequestBuilder = {
    submitPage(
      value,
      s"$onshoreRoute/contractual-disclosure-facility"
    )
  }

  val navigateToWhatDoYouNeedToDisclose: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/what-do-you-need-to-disclose",
      s"$onshoreRoute/what-do-you-need-to-disclose"
    )

  def submitDisclosurePreferences: HttpRequestBuilder =
    http(s"Submit Disclosure Preferences")
      .post(s"$onshoreRoute/what-do-you-need-to-disclose")
      .formParam("value[1]", _ => "businessIncomeLiabilities")
      .formParam("value[3]", _ => "company.corporationTaxLiabilities")
      .formParam("value[4]", _ => "company.directorLoanLiabilities")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateToCorporationTaxLiabilities: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/corporation-tax-liabilities/0",
      s"$onshoreRoute/corporation-tax-liabilities/0"
    )

  def submitCorporationTaxLiabilities: HttpRequestBuilder =
    http(s"Submit values for CorporationTaxLiabilities")
      .post(s"$onshoreRoute/corporation-tax-liabilities/0")
      .formParam("periodEnd.day", _ => "2")
      .formParam("periodEnd.month", _ => "2")
      .formParam("periodEnd.year", _ => "2012")
      .formParam("howMuchIncome", _ => "2")
      .formParam("howMuchUnpaid", _ => "2")
      .formParam("howMuchInterest", _ => "2")
      .formParam("penaltyRate", _ => "2")
      .formParam("penaltyRateReason", _ => "This is Test")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateToCorporationTaxSummary: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/corporation-tax-summary",
      s"$onshoreRoute/corporation-tax-summary"
    )

  val navigateToCorporationTaxAccountingPeriods: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/corporation-tax-accounting-periods",
      s"$onshoreRoute/corporation-tax-accounting-periods"
    )

  def submitCTAccountingPeriodsPreferences(value: String): HttpRequestBuilder = {
    submitPage(
      value,
      s"$onshoreRoute/corporation-tax-accounting-periods"
    )
  }

  val navigateToDirectorLoanLiabilities: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/director-loan-liabilities/0",
      s"$onshoreRoute/director-loan-liabilities/0"
    )

  def submitDirectorLoanLiabilities: HttpRequestBuilder =
    http(s"Submit values for DirectorLoanLiabilities")
      .post(s"$onshoreRoute/director-loan-liabilities/0")
      .formParam("name", _ => "DL Name")
      .formParam("periodEnd.day", _ => "3")
      .formParam("periodEnd.month", _ => "3")
      .formParam("periodEnd.year", _ => "2013")
      .formParam("overdrawn", _ => "3")
      .formParam("unpaidTax", _ => "3")
      .formParam("interest", _ => "3")
      .formParam("penaltyRate", _ => "3")
      .formParam("penaltyRateReason", _ => "This is Test")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateToDirectorLoanLiabilitiesSummary: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/director-loan-summary",
      s"$onshoreRoute/director-loan-summary"
    )

  val navigateToDLAccountingPeriods: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/director-loans-accounting-periods",
      s"$onshoreRoute/director-loans-accounting-periods"
    )

  def submitDLAccountingPeriodsPreferences(value: String): HttpRequestBuilder = {
    submitPage(
      value,
      s"$onshoreRoute/director-loans-accounting-periods"
    )
  }

  val navigateToOnshoreYears: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/onshore-years",
      s"$onshoreRoute/onshore-years"
    )

  val submitOnshoreYearsSelection: HttpRequestBuilder = {
    submitPageWithFormParams(
      "Submit",
      s"$onshoreRoute/onshore-years",
      "value[3]",
      "2018"
    )
  }

  val navigateToOnshoreLiabilities: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/onshore-liabilities/0",
      s"$onshoreRoute/onshore-liabilities/0"
    )

  val submitOnshoreLiabilities: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$onshoreRoute/onshore-liabilities/0")
      .formParam("businessIncome", _ => "2")
      .formParam("unpaidTax", _ => "2")
      .formParam("niContributions", _ => "2")
      .formParam("interest", _ => "2")
      .formParam("penaltyRate", _ => "2")
      .formParam("penaltyRateReason", _ => "This is test")
      .formParam("undeclaredIncomeOrGain", _ => "This is test")
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  val navigateToOnshoreLiabilitiesSummary: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/onshore-liabilities-summary",
      s"$onshoreRoute/onshore-liabilities-summary"
    )
}
