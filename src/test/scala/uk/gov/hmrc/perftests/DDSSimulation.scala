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

package uk.gov.hmrc.perftests

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import AddressLookupRequests._
import AuthLoginRequests._
import CommonRequests._
import CompanyRequests._
import EstateRequests._
import IndividualRequests._
import LimitedLiabilityPartnershipRequests._
import TrustRequests._
import YourInfoRequests._

class DDSSimulation extends PerformanceTestRunner {


  setup(
    "DigitalDisclosureIndividualJourney",
    "A full individual journey through the Digital Disclosure service"
  ) withRequests(
    getLoginPage,
    postLoginPage,
    getHomePage,
    getLetterFromHMRCPage,
    postLetterFromHMRCPage,
    getHmrcLetterReferencePage,
    postHmrcLetterReferencePage,
    getWhatIsDisclosureAboutPage,
    postWhatIsDisclosureAboutPage_Individual,
    getAreYouTheIndividualPage,
    postAreYouTheIndividualPage,
    getAreYouRepresentingAnOrgPage,
    postAreYouRepresentingAnOrgPage,
    getOrgNamePage,
    postOrgNamePage,
    getOffshoreLiabilitiesPage,
    postOffshoreLiabilitiesPage,
    getOnshoreLiabilitiesPage,
    postOnshoreLiabilitiesPage_Individual,
    getIndividualFullNamePage,
    postIndividualFullNamePage,
    getIndividualDateOfBirthPage,
    postIndividualDateOfBirthPage,
    getIndividualOccupationPage,
    postIndividualOccupationPage,
    getIndividualHaveNinoPage,
    postIndividualHaveNinoPage,
    getIndividualNinoPage,
    postIndividualNinoPage,
    getIsIndividualRegForVATPage,
    postIsIndividualRegForVATPage,
    getIndividualVATRegNumberPage,
    postIndividualVATRegNumberPage,
    getIsIndividualRegForSAPage,
    postIsIndividualRegForSAPage,
    getIndividualUTRPage,
    postIndividualUTRPage,
    getIndividualAddressLookupPage,
    getALFESubjectBeginPage,
    getALFESubjectCountryPickerPage,
    postALFESubjectCountryPickerPage,
    getALFESubjectAddressEditPage,
    postALFESubjectAddressEditPage,
    getALFESubjectConfirmPage,
    postALFEIndividualConfirmPage,
    getYourFullNamePage,
    postYourFullNamePage,
    getYourTelNumberPage,
    postYourTelNumberPage,
    getDoYouHaveEmailPage,
    postDoYouHaveEmailPage,
    getYourEmailPage,
    postYourEmailPage,
    getYourAddressLookupPage,
    getALFEYourBeginPage,
    getALFEYourCountryPickerPage,
    postALFEYourCountryPickerPage,
    getALFEYourAddressEditPage,
    postALFEYourAddressEditPage,
    getALFEYourAddressConfirmPage,
    postALFEYourAddressConfirmPage,
    getCYAPage,
    postCYAPage,
    getSubmissionConfirmationPage
  )

