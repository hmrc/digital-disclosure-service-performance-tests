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

package uk.gov.hmrc.perftests.dds

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.dds.requests.AddressLookupRequests._
import uk.gov.hmrc.perftests.dds.requests.AuthLoginStubRequests._
import uk.gov.hmrc.perftests.dds.requests.DigitalDisclosureRequests._

class Simulation extends PerformanceTestRunner {

  setup(
    "auth-login",
    "Log in via Auth Wizard with a NINO"
  ).withRequests(
    navigateToSignIn,
    signIn("AA000000B"),
    navigateToStart
  )

  setup(
    "hmrc-letter-reference",
    "Provide a HMRC letter reference for a disclosure"
  ).withRequests(
    navigateToLetterFromHmrc,
    submitLetterFromHmrc("true"),
    navigateToHmrcLetterReference,
    submitHmrcLetterReference("CFSS-1234567")
  )

  setup(
    "company-details",
    "Provide details of the company submitting a disclosure"
  ).withRequests(
    navigateToWhatIsThisDisclosureAbout,
    submitWhatIsThisDisclosureAbout("company"),
    navigateToAreYouAnOfficer,
    submitAreYouAnOfficer("true"),
    navigateToDiscloseOffshoreLiabilities,
    submitDiscloseOffshoreLiabilities("false"),
    navigateToDiscloseOnshoreLiabilitiesOnly,
    navigateToCompanyName,
    submitCompanyName("Test Company Name"),
    navigateToCompanyRegistration,
    submitCompanyRegistration("12345678"),
    navigateToCompanyAddressLookup
  )

  setup(
    "personal-details",
    "Provide details of the person submitting a disclosure"
  ).withRequests(
    navigateToYourFullName,
    submitYourFullName("Test Full Name"),
    navigateToYourTelephoneNumber,
    submitYourTelephoneNumber("07123456789"),
    navigateToContactByEmail,
    submitContactByEmail("true"),
    navigateToYourEmailAddress,
    submitYourEmailAddress("dds@example.com"),
    navigateToYourAddressLookup
  )

  setup(
    "address-lookup",
    "Provide an address via address lookup"
  ).withRequests(
    navigateToBegin,
    navigateToCountryPicker,
    submitCountryPicker("United Kingdom", "GB"),
    navigateToLookup,
    navigateToEdit,
    submitEdit("2 Other Place", "Some District", "Anytown", "ZZ1 1ZZ", "GB"),
    navigateToConfirm
  )

  setup(
    "check-answers",
    "Check and submit a disclosure"
  ).withRequests(
    navigateToCheckYourAnswers,
    submitCheckYourAnswers,
    navigateToSubmitted("CFSS-1234567")
  )

  runSimulation()

}
