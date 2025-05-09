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

object AuthLoginStubRequests extends Configuration {

  val signInRoute: String    = s"$authLoginStubUrl/auth-login-stub/gg-sign-in"
  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"

  val navigateToSignIn: HttpRequestBuilder =
    http("Navigate to /auth-login-stub/gg-sign-in")
      .get(signInRoute)
      .check(status.is(200))

  def signIn(nino: String): HttpRequestBuilder =
    http("Log in")
      .post(signInRoute)
      .formParam("redirectionUrl", digitalDisclosureRoute)
      .formParam("credentialStrength", "strong")
      .formParam("authorityId", "")
      .formParam("confidenceLevel", "250")
      .formParam("affinityGroup", "Organisation")
      .formParam("email", "user@test.com")
      .formParam("credentialRole", "User")
      .formParam("nino", nino)
      .check(status.is(303))
      .check(header("Location").is(digitalDisclosureRoute))

}