  setup(
    "DigitalDisclosureEstateJourney",
    "A full estate journey through the Digital Disclosure service"
  ) withRequests(
    getLoginPage,
    postLoginPage,
    getHomePage,
    getLetterFromHMRCPage,
    postLetterFromHMRCPage,
    getHmrcLetterReferencePage,
    postHmrcLetterReferencePage,
    getWhatIsDisclosureAboutPage,
    postWhatIsDisclosureAboutPage_Estate,
    getAreYouTheEstateExecutorPage,
    postAreYouTheEstateExecutorPage,
    getAreYouRepresentingAnOrgPage,
    postAreYouRepresentingAnOrgPage,
    getOrgNamePage,
    postOrgNamePage,
    getOffshoreLiabilitiesPage,
    postOffshoreLiabilitiesPage,
    getOnshoreLiabilitiesPage,
    postOnshoreLiabilitiesPage_Estate,
    getEstateIndividualFullNamePage,
    postEstateIndividualFullNamePage,
    getEstateIndividualDateOfBirthPage,
    postEstateIndividualDateOfBirthPage,
    getEstateIndividualOccupationPage,
    postEstateIndividualOccupationPage,
    getEstateIndividualHadNinoPage,
    postEstateIndividualHadNinoPage,
    getEstateIndividualNinoPage,
    postEstateIndividualNinoPage,
    getEstateIndividualWasRegForVATPage,
    postEstateIndividualWasRegForVATPage,
    getEstateIndividualVATRegNumberPage,
    postEstateIndividualVATRegNumberPage,
    getEstateIndividualWasRegForSAPage,
    postEstateIndividualWasRegForSAPage,
    getEstateIndividualUTRPage,
    postEstateIndividualUTRPage,
    getEstateAddressLookupPage,
    getALFESubjectBeginPage,
    getALFESubjectCountryPickerPage,
    postALFESubjectCountryPickerPage,
    getALFESubjectAddressEditPage,
    postALFESubjectAddressEditPage,
    getALFESubjectConfirmPage,
    postALFEEstateConfirmPage,
    getYourFullNamePage,
    postYourFullNamePage,
    getYourTelNumberPage,
    postYourTelNumberPage,
    getDoYouHaveEmailPage,
    postDoYouHaveEmailPage,
    getYourEmailPage,
    postYourEmailPage,
    getYourAddressLookupPage,
    getALFEYourBeginPage,
    getALFEYourCountryPickerPage,
    postALFEYourCountryPickerPage,
    getALFEYourAddressEditPage,
    postALFEYourAddressEditPage,
    getALFEYourAddressConfirmPage,
    postALFEYourAddressConfirmPage,
    getCYAPage,
    postCYAPage,
    getSubmissionConfirmationPage
    )

  setup(
    "DigitalDisclosureCompanyJourney",
    "A full company journey through the Digital Disclosure service"
  ) withRequests(
    getLoginPage,
    postLoginPage,
    getHomePage,
    getLetterFromHMRCPage,
    postLetterFromHMRCPage,
    getHmrcLetterReferencePage,
    postHmrcLetterReferencePage,
    getWhatIsDisclosureAboutPage,
    postWhatIsDisclosureAboutPage_Company,
    getAreYouAnOfficerPage,
    postAreYouAnOfficerPage,
    getAreYouRepresentingAnOrgPage,
    postAreYouRepresentingAnOrgPage,
    getOrgNamePage,
    postOrgNamePage,
    getOffshoreLiabilitiesPage,
    postOffshoreLiabilitiesPage,
    getOnshoreLiabilitiesPage,
    postOnshoreLiabilitiesPage_Company,
    getCompanyNamePage,
    postCompanyNamePage,
    getCompanyRegistrationNumberPage,
    postCompanyRegistrationNumberPage,
    getCompanyAddressLookupPage,
    getALFESubjectBeginPage,
    getALFESubjectCountryPickerPage,
    postALFESubjectCountryPickerPage,
    getALFESubjectAddressEditPage,
    postALFESubjectAddressEditPage,
    getALFESubjectConfirmPage,
    postALFECompanyConfirmPage,
    getYourFullNamePage,
    postYourFullNamePage,
    getYourTelNumberPage,
    postYourTelNumberPage,
    getDoYouHaveEmailPage,
    postDoYouHaveEmailPage,
    getYourEmailPage,
    postYourEmailPage,
    getYourAddressLookupPage,
    getALFEYourBeginPage,
    getALFEYourCountryPickerPage,
    postALFEYourCountryPickerPage,
    getALFEYourAddressEditPage,
    postALFEYourAddressEditPage,
    getALFEYourAddressConfirmPage,
    postALFEYourAddressConfirmPage,
    getCYAPage,
    postCYAPage,
    getSubmissionConfirmationPage
  )

