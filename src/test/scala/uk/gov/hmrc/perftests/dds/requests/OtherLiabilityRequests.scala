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

object OtherLiabilityRequests extends Configuration{

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute = s"$digitalDisclosureRoute/notification"
  val onshoreRoute = s"$digitalDisclosureRoute/onshore"
  val offshoreRoute = s"$digitalDisclosureRoute/offshore"
  val otherLiabilityRoute = s"$digitalDisclosureRoute/other-liabilities"

  val navigateToOtherLiabilityIssues: HttpRequestBuilder =
    http("Navigate to /other-liability-issues")
      .get(s"$otherLiabilityRoute/other-liability-issues")
      .check(status.is(200))

  def submitOtherLiabilityIssues: HttpRequestBuilder =
    http(s"Submit other liability Issues")
      .post(s"$otherLiabilityRoute/other-liability-issues")
      .formParam("value[4]", "other")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOtherLiabilitiesToFix: HttpRequestBuilder =
    http("Navigate to /other-liabilities-to-fix")
      .get(s"$otherLiabilityRoute/other-liabilities-to-fix")
      .check(status.is(200))

  def submitOtherLiabilitiesToFix: HttpRequestBuilder =
    http(s"Submit Other Liabilities To Fix")
      .post(s"$otherLiabilityRoute/other-liabilities-to-fix")
      .formParam("value", "Test")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToOtherLiabilitiesSummary: HttpRequestBuilder =
    http("Navigate to /review-other-liability-issues")
      .get(s"$otherLiabilityRoute/review-other-liability-issues")
      .check(status.is(200))

}
