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

package uk.gov.hmrc.perftests.dds

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait Configuration extends ServicesConfiguration {

  val authLoginStubUrl: String     = baseUrlFor("auth-login-stub")
  val addressLookupUrl: String     = baseUrlFor("address-lookup")
  val digitalDisclosureUrl: String = baseUrlFor("digital-disclosure")

  private val csrfTokenPattern: String              = """<input type="hidden" name="csrfToken"\s+value="([^"]+)""""
  private val addressLookupJourneyIdPattern: String = """.*/lookup-address/(.*)/.*"""
  private val referencePattern: String              = ".govuk-panel__body"

  def saveCsrfToken: HttpCheck =
    regex(_ => csrfTokenPattern).saveAs("csrfToken")

  def saveAddressLookupJourneyId: HttpCheck =
    headerRegex("location", addressLookupJourneyIdPattern).saveAs("addressLookupJourneyId")

  def referenceIsDisplayed(reference: String): HttpCheck =
    css(s"$referencePattern:contains('$reference')").exists

}
