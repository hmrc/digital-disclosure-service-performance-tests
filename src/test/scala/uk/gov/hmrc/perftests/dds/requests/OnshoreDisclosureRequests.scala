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
      .formParam("value[5]", "deliberatelyDidNotNotify")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToCDF: HttpRequestBuilder =
    http("Navigate to /contractual-disclosure-facility")
      .get(s"$onshoreRoute/contractual-disclosure-facility")
      .check(status.is(200))

  def submitCDFPreferences(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$onshoreRoute/contractual-disclosure-facility")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToWhatDoYouNeedToDisclose: HttpRequestBuilder =
    http("Navigate to /what-do-you-need-to-disclose")
      .get(s"$onshoreRoute/what-do-you-need-to-disclose")
      .check(status.is(200))

  def submitDisclosurePreferences: HttpRequestBuilder =
    http(s"Submit Disclosure Preferences")
      .post(s"$onshoreRoute/what-do-you-need-to-disclose")
      .formParam("value[1]", "businessIncomeLiabilities")
      .formParam("value[3]", "company.corporationTaxLiabilities")
      .formParam("value[4]", "company.directorLoanLiabilities")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToCorporationTaxLiabilities: HttpRequestBuilder =
    http("Navigate to /corporation-tax-liabilities/0")
      .get(s"$onshoreRoute/corporation-tax-liabilities/0")
      .check(status.is(200))

  def submitCorporationTaxLiabilities: HttpRequestBuilder =
    http(s"Submit values for CorporationTaxLiabilities")
      .post(s"$onshoreRoute/corporation-tax-liabilities/0")
      .formParam("periodEnd.day", "2")
      .formParam("periodEnd.month", "2")
      .formParam("periodEnd.year", "2012")
      .formParam("howMuchIncome", "2")
      .formParam("howMuchUnpaid", "2")
      .formParam("howMuchInterest", "2")
      .formParam("penaltyRate", "2")
      .formParam("penaltyRateReason", "This is Test")
      .formParam("csrfToken", "${csrfToken}")
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
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToDirectorLoanLiabilities: HttpRequestBuilder =
    http("Navigate to /director-loan-liabilities/0")
      .get(s"$onshoreRoute/director-loan-liabilities/0")
      .check(status.is(200))

  def submitDirectorLoanLiabilities: HttpRequestBuilder =
    http(s"Submit values for DirectorLoanLiabilities")
      .post(s"$onshoreRoute/director-loan-liabilities/0")
      .formParam("name", "DL Name")
      .formParam("periodEnd.day", "3")
      .formParam("periodEnd.month", "3")
      .formParam("periodEnd.year", "2013")
      .formParam("overdrawn", "3")
      .formParam("unpaidTax", "3")
      .formParam("interest", "3")
      .formParam("penaltyRate", "3")
      .formParam("penaltyRateReason", "This is Test")
      .formParam("csrfToken", "${csrfToken}")
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
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOnshoreYears: HttpRequestBuilder =
    http("Navigate to /onshore-years")
      .get(s"$onshoreRoute/onshore-years")
      .check(status.is(200))

  val submitOnshoreYearsSelection: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$onshoreRoute/onshore-years")
      .formParam("value[3]", "2018")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOnshoreLiabilities: HttpRequestBuilder =
    http("Navigate to /onshore-liabilities/0")
      .get(s"$onshoreRoute/onshore-liabilities/0")
      .check(status.is(200))

  val submitOnshoreLiabilities: HttpRequestBuilder =
    http(s"Submit")
      .post(s"$onshoreRoute/onshore-liabilities/0")
      .formParam("businessIncome", "2")
      .formParam("unpaidTax", "2")
      .formParam("niContributions", "2")
      .formParam("interest", "2")
      .formParam("penaltyRate", "2")
      .formParam("penaltyRateReason", "This is test")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOnshoreLiabilitiesSummary: HttpRequestBuilder =
    http("Navigate to /onshore-liabilities-summary")
      .get(s"$onshoreRoute/onshore-liabilities-summary")
      .check(status.is(200))
}
