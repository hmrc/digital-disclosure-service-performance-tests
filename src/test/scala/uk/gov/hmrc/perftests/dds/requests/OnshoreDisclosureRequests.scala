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

object OnshoreDisclosureRequests extends Configuration {

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute = s"$digitalDisclosureRoute/notification"
  val onshoreRoute = s"$digitalDisclosureRoute/onshore"

  val navigateToReasonForDisclosure: HttpRequestBuilder =
    http("Navigate to /reason-for-disclosure")
      .get(s"$onshoreRoute/reason-for-disclosure")
      .check(status.is(200))

  def submitReasonForDisclosurePreferences: HttpRequestBuilder =
    http(s"Submit reason for disclosure preferences")
      .post(s"$onshoreRoute/reason-for-disclosure")
      .formParam("value[0]", _ => "didNotNotifyHMRC")
      .formParam("value[1]", _ => "didNotFile")
      .formParam("value[2]", _ => "inaccurateReturn")
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateWhyNotNotify: HttpRequestBuilder =
    http("Navigate to /reason-for-not-notify-tax-liability")
      .get(s"$onshoreRoute/reason-for-not-notify-tax-liability")
      .check(status.is(200))

  def submitWhyNotNotify: HttpRequestBuilder =
    http(s"Submit reason for not notifying")
      .post(s"$onshoreRoute/reason-for-not-notify-tax-liability")
      .formParam("value[0]", _ => "deliberatelyDidNotNotify")
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateWhyNotFileOnTime: HttpRequestBuilder =
    http("Navigate to /reason-for-return-not-filed-on-time")
      .get(s"$onshoreRoute/reason-for-return-not-filed-on-time")
      .check(status.is(200))

  def submitWhyNotFileOnTime: HttpRequestBuilder =
    http(s"Submit reason for not filing on time")
      .post(s"$onshoreRoute/reason-for-return-not-filed-on-time")
      .formParam("value[2]", _ => "deliberatelyWithheldInformation")
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateWhySubmittedInaccurateReturn: HttpRequestBuilder =
    http("Navigate to /reason-for-inaccurate-return")
      .get(s"$onshoreRoute/reason-for-inaccurate-return")
      .check(status.is(200))

  def submitWhySubmittedInaccurateReturn: HttpRequestBuilder =
    http(s"Submit reason inaccurate return")
      .post(s"$onshoreRoute/reason-for-inaccurate-return")
      .formParam("value[2]", _ => "deliberatelyInaccurate")
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToCDF: HttpRequestBuilder =
    http("Navigate to /contractual-disclosure-facility")
      .get(s"$onshoreRoute/contractual-disclosure-facility")
      .check(status.is(200))

  def submitCDFPreferences(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$onshoreRoute/contractual-disclosure-facility")
      .formParam("value", _ => value)
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToWhatDoYouNeedToDisclose: HttpRequestBuilder =
    http("Navigate to /what-do-you-need-to-disclose")
      .get(s"$onshoreRoute/what-do-you-need-to-disclose")
      .check(status.is(200))

  def submitDisclosurePreferences: HttpRequestBuilder =
    http(s"Submit Disclosure Preferences")
      .post(s"$onshoreRoute/what-do-you-need-to-disclose")
      .formParam("value[1]", _ => "businessIncomeLiabilities")
      .formParam("value[3]", _ => "company.corporationTaxLiabilities")
      .formParam("value[4]", _ => "company.directorLoanLiabilities")
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToCorporationTaxLiabilities: HttpRequestBuilder =
    http("Navigate to /corporation-tax-liabilities/0")
      .get(s"$onshoreRoute/corporation-tax-liabilities/0")
      .check(status.is(200))

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
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToCorporationTaxSummary: HttpRequestBuilder =
    http("Navigate to /corporation-tax-summary")
      .get(s"$onshoreRoute/corporation-tax-summary")
      .check(status.is(200))

  val navigateToCorporationTaxAccountingPeriods: HttpRequestBuilder =
    http("Navigate to /corporation-tax-accounting-periods")
      .get(s"$onshoreRoute/corporation-tax-accounting-periods")
      .check(status.is(200))

  def submitCTAccountingPeriodsPreferences(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$onshoreRoute/corporation-tax-accounting-periods")
      .formParam("value", _ => value)
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToDirectorLoanLiabilities: HttpRequestBuilder =
    http("Navigate to /director-loan-liabilities/0")
      .get(s"$onshoreRoute/director-loan-liabilities/0")
      .check(status.is(200))

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
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToDirectorLoanLiabilitiesSummary: HttpRequestBuilder =
    http("Navigate to /director-loan-summary")
      .get(s"$onshoreRoute/director-loan-summary")
      .check(status.is(200))

  val navigateToDLAccountingPeriods: HttpRequestBuilder =
    http("Navigate to /director-loans-accounting-periods")
      .get(s"$onshoreRoute/director-loans-accounting-periods")
      .check(status.is(200))

  def submitDLAccountingPeriodsPreferences(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$onshoreRoute/director-loans-accounting-periods")
      .formParam("value", _ => value)
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToOnshoreYears: HttpRequestBuilder =
    http("Navigate to /onshore-years")
      .get(s"$onshoreRoute/onshore-years")
      .check(status.is(200))

  val submitOnshoreYearsSelection: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$onshoreRoute/onshore-years")
      .formParam("value[3]", _ => "2018")
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToOnshoreLiabilities: HttpRequestBuilder =
    http("Navigate to /onshore-liabilities/0")
      .get(s"$onshoreRoute/onshore-liabilities/0")
      .check(status.is(200))

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
      .formParam("csrfToken", _ => "#{csrfToken}")
      .check(status.is(303))

  val navigateToOnshoreLiabilitiesSummary: HttpRequestBuilder =
    http("Navigate to /onshore-liabilities-summary")
      .get(s"$onshoreRoute/onshore-liabilities-summary")
      .check(status.is(200))
}
