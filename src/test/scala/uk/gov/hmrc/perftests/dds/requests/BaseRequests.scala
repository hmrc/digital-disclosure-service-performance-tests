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

import io.gatling.core.Predef.*
import io.gatling.http.Predef.*
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.dds.Configuration

object BaseRequests extends Configuration {

  def navigateToPage(
    pageName: String,
    url: String
  ): HttpRequestBuilder =
    http(s"Navigate to $pageName")
      .get(url)
      .check(status.is(200))
      .check(saveCsrfToken)

  def navigateToAddressLookup(
    pageName: String,
    url: String
  ): HttpRequestBuilder =
    http(s"Navigate to $pageName")
      .get(url)
      .check(status.is(303))
      .check(saveAddressLookupJourneyId)

  def navigateToPageWithoutCsrf(
    pageName: String,
    url: String
  ): HttpRequestBuilder =
    http(s"Navigate to $pageName")
      .get(url)
      .check(status.is(200))

  def submitPage(
    pageName: String,
    url: String
  ): HttpRequestBuilder =
    http(s"Submit '$pageName'")
      .post(url)
      .formParam("value", _ => pageName)
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  def submitPageWithoutValue(
    pageName: String,
    url: String
  ): HttpRequestBuilder =
    http(s"Submit '$pageName''")
      .post(url)
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))

  def submitPageWithFormParams(
    pageName: String,
    url: String,
    formParamName: String,
    value: String
  ): HttpRequestBuilder =
    http(s"Submit '$pageName'")
      .post(url)
      .formParam(formParamName, _ => value)
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))
}
