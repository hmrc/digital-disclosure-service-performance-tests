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

object DigitalDisclosureRequests extends Configuration {

  val digitalDisclosureRoute = s"$digitalDisclosureUrl/tell-hmrc-about-underpaid-tax-from-previous-years"
  val notificationRoute      = s"$digitalDisclosureRoute/notification"
  val onshoreRoute      = s"$digitalDisclosureRoute/onshore"

  val navigateToStart: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/tell-hmrc-about-underpaid-tax-from-previous-years",
      s"$digitalDisclosureRoute"
    )

  val navigateToUsingThisService: HttpRequestBuilder =
    navigateToPage(
      "/using-this-service",
      s"$digitalDisclosureRoute/using-this-service"
    )

  def submitUsingThisService(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$digitalDisclosureRoute/using-this-service"
    )

  val navigateToLetterFromHmrc: HttpRequestBuilder =
    navigateToPage(
      "/letter-from-hmrc",
      s"$notificationRoute/letter-from-hmrc"
    )

  def submitLetterFromHmrc(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/letter-from-hmrc"
    )

  val navigateToHmrcLetterReference: HttpRequestBuilder =
    navigateToPage(
      "/hmrc-letter-reference",
      s"$notificationRoute/hmrc-letter-reference"
    )

  def submitHmrcLetterReference(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/hmrc-letter-reference"
    )

  val navigateToWhatIsThisDisclosureAbout: HttpRequestBuilder =
    navigateToPage(
      "/what-is-this-disclosure-aboute",
      s"$notificationRoute/what-is-this-disclosure-about"
    )

  def submitWhatIsThisDisclosureAbout(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/what-is-this-disclosure-about"
    )

  val navigateToAreYouAnOfficer: HttpRequestBuilder =
    navigateToPage(
      "/are-you-the-entity",
      s"$notificationRoute/are-you-the-entity"
    )

  def submitAreYouAnOfficer(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/are-you-the-entity"
    )

  val navigateToDiscloseOffshoreLiabilities: HttpRequestBuilder =
    navigateToPage(
      "/disclose-offshore-liabilities",
      s"$notificationRoute/disclose-offshore-liabilities"
    )

  def submitDiscloseOffshoreLiabilities(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/disclose-offshore-liabilities"
    )

  def submitDiscloseOnshoreLiabilities(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/disclose-onshore-liabilities"
    )

  val navigateToDiscloseOnshoreLiabilitiesOnly: HttpRequestBuilder =
    navigateToPage(
      "/disclose-onshore-liabilities-only",
      s"$notificationRoute/disclose-onshore-liabilities-only"
    )

  val navigateToCompanyName: HttpRequestBuilder =
    navigateToPage(
      "/company-name",
      s"$notificationRoute/company-name"
    )

  def submitCompanyName(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/company-name"
    )

  val navigateToCompanyRegistration: HttpRequestBuilder =
    navigateToPage(
      "/company-registration",
      s"$notificationRoute/company-registration"
    )

  def submitCompanyRegistration(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/company-registration"
    )

  val navigateToCompanyAddressLookup: HttpRequestBuilder = {
    navigateToAddressLookup(
      "/company-address/lookup",
      s"$notificationRoute/company-address/lookup"
    )
  }

  val navigateToYourFullName: HttpRequestBuilder =
    navigateToPage(
      "your-full-name",
      s"$notificationRoute/your-full-name"
    )

  def submitYourFullName(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/your-full-name"
    )

  val navigateToYourTelephoneNumber: HttpRequestBuilder =
    navigateToPage(
      "/your-telephone-number",
      s"$notificationRoute/your-telephone-number"
    )

  def submitYourTelephoneNumber(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/your-telephone-number"
    )

  val navigateToContactPreferences: HttpRequestBuilder =
    navigateToPage(
      "/contact-preference",
      s"$notificationRoute/contact-preference"
    )

  def submitContactPreferences: HttpRequestBuilder =
    http(s"Submit contact preferences")
      .post(s"$notificationRoute/contact-preference")
      .formParam("value[0]", _ => "email")
      .formParam("value[1]", _ => "telephone")
      .formParam("csrfToken", _("csrfToken").as[String])
     
      .check(status.is(303))

  val navigateToYourEmailAddress: HttpRequestBuilder =
    navigateToPage(
      "/your-email-address",
      s"$notificationRoute/your-email-address"
    )

  def submitYourEmailAddress(value: String): HttpRequestBuilder =
    submitPage(
      value,
      s"$notificationRoute/your-email-address"
    )

  val navigateToYourAddressLookup: HttpRequestBuilder =
    navigateToAddressLookup(
      "/your-address/lookup",
      s"$notificationRoute/your-address/lookup"
    )

  val navigateToCheckYourAnswers: HttpRequestBuilder =
    navigateToPage(
      "/check-your-answers",
      s"$notificationRoute/check-your-answers"
    )

  val submitCheckYourAnswers: HttpRequestBuilder = {
    submitPageWithoutValue(
      "/check-your-answers",
      s"$notificationRoute/check-your-answers"
    )
  }

  val navigateToSubmitted: HttpRequestBuilder = {
    navigateToPageWithoutCsrf(
      "/submitted",
      s"$notificationRoute/submitted"
    )
  }

  val navigateToMakeADisclosure: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/make-a-disclosure",
      s"$digitalDisclosureRoute/make-a-disclosure"
    )

  val submitMakeADisclosure: HttpRequestBuilder =
    submitPageWithoutValue(
      "/make-a-disclosure",
      s"$digitalDisclosureRoute/make-a-disclosure"
    )

  val navigateToTaskList: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/disclosure-list-of-tasks",
      s"$digitalDisclosureRoute/disclosure-list-of-tasks"
    )

  val navigateToDeclaration: HttpRequestBuilder =
    navigateToPageWithoutCsrf(
      "/declaration",
      s"$digitalDisclosureRoute/declaration"
    )


  val submitDeclarationConfirmation: HttpRequestBuilder = {
    submitPageWithoutValue(
      "/confirm",
        s"$digitalDisclosureRoute/declaration/confirm"
    )
  }


}