  setup(
    "DigitalDisclosureLimitedLiabilityPartnershipJourney",
    "A full limited liability partnership journey through the Digital Disclosure service"
  ) withRequests(
    getLoginPage,
    postLoginPage,
    getHomePage,
    getLetterFromHMRCPage,
    postLetterFromHMRCPage,
    getHmrcLetterReferencePage,
    postHmrcLetterReferencePage,
    getWhatIsDisclosureAboutPage,
    postWhatIsDisclosureAboutPage_LLP,
    getAreYouDesignatedMemberPage,
    postAreYouDesignatedMemberPage,
    getAreYouRepresentingAnOrgPage,
    postAreYouRepresentingAnOrgPage,
    getOrgNamePage,
    postOrgNamePage,
    getOffshoreLiabilitiesPage,
    postOffshoreLiabilitiesPage,
    getOnshoreLiabilitiesPage,
    postOnshoreLiabilitiesPage_LLP,
    getLLPNamePage,
    postLLPNamePage,
    getLLPAddressLookupPage,
    getALFESubjectBeginPage,
    getALFESubjectCountryPickerPage,
    postALFESubjectCountryPickerPage,
    getALFESubjectAddressEditPage,
    postALFESubjectAddressEditPage,
    getALFESubjectConfirmPage,
    postALFELLPConfirmPage,
    getYourFullNamePage,
    postYourFullNamePage,
    getYourTelNumberPage,
    postYourTelNumberPage,
    getDoYouHaveEmailPage,
    postDoYouHaveEmailPage,
    getYourEmailPage,
    postYourEmailPage,
    getYourAddressLookupPage,
    getALFEYourBeginPage,
    getALFEYourCountryPickerPage,
    postALFEYourCountryPickerPage,
    getALFEYourAddressEditPage,
    postALFEYourAddressEditPage,
    getALFEYourAddressConfirmPage,
    postALFEYourAddressConfirmPage,
    getCYAPage,
    postCYAPage,
    getSubmissionConfirmationPage
  )

  setup(
    "DigitalDisclosureTrustJourney",
    "A full trust journey through the Digital Disclosure service"
  ) withRequests(
    getLoginPage,
    postLoginPage,
    getHomePage,
    getLetterFromHMRCPage,
    postLetterFromHMRCPage,
    getHmrcLetterReferencePage,
    postHmrcLetterReferencePage,
    getWhatIsDisclosureAboutPage,
    postWhatIsDisclosureAboutPage_Trust,
    getAreYouTrusteePage,
    postAreYouTrusteePage,
    getAreYouRepresentingAnOrgPage,
    postAreYouRepresentingAnOrgPage,
    getOrgNamePage,
    postOrgNamePage,
    getOffshoreLiabilitiesPage,
    postOffshoreLiabilitiesPage,
    getOnshoreLiabilitiesPage,
    postOnshoreLiabilitiesPage_Trust,
    getTrustNamePage,
    postTrustNamePage,
    getTrustAddressLookupPage,
    getALFESubjectBeginPage,
    getALFESubjectCountryPickerPage,
    postALFESubjectCountryPickerPage,
    getALFESubjectAddressEditPage,
    postALFESubjectAddressEditPage,
    getALFESubjectConfirmPage,
    postALFETrustConfirmPage,
    getYourFullNamePage,
    postYourFullNamePage,
    getYourTelNumberPage,
    postYourTelNumberPage,
    getDoYouHaveEmailPage,
    postDoYouHaveEmailPage,
    getYourEmailPage,
    postYourEmailPage,
    getYourAddressLookupPage,
    getALFEYourBeginPage,
    getALFEYourCountryPickerPage,
    postALFEYourCountryPickerPage,
    getALFEYourAddressEditPage,
    postALFEYourAddressEditPage,
    getALFEYourAddressConfirmPage,
    postALFEYourAddressConfirmPage,
    getCYAPage,
    postCYAPage,
    getSubmissionConfirmationPage
  )

  runSimulation()
}
