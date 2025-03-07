/*
 * Copyright 2025 HM Revenue & Customs
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

object ReasonRequests extends Configuration{

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute = s"$digitalDisclosureRoute/notification"
  val onshoreRoute = s"$digitalDisclosureRoute/onshore"
  val offshoreRoute = s"$digitalDisclosureRoute/offshore"
  val otherLiabilityRoute = s"$digitalDisclosureRoute/other-liabilities"
  val reasonRoute = s"$digitalDisclosureRoute/reason"

  val navigateToWhyDisclosureNow: HttpRequestBuilder =
    http("Navigate to /why-disclosure-now")
      .get(s"$reasonRoute/why-disclosure-now")
      .check(status.is(200))

  def submitWhyDisclosureNowPreferences: HttpRequestBuilder =
    http(s"Submit reason for why-disclosure-now")
      .post(s"$reasonRoute/why-disclosure-now")
      .formParam("value[7]", "other")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToReasonForDisclosureNow: HttpRequestBuilder =
    http("Navigate to /reason-for-disclosure-now")
      .get(s"$reasonRoute/reason-for-disclosure-now")
      .check(status.is(200))

  def submitReasonForDisclosureNow: HttpRequestBuilder =
    http(s"Submit reason for reason-for-disclosure-now")
      .post(s"$reasonRoute/reason-for-disclosure-now")
      .formParam("value", "Test")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToWhyDisclosureNotMadeBefore: HttpRequestBuilder =
    http("Navigate to /why-disclosure-not-made-before")
      .get(s"$reasonRoute/why-disclosure-not-made-before")
      .check(status.is(200))

  def submitReasonForWhyDisclosureNotMadeBefore: HttpRequestBuilder =
    http(s"Submit reason for why-disclosure-not-made-before")
      .post(s"$reasonRoute/why-disclosure-not-made-before")
      .formParam("value", "Test")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToSomeoneGaveAdvice: HttpRequestBuilder =
    http("Navigate to /someone-gave-advice")
      .get(s"$reasonRoute/someone-gave-advice")
      .check(status.is(200))


  def submitSomeoneGaveAdvicePreferences: HttpRequestBuilder =
    http(s"Submit Preferences for someone-gave-advice")
      .post(s"$reasonRoute/someone-gave-advice")
      .formParam("value", "false")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToMakingDisclosureNowSummary: HttpRequestBuilder =
    http("Navigate to /making-disclosure-now")
      .get(s"$reasonRoute/making-disclosure-now")
      .check(status.is(200))

  val navigateToYourOffer: HttpRequestBuilder =
    http("Navigate to /your-offer")
      .get(s"$digitalDisclosureRoute/your-offer")
      .check(status.is(200))

  def submitYourOffer: HttpRequestBuilder =
    http(s"Submit Your Offer")
      .post(s"$digitalDisclosureRoute/your-offer")
      .formParam("value", "2")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))


  def navigateToDisclosureSubmitted(reference: String): HttpRequestBuilder =
    http(s"Navigate to /disclosure-submitted/$reference")
      .get(s"$digitalDisclosureRoute/disclosure-submitted/$reference")
      .check(status.is(200))
      .check(referenceIsDisplayed(reference))

}
