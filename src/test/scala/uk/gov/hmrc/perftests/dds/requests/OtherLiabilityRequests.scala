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

import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.dds.Configuration
import uk.gov.hmrc.perftests.dds.requests.BaseRequests.*

object OtherLiabilityRequests extends Configuration{

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute = s"$digitalDisclosureRoute/notification"
  val onshoreRoute = s"$digitalDisclosureRoute/onshore"
  val offshoreRoute = s"$digitalDisclosureRoute/offshore"
  val otherLiabilityRoute = s"$digitalDisclosureRoute/other-liabilities"

  val navigateToOtherLiabilityIssues: HttpRequestBuilder = {
    navigateToPage(
      "/other-liability-issues",
      s"$otherLiabilityRoute/other-liability-issues"
    )
  }

  def submitOtherLiabilityIssues: HttpRequestBuilder = {
    submitPageWithFormParams(
      "other liability Issues",
      s"$otherLiabilityRoute/other-liability-issues",
      "value[4]",
      "other"
    )
  }

  val navigateToOtherLiabilitiesToFix: HttpRequestBuilder = {
    navigateToPage(
      "/other-liabilities-to-fix",
      s"$otherLiabilityRoute/other-liabilities-to-fix"
    )
  }

  def submitOtherLiabilitiesToFix: HttpRequestBuilder = {
    submitPageWithFormParams(
      "other Liabilities To Fix",
      s"$otherLiabilityRoute/other-liabilities-to-fix",
      "value",
      "Test"
    )
  }

  val navigateToOtherLiabilitiesSummary: HttpRequestBuilder = {
    navigateToPage(
      "/review-other-liability-issues",
      s"$otherLiabilityRoute/review-other-liability-issues"
    )
  }

}
