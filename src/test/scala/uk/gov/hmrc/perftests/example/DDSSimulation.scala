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

package uk.gov.hmrc.perftests.example

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.example.AuthLoginRequests._
import uk.gov.hmrc.perftests.example.CompanyRequests._
import uk.gov.hmrc.perftests.example.EstateRequests._
import uk.gov.hmrc.perftests.example.IndividualRequests._

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
    postWhatIsDisclosureAboutPage,
    getAreYouTheIndividualPage,
    postAreYouTheIndividualPage,
    getAreYouRepresentingAnOrgPage,
    postAreYouRepresentingAnOrgPage,
    getOrgNamePage,
    postOrgNamePage,
    getOffshoreLiabilitiesPage,
    postOffshoreLiabilitiesPage,
    getOnshoreLiabilitiesPage,
    postOnshoreLiabilitiesPage,
    getIndividualFullNamePage,
    postIndividualFullNamePage,
    getDateOfBirthPage,
    postDateOfBirthPage,
    getOccupationPage,
    postOccupationPage,
    getHaveNinoPage,
    postHaveNinoPage,
    getNinoPage,
    postNinoPage,
    getIsRegForVATPage,
    postIsRegForVATPage,
    getVATRegNumberPage,
    postVATRegNumberPage,
    getIsRegForSAPage,
    postIsRegForSAPage,
    getUTRPage,
    postUTRPage,
    getAddressLookupPage,
    getALFEBeginPage,
    getALFECountryPickerPage,
    postALFECountryPickerPage,
    getALFEAddressEditPage,
    postALFEAddressEditPage,
    postALFEConfirmPage,
    getYourFullNamePage,
    postYourFullNamePage
//      getYourTelNumberPage,
//      postYourTelNumberPage
  )

  setup(
    "DigitalDisclosureEstateJourney",
    "A full estate journey through the Digital Disclosure service"
  ) withRequests(
    getLoginPage,
    postLoginPage,
    getEstateHomePage,
    getEstateLetterFromHMRCPage,
    postEstateLetterFromHMRCPage,
    getEstateHmrcLetterReferencePage,
    postEstateHmrcLetterReferencePage,
    getEstateWhatIsDisclosureAboutPage,
    postEstateWhatIsDisclosureAboutPage,
    getEstateAreYouTheExecutorPage,
    postEstateAreYouTheExecutorPage,
    getEstateAreYouRepresentingAnOrgPage,
    postEstateAreYouRepresentingAnOrgPage,
    getEstateOrgNamePage,
    postEstateOrgNamePage,
    getEstateOffshoreLiabilitiesPage,
    postEstateOffshoreLiabilitiesPage,
    getEstateOnshoreLiabilitiesPage,
    postEstateOnshoreLiabilitiesPage,
    getEstateIndividualFullNamePage,
    postEstateIndividualFullNamePage,
    getEstateDateOfBirthPage,
    postEstateDateOfBirthPage,
    getEstateOccupationPage,
    postEstateOccupationPage,
    getEstateHaveNinoPage,
    postEstateHaveNinoPage,
    getEstateNinoPage,
    postEstateNinoPage,
    getEstateIsRegForVATPage,
    postEstateIsRegForVATPage,
    getEstateVATRegNumberPage,
    postEstateVATRegNumberPage,
    getEstateIsRegForSAPage,
    postEstateIsRegForSAPage,
    getEstateUTRPage,
    postEstateUTRPage,
    getEstateAddressLookupPage,
    getEstateALFEBeginPage,
    getEstateALFECountryPickerPage,
    postEstateALFECountryPickerPage,
    getEstateALFEAddressEditPage,
    postEstateALFEAddressEditPage,
    getEstateALFEConfirmPage,
    postEstateALFEConfirmPage,
    getEstateYourFullNamePage,
    postEstateYourFullNamePage
//    getEstateYourTelNumberPage,
//    postEstateYourTelNumberPage
    )

  setup(
    "DigitalDisclosureCompanyJourney",
    "A full company journey through the Digital Disclosure service"
  ) withRequests(
    getLoginPage,
    postLoginPage,
    getCompanyHomePage,
    getCompanyLetterFromHMRCPage,
    postCompanyLetterFromHMRCPage,
    getCompanyHmrcLetterReferencePage,
    postCompanyHmrcLetterReferencePage,
    getCompanyWhatIsDisclosureAboutPage,
    postCompanyWhatIsDisclosureAboutPage,
    getCompanyAreYouTheOfficerPage,
    postCompanyAreYouTheOfficerPage,
    getCompanyAreYouRepresentingAnOrgPage,
    postCompanyAreYouRepresentingAnOrgPage,
    getCompanyOrgNamePage,
    postCompanyOrgNamePage,
    getCompanyOffshoreLiabilitiesPage,
    postCompanyOffshoreLiabilitiesPage,
    getCompanyOnshoreLiabilitiesPage,
    postCompanyOnshoreLiabilitiesPage,
    getCompanyNamePage,
    postCompanyNamePage,
    getCompanyRegistrationNumberPage,
    postCompanyRegistrationNumberPage,
    getCompanyAddressLookupPage,
    getCompanyALFEBeginPage,
    getCompanyALFECountryPickerPage,
    postCompanyALFECountryPickerPage,
    getCompanyALFEAddressEditPage,
    postCompanyALFEAddressEditPage,
    getCompanyALFEConfirmPage,
    postCompanyALFEConfirmPage,
    getCompanyYourFullNamePage,
    postCompanyYourFullNamePage
  )

  runSimulation()
}
