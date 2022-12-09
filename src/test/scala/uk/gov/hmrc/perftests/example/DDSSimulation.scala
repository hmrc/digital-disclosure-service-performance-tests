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
import uk.gov.hmrc.perftests.example.AuthLoginRequests.{getLoginPage, postLoginPage}
import uk.gov.hmrc.perftests.example.ExampleRequests._
import uk.gov.hmrc.perftests.example.IndividualRequests._

class DDSSimulation extends PerformanceTestRunner {

  setup("get-auth-login", "GET Auth Login") withRequests getLoginPage

  setup("post-auth-login", "POST Auth Login") withRequests postLoginPage

  setup("navigate-to-home-page", "GET Home Page") withRequests getHomePage

  setup("get-letter-from-hmrc-page", "GET Letter From HMRC Page") withRequests getLetterFromHMRCPage

  setup("post-letter-from-hmrc-page", "POST Letter From HMRC Page") withRequests postLetterFromHMRCPage

  setup("get-hmrc-letter-reference-page", "GET HMRC Letter Reference Page") withRequests getHmrcLetterReferencePage

  setup("post-hmrc-letter-reference-page", "POST HMRC Letter Reference Page") withRequests postHmrcLetterReferencePage

  setup("get-what-is-disclosure-about-page", "GET What Is This Disclosure About Page") withRequests getWhatIsDisclosureAboutPage

  setup("post-what-is-disclosure-about-page", "POST What Is This Disclosure About Page") withRequests postWhatIsDisclosureAboutPage

  setup("get-are-you-the-individual-page", "GET Are You The Individual Page") withRequests getAreYouTheIndividualPage

  setup("post-are-you-the-individual-page", "POST Are You The Individual Page") withRequests postAreYouTheIndividualPage

  setup("get-are-you-representing-an-org-page", "GET Are You Representing An Org Page") withRequests getAreYouRepresentingAnOrgPage

  setup("post-are-you-representing-an-org-page", "POST Are You Representing An Org Page") withRequests postAreYouRepresentingAnOrgPage

  setup("get-org-name-page", "GET Representing Organisation Name Page") withRequests getOrgNamePage

  setup("post-org-name-page", "POST Representing Organisation Name Page") withRequests postOrgNamePage

  setup("get-offshore-liabilities-page", "GET Disclose Offshore Liabilities Page") withRequests getOffshoreLiabilitiesPage

  setup("post-offshore-liabilities-page", "POST Disclose Offshore Liabilities Page") withRequests postOffshoreLiabilitiesPage

  setup("get-onshore-liabilities-page", "GET Disclose Onshore Liabilities Page") withRequests getOnshoreLiabilitiesPage

  setup("post-onshore-liabilities-page", "POST Disclose Onshore Liabilities Page") withRequests postOnshoreLiabilitiesPage

  setup("get-individual-full-name-page", "GET Individual Full Name Page") withRequests getIndividualFullNamePage

  setup("post-individual-full-name-page", "POST Individual Full Name Page") withRequests postIndividualFullNamePage

  setup("get-date-of-birth-page", "GET Date Of Birth Page") withRequests getDateOfBirthPage

  setup("post-date-of-birth-page", "POST Date Of Birth Page") withRequests postDateOfBirthPage

  setup("get-occupation-page", "GET Individual Main Occupation Page") withRequests getOccupationPage

  setup("post-occupation-page", "POST Individual Main Occupation Page") withRequests postOccupationPage

  setup("get-have-nino-page", "GET Have NINO Page") withRequests getHaveNinoPage

  setup("post-have-nino-page", "POST Have NINO Page") withRequests postHaveNinoPage

  setup("get-nino-page", "GET NINO Page") withRequests getNinoPage

  setup("post-nino-page", "POST NINO Page") withRequests postNinoPage

  setup("get-registered-for-vat-page", "GET Registered For VAT Page") withRequests getIsRegForVATPage

  setup("post-registered-for-vat-page", "POST Registered For VAT Page") withRequests postIsRegForVATPage

  setup("get-vat-registration-number-page", "GET VAT Registration Number Page") withRequests getVATRegNumberPage

  setup("post-vat-registration-number-page", "POST VAT Registration Number Page") withRequests postVATRegNumberPage

  setup("get-registered-for-self-assessment-page", "GET Registered For Self Assessment Page") withRequests getIsRegForSAPage

  setup("post-registered-for-self-assessment-page", "POST Registered For Self Assessment Page") withRequests postIsRegForSAPage

  setup("get-self-assessment-utr-page", "GET Self Assessment UTR Page") withRequests getUTRPage

  setup("post-self-assessment-utr-page", "POST Self Assessment UTR Page") withRequests postUTRPage

  setup("get-dds-address-lookup-page", "GET DDS Address Lookup Page") withRequests getAddressLookupPage

  setup("get-alfe-begin-page", "GET ALFE Begin Page") withRequests getALFEBeginPage

  setup("get-alfe-country-picker-page", "GET ALFE Country Picker Page") withRequests getALFECountryPickerPage

  setup("post-alfe-country-picker-page", "POST ALFE Country Picker Page") withRequests postALFECountryPickerPage

  setup("get-alfe-address-edit-page", "GET ALFE Address Edit Page") withRequests getALFEAddressEditPage

  setup("post-alfe-address-edit-page", "POST ALFE Address Edit Page") withRequests postALFEAddressEditPage

  setup("post-alfe-confirm-page", "POST ALFE Confirm Page") withRequests postALFEConfirmPage

  setup("get-your-full-name-page", "GET Your Full Name Page") withRequests getYourFullNamePage

  setup("post-your-full-name-page", "POST Your Full Name Page") withRequests postYourFullNamePage

//  setup("get-tel-number-page", "GET Your Telephone Number Page") withRequests getYourTelNumberPage

//  setup("post-tel-number-page", "POST Your Telephone Number Page") withRequests postYourTelNumberPage









  //
//  setup("post-home-page", "POST Home Page") withRequests postHomePage
//
//  setup("post-vat-return-period", "Post vat return period") withRequests postVatReturnPeriod
//
//  setup("get-turnover-page", "Get turnover page") withRequests getTurnoverPage

  runSimulation()
}
