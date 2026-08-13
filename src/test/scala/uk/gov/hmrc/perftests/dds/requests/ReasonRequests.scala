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
import uk.gov.hmrc.perftests.dds.requests.BaseRequests.*

object ReasonRequests extends Configuration{

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute = s"$digitalDisclosureRoute/notification"
  val onshoreRoute = s"$digitalDisclosureRoute/onshore"
  val offshoreRoute = s"$digitalDisclosureRoute/offshore"
  val otherLiabilityRoute = s"$digitalDisclosureRoute/other-liabilities"
  val reasonRoute = s"$digitalDisclosureRoute/reason"

  val navigateToWhyDisclosureNow: HttpRequestBuilder = {
    navigateToPage(
      "/why-disclosure-now",
      s"$reasonRoute/why-disclosure-now"
    )
  }

  def submitWhyDisclosureNowPreferences: HttpRequestBuilder = {
    submitPageWithFormParams(
      "reason for why-disclosure-now",
      s"$reasonRoute/why-disclosure-now",
      "value[7]",
      "other"
    )
  }

  val navigateToReasonForDisclosureNow: HttpRequestBuilder = {
    navigateToPage(
      "/reason-for-disclosure-now",
      s"$reasonRoute/reason-for-disclosure-now"
    )
  }

  def submitReasonForDisclosureNow: HttpRequestBuilder = {
    submitPageWithFormParams(
      "reason for reason-for-disclosure-now",
      s"$reasonRoute/reason-for-disclosure-now",
      "value",
      "Test"
    )
  }

  val navigateToWhyDisclosureNotMadeBefore: HttpRequestBuilder = {
    navigateToPage(
      "/why-disclosure-not-made-before",
      s"$reasonRoute/why-disclosure-not-made-before",
    )
  }

  def submitReasonForWhyDisclosureNotMadeBefore: HttpRequestBuilder =
    submitPageWithFormParams(
      "reason for why-disclosure-not-made-before",
      s"$reasonRoute/why-disclosure-not-made-before",
      "value",
      "Test"
    )

  val navigateToSomeoneGaveAdvice: HttpRequestBuilder =
    navigateToPage(
      "/someone-gave-advice",
      s"$reasonRoute/someone-gave-advice",
    )


  def submitSomeoneGaveAdvicePreferences: HttpRequestBuilder =
    submitPageWithFormParams(
      "Preferences for someone-gave-advice",
      s"$reasonRoute/someone-gave-advice",
      "value",
      "false"
    )

  val navigateToMakingDisclosureNowSummary: HttpRequestBuilder =
    navigateToPage(
      "/making-disclosure-now",
      s"$reasonRoute/making-disclosure-now",
    )

  val navigateToYourOffer: HttpRequestBuilder =
    navigateToPage(
      "/your-offer",
      s"$digitalDisclosureRoute/your-offer",
    )

  def submitYourOffer: HttpRequestBuilder =
    submitPageWithFormParams(
      "Your offer",
      s"$digitalDisclosureRoute/your-offer",
      "value",
      "2"
    )


  def navigateToDisclosureSubmitted(reference: String): HttpRequestBuilder =
    http(s"Navigate to /disclosure-submitted/$reference")
      .get(s"$digitalDisclosureRoute/disclosure-submitted/$reference")
      .check(status.is(200))
      .check(referenceIsDisplayed(reference))

}
