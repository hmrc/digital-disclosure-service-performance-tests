/*
 * Copyright 2022 HM Revenue & Customs
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

object DigitalDisclosureRequests extends Configuration {

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/digital-disclosure"
  val notificationRoute      = s"$digitalDisclosureRoute/notification"

  val navigateToStart: HttpRequestBuilder =
    http("Navigate to /digital-disclosure")
      .get(s"$digitalDisclosureRoute")
      .check(status.is(200))

  val navigateToLetterFromHmrc: HttpRequestBuilder =
    http("Navigate to /letter-from-hmrc")
      .get(s"$notificationRoute/letter-from-hmrc")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitLetterFromHmrc(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/letter-from-hmrc")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToHmrcLetterReference: HttpRequestBuilder =
    http("Navigate to /hmrc-letter-reference")
      .get(s"$notificationRoute/hmrc-letter-reference")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHmrcLetterReference(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/hmrc-letter-reference")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToWhatIsThisDisclosureAbout: HttpRequestBuilder =
    http("Navigate to /what-is-this-disclosure-about")
      .get(s"$notificationRoute/what-is-this-disclosure-about")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhatIsThisDisclosureAbout(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/what-is-this-disclosure-about")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToAreYouAnOfficer: HttpRequestBuilder =
    http("Navigate to /are-you-an-officer")
      .get(s"$notificationRoute/are-you-an-officer")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAreYouAnOfficer(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/are-you-an-officer")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToDiscloseOffshoreLiabilities: HttpRequestBuilder =
    http("Navigate to /disclose-offshore-liabilities")
      .get(s"$notificationRoute/disclose-offshore-liabilities")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDiscloseOffshoreLiabilities(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/disclose-offshore-liabilities")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToDiscloseOnshoreLiabilitiesOnly: HttpRequestBuilder =
    http("Navigate to /disclose-onshore-liabilities-only")
      .get(s"$notificationRoute/disclose-onshore-liabilities-only")
      .check(status.is(200))

  val navigateToCompanyName: HttpRequestBuilder =
    http("Navigate to /company-name")
      .get(s"$notificationRoute/company-name")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitCompanyName(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/company-name")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToCompanyRegistration: HttpRequestBuilder =
    http("Navigate to /company-registration")
      .get(s"$notificationRoute/company-registration")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitCompanyRegistration(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/company-registration")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToCompanyAddressLookup: HttpRequestBuilder =
    http("Navigate to /company-address/lookup")
      .get(s"$notificationRoute/company-address/lookup")
      .check(status.is(303))
      .check(saveAddressLookupJourneyId)

  val navigateToYourFullName: HttpRequestBuilder =
    http("Navigate to /your-full-name")
      .get(s"$notificationRoute/your-full-name")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitYourFullName(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/your-full-name")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToYourTelephoneNumber: HttpRequestBuilder =
    http("Navigate to /your-telephone-number")
      .get(s"$notificationRoute/your-telephone-number")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitYourTelephoneNumber(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/your-telephone-number")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToContactByEmail: HttpRequestBuilder =
    http("Navigate to /contact-by-email")
      .get(s"$notificationRoute/contact-by-email")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContactByEmail(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/contact-by-email")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToYourEmailAddress: HttpRequestBuilder =
    http("Navigate to /your-email-address")
      .get(s"$notificationRoute/your-email-address")
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitYourEmailAddress(value: String): HttpRequestBuilder =
    http(s"Submit '$value'")
      .post(s"$notificationRoute/your-email-address")
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToYourAddressLookup: HttpRequestBuilder =
    http("Navigate to /your-address/lookup")
      .get(s"$notificationRoute/your-address/lookup")
      .check(status.is(303))
      .check(saveAddressLookupJourneyId)

  val navigateToCheckYourAnswers: HttpRequestBuilder =
    http("Navigate to /check-your-answers")
      .get(s"$notificationRoute/check-your-answers")
      .check(status.is(200))
      .check(saveCsrfToken)

  val submitCheckYourAnswers: HttpRequestBuilder =
    http(s"Submit answers")
      .post(s"$notificationRoute/check-your-answers")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  def navigateToSubmitted(reference: String): HttpRequestBuilder =
    http(s"Navigate to /submitted/$reference")
      .get(s"$notificationRoute/submitted/$reference")
      .check(status.is(200))
      .check(referenceIsDisplayed(reference))

}
