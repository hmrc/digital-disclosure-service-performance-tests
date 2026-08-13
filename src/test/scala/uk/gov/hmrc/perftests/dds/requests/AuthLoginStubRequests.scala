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

object AuthLoginStubRequests extends Configuration {

  val signInRoute: String    = s"$authLoginStubUrl/auth-login-stub/gg-sign-in"
  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"

  val navigateToSignIn: HttpRequestBuilder = {
    navigateToPage(
      "/auth-login-stub/gg-sign-in",
      signInRoute
    )
  }

  def signIn(nino: String): HttpRequestBuilder =
    http("Log in")
      .post(signInRoute)
      .formParam("redirectionUrl", _ => digitalDisclosureRoute)
      .formParam("credentialStrength", _ => "strong")
      .formParam("authorityId", _ => "")
      .formParam("confidenceLevel", _ => "250")
      .formParam("affinityGroup", _ => "Organisation")
      .formParam("email", _ => "user@test.com")
      .formParam("credentialRole", _ => "User")
      .formParam("nino", _ => nino)
      .formParam("csrfToken", _("csrfToken").as[String])
      .check(status.is(303))
      .check(header(_ => "Location").is(digitalDisclosureRoute))

}
