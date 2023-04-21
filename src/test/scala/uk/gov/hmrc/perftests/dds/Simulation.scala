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

package uk.gov.hmrc.perftests.dds

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.dds.requests.AddressLookupRequests._
import uk.gov.hmrc.perftests.dds.requests.AuthLoginStubRequests._
import uk.gov.hmrc.perftests.dds.requests.DigitalDisclosureRequests._
import uk.gov.hmrc.perftests.dds.requests.OnshoreDisclosureRequests._
import uk.gov.hmrc.perftests.dds.requests.OffshoreDisclosureRequests._
import uk.gov.hmrc.perftests.dds.requests.OtherLiabilityRequests._
import uk.gov.hmrc.perftests.dds.requests.ReasonRequests._

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
    "using-this-service",
    "Begin a notification"
  ).withRequests(
    navigateToUsingThisService,
    submitUsingThisService("notification")
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
    submitDiscloseOffshoreLiabilities("true"),
    submitDiscloseOnshoreLiabilities("true"),
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
    navigateToContactPreferences,
    submitContactPreferences,
    navigateToYourEmailAddress,
    submitYourEmailAddress("dds@example.com"),
    navigateToYourTelephoneNumber,
    submitYourTelephoneNumber("07123456789"),
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
    navigateToSubmitted("CFSS-1234567") ,
  )

  setup(
    "make-a-disclosure",
    "make-a-disclosure"
  ).withRequests(
    navigateToStart,
    navigateToMakeADisclosure,
    submitMakeADisclosure,
    navigateToTaskList
  )

  setup(
    "declaration",
    "Make a Declaration"
  ).withRequests(
    navigateToDeclaration,
    submitDeclarationConfirmation,
    navigateToTaskList
  )

  setup(
    "onshore-disclosure",
    "Submit Onshore disclosure"
  ).withRequests(
    navigateToReasonForDisclosure,
    submitReasonForDisclosurePreferences,
    navigateToCDF ,
    submitCDFPreferences("true") ,
    navigateToWhatDoYouNeedToDisclose,
    submitDisclosurePreferences,
  )

  setup(
    "accounting-Period",
    "Provide CT and DL Accounting Period"
  ).withRequests(
    navigateToCorporationTaxLiabilities,
    submitCorporationTaxLiabilities,
    navigateToCorporationTaxSummary,
    navigateToCorporationTaxAccountingPeriods,
    submitCTAccountingPeriodsPreferences("false"),
    navigateToDirectorLoanLiabilities,
    submitDirectorLoanLiabilities,
    navigateToDirectorLoanLiabilitiesSummary,
    navigateToDLAccountingPeriods,
    submitDLAccountingPeriodsPreferences("false")
  )

  setup(
    "onshore-years",
    "Select Onshore Tax Year"
  ).withRequests(
    navigateToOnshoreYears,
    submitOnshoreYearsSelection,
    navigateToOnshoreLiabilities,
    submitOnshoreLiabilities,
    navigateToOnshoreLiabilitiesSummary,
    navigateToTaskList
  )

  setup(
    "offshore-disclosure",
    "Submit Offshore disclosure"
  ).withRequests(
    navigateToReasonForDisclosureOffshore,
    submitReasonForDisclosurePreferencesOffshore,
    navigateToCDFOffshore,
    submitCDFPreferencesOffshore("true"),
  )

  setup(
    "offshore-years",
    "Select Offshore Tax Year"
  ).withRequests(
    navigateToOffshoreYears,
    submitOffshoreYearsSelection,
    navigateToOffshoreLiabilitiesCountry,
    submitOffshoreLiabilitiesCountryPreferences("BDI"),
    navigateToCountriesOrTerritories,
    submitCountriesOrTerritoriesPreferences("false"),
    navigateToOffshoreLiabilities,
    submitOffshoreLiabilities,
    navigateToLegalInterpretation,
    submitLegalInterpretation,
    navigateToOffshoreLiabilitiesTaxNotIncluded,
    submitOffshoreLiabilitiesTaxNotIncluded,
    navigateToOffshoreMaxValueAssets,
    submitOffshoreMaxValueAssets,
    navigateToOffshoreLiabilitiesSummary,
    navigateToTaskList
  )

  setup(
    "other-liability-issues",
    "Submit other liability"
  ).withRequests(
    navigateToOtherLiabilityIssues,
    submitOtherLiabilityIssues,
    navigateToOtherLiabilitiesToFix,
    submitOtherLiabilitiesToFix,
    navigateToOtherLiabilitiesSummary ,
    navigateToTaskList
  )

  setup(
    "why-disclosure-now",
    "add a reason"
  ).withRequests(
    navigateToWhyDisclosureNow,
    submitWhyDisclosureNowPreferences,
    navigateToReasonForDisclosureNow,
    submitReasonForDisclosureNow,
    navigateToWhyDisclosureNotMadeBefore,
    submitReasonForWhyDisclosureNotMadeBefore,
    navigateToSomeoneGaveAdvice,
    submitSomeoneGaveAdvicePreferences,
    navigateToMakingDisclosureNowSummary,
    navigateToTaskList
  )

  setup(
    "your-offer",
    "Submit Your Offer"
  ).withRequests(
    navigateToYourOffer,
    submitYourOffer,
    navigateToDisclosureSubmitted("CFSS-1234567")
  )


  runSimulation()

}
