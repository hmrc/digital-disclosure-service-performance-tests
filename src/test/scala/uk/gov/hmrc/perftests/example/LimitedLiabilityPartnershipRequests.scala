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

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import io.gatling.http.check.header.HttpHeaderRegexCheckType
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.example.AuthLoginRequests._

object LimitedLiabilityPartnershipRequests extends ServicesConfiguration {

  private val lookupRegexp = "(.*)/begin"
  val addressLookupIdPattern: String = """.*/lookup-address/(.*)/.*"""
  val saveAddressLookupId: HttpCheck = headerRegex("location", addressLookupIdPattern).saveAs("addressLookupId")
  val saveAddressLookupUrl: CheckBuilder[HttpHeaderRegexCheckType, Response, String] = headerRegex("Location", lookupRegexp).saveAs("lookupAddressLocation")


  val getLLPHomePage: HttpRequestBuilder =
    http("Navigate to Home Page")
      .get(DDSUrl)
      .check(status.is(200))

  val getLLPLetterFromHMRCPage: HttpRequestBuilder =
    http("Get Letter From HMRC Page")
      .get(DDSUrl + "/notification/letter-from-hmrc": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPLetterFromHMRCPage: HttpRequestBuilder =
    http("POST Letter From HMRC Page")
      .post(DDSUrl + "/notification/letter-from-hmrc": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/hmrc-letter-reference").saveAs("letterReference"))

  val getLLPHmrcLetterReferencePage: HttpRequestBuilder =
    http("Get HMRC Letter Ref Page")
      .get(baseUrl + s"$${letterReference}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPHmrcLetterReferencePage: HttpRequestBuilder =
    http("POST HMRC Letter Ref Page")
      .post(baseUrl + s"$${letterReference}": String)
      .formParam("""value""", """CFSS1234567""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/what-is-this-disclosure-about").saveAs("whatIsThisDisclosureAbout"))

  val getLLPWhatIsDisclosureAboutPage: HttpRequestBuilder =
    http("Get What Is This Disclosure About Page")
      .get(baseUrl + s"$${whatIsThisDisclosureAbout}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPWhatIsDisclosureAboutPage: HttpRequestBuilder =
    http("POST What Is This Disclosure About Page")
      .post(baseUrl + s"$${whatIsThisDisclosureAbout}": String)
      .formParam("""value""", """llp""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/are-you-a-designated-member").saveAs("areYouDesignatedMember"))

  val getLLPAreYouDesignatedMemberPage: HttpRequestBuilder =
    http("Get Are You The Designated Member Page")
      .get(baseUrl + s"$${areYouDesignatedMember}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPAreYouDesignatedMemberPage: HttpRequestBuilder =
    http("POST Are You The Designated Member Page")
      .post(baseUrl + s"$${areYouDesignatedMember}": String)
      .formParam("""value""", """false""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/representing-organisation").saveAs("areYouRepresentingAnOrg"))

  val getLLPAreYouRepresentingAnOrgPage: HttpRequestBuilder =
    http("Get Are You Representing An Org Page")
      .get(baseUrl + s"$${areYouRepresentingAnOrg}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPAreYouRepresentingAnOrgPage: HttpRequestBuilder =
    http("POST Are You Representing An Org Page")
      .post(baseUrl + s"$${areYouRepresentingAnOrg}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/representing-organisation-name").saveAs("orgName"))

  val getLLPOrgNamePage: HttpRequestBuilder =
    http("Get Org Name Page")
      .get(baseUrl + s"$${orgName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPOrgNamePage: HttpRequestBuilder =
    http("POST Org Name Page")
      .post(baseUrl + s"$${orgName}": String)
      .formParam("""value""", """TestOrg""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/disclose-offshore-liabilities").saveAs("offshore"))

  val getLLPOffshoreLiabilitiesPage: HttpRequestBuilder =
    http("Get Offshore Liabilities Page")
      .get(baseUrl + s"$${offshore}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPOffshoreLiabilitiesPage: HttpRequestBuilder =
    http("POST Offshore Liabilities Page")
      .post(baseUrl + s"$${offshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/disclose-onshore-liabilities").saveAs("onshore"))

  val getLLPOnshoreLiabilitiesPage: HttpRequestBuilder =
    http("Get Onshore Liabilities Page")
      .get(baseUrl + s"$${onshore}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPOnshoreLiabilitiesPage: HttpRequestBuilder =
    http("POST Offshore Liabilities Page")
      .post(baseUrl + s"$${onshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/llp-name").saveAs("llpName"))

  val getLLPNamePage: HttpRequestBuilder =
    http("Get LLP Name Page")
      .get(baseUrl + s"$${llpName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPNamePage: HttpRequestBuilder =
    http("POST LLP Name Page")
      .post(baseUrl + s"$${llpName}": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/llp-address/lookup").saveAs("ddsLookup"))

  val getLLPAddressLookupPage: HttpRequestBuilder =
    http("Get Address Lookup Page")
      .get(baseUrl + s"$${ddsLookup}": String)
      .check(status.is(303))
      .check(saveAddressLookupId)
      .check(header("Location").is(alfeUrl + s"/$${addressLookupId}" + "/begin").saveAs("alfeBegin"))

  val getLLPALFEBeginPage: HttpRequestBuilder =
    http("Get Address Lookup FrontEnd Begin Page")
      .get(s"$${alfeBegin}": String)
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${addressLookupId}" + "/country-picker").saveAs("alfeCountryLookup"))

  val getLLPALFECountryPickerPage: HttpRequestBuilder =
    http("Get Address Lookup FrontEnd Country Picker Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeCountryLookup}": String)
      .check(status.is(200))

  val postLLPALFECountryPickerPage: HttpRequestBuilder =
    http("Post ALFE Country Picker Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeCountryLookup}": String)
      .formParam("""countryCode""", """France""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${addressLookupId}" + "/international/edit").saveAs("alfeAddressEdit"))

  val getLLPALFEAddressEditPage: HttpRequestBuilder =
    http("Get ALFE Address Edit Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeAddressEdit}": String)
      .check(status.is(200))

  val postLLPALFEAddressEditPage: HttpRequestBuilder =
    http("Post ALFE Address Edit Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeAddressEdit}": String)
      .formParam("""line1""", """Address Line 1""")
      .formParam("""line2""", """Address Line 2""")
      .formParam("""town""", """Test Town""")
      .formParam("""countryCode""", """FR""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${addressLookupId}" + "/international/confirm").saveAs("alfeConfirm"))

  val getLLPALFEConfirmPage: HttpRequestBuilder =
    http("Get ALFE Confirm Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeConfirm}": String)
      .check(status.is(200))

  val postLLPALFEConfirmPage: HttpRequestBuilder =
    http("Post ALFE Confirm Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeConfirm}": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSUrl/notification/llp-address/retrieve?id=$${addressLookupId}").saveAs("ddsRetrieveAddress"))

  val getLLPYourFullNamePage: HttpRequestBuilder =
    http("Get Your Full Name Page")
      .get(DDSUrl + "/notification/your-full-name": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLLPYourFullNamePage: HttpRequestBuilder =
    http("Post Your Full Name Page")
      .post(DDSUrl + "/notification/your-full-name": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/your-telephone-number").saveAs("telNumber"))









  //  val getLLPOccupationPage: HttpRequestBuilder =
  //    http("Get Occupation Page")
  //      .get(baseUrl + s"$${occupation}": String)
  //      .check(status.is(200))
  //      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  //
  //  val postLLPOccupationPage: HttpRequestBuilder =
  //    http("POST Occupation Page")
  //      .post(baseUrl + s"$${occupation}": String)
  //      .formParam("""value""", """Plumber""")
  //      .formParam("csrfToken", s"$${csrfToken}")
  //      .check(status.is(303))
  //      .check(header("Location").is(s"$DDSHome/notification/estates-have-national-insurance-number").saveAs("haveNino"))
  //
  //  val getLLPHaveNinoPage: HttpRequestBuilder =
  //    http("Get Have NINO Page")
  //      .get(baseUrl + s"$${haveNino}": String)
  //      .check(status.is(200))
  //      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  //
  //  val postLLPHaveNinoPage: HttpRequestBuilder =
  //    http("POST Have NINO Page")
  //      .post(baseUrl + s"$${haveNino}": String)
  //      .formParam("""value""", """yesIKnow""")
  //      .formParam("csrfToken", s"$${csrfToken}")
  //      .check(status.is(303))
  //      .check(header("Location").is(s"$DDSHome/notification/estates-individual-national-insurance-number").saveAs("nino"))
  //
  //  val getLLPNinoPage: HttpRequestBuilder =
  //    http("Get NINO Page")
  //      .get(baseUrl + s"$${nino}": String)
  //      .check(status.is(200))
  //      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  //
  //  val postLLPNinoPage: HttpRequestBuilder =
  //    http("POST NINO Page")
  //      .post(baseUrl + s"$${nino}": String)
  //      .formParam("""value""", """JZ111111D""")
  //      .formParam("csrfToken", s"$${csrfToken}")
  //      .check(status.is(303))
  //      .check(header("Location").is(s"$DDSHome/notification/estates-individual-registered-for-vat").saveAs("regForVat"))
  //
  //  val getLLPIsRegForVATPage: HttpRequestBuilder =
  //    http("Get Registered For VAT Page")
  //      .get(baseUrl + s"$${regForVat}": String)
  //      .check(status.is(200))
  //      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  //
  //  val postLLPIsRegForVATPage: HttpRequestBuilder =
  //    http("POST Registered For VAT Page")
  //      .post(baseUrl + s"$${regForVat}": String)
  //      .formParam("""value""", """yesIKnow""")
  //      .formParam("csrfToken", s"$${csrfToken}")
  //      .check(status.is(303))
  //      .check(header("Location").is(s"$DDSHome/notification/estates-individual-vat-registration").saveAs("vatNumber"))
  //
  //  val getLLPVATRegNumberPage: HttpRequestBuilder =
  //    http("Get VAT Registration Number Page")
  //      .get(baseUrl + s"$${vatNumber}": String)
  //      .check(status.is(200))
  //      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  //
  //  val postLLPVATRegNumberPage: HttpRequestBuilder =
  //    http("POST VAT Registration Number Page")
  //      .post(baseUrl + s"$${vatNumber}": String)
  //      .formParam("""value""", """123456789""")
  //      .formParam("csrfToken", s"$${csrfToken}")
  //      .check(status.is(303))
  //      .check(header("Location").is(s"$DDSHome/notification/estates-individual-self-assessment").saveAs("regForSA"))
  //
  //  val getLLPIsRegForSAPage: HttpRequestBuilder =
  //    http("Get Registered For SA Page")
  //      .get(baseUrl + s"$${regForSA}": String)
  //      .check(status.is(200))
  //      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  //
  //  val postLLPIsRegForSAPage: HttpRequestBuilder =
  //    http("Get Registered For SA Page")
  //      .post(baseUrl + s"$${regForSA}": String)
  //      .formParam("""value""", """yesIKnow""")
  //      .formParam("csrfToken", s"$${csrfToken}")
  //      .check(status.is(303))
  //      .check(header("Location").is(s"$DDSHome/notification/estates-individual-utr").saveAs("utrNumber"))
  //
  //  val getLLPUTRPage: HttpRequestBuilder =
  //    http("Get SA UTR Page")
  //      .get(baseUrl + s"$${utrNumber}": String)
  //      .check(status.is(200))
  //      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  //
  //  val postLLPUTRPage: HttpRequestBuilder =
  //    http("Post SA UTR Page")
  //      .post(baseUrl + s"$${utrNumber}": String)
  //      .formParam("""value""", """1234567890""")
  //      .formParam("csrfToken", s"$${csrfToken}")
  //      .check(status.is(303))
  //      .check(header("Location").is(s"$DDSHome/notification/estate-address/lookup").saveAs("ddsLookup"))
  //
  //
  //
  //
  //
  //  val getLLPYourTelNumberPage: HttpRequestBuilder =
  //    http("Get Telephone Number Page")
  //      .get(baseUrl + s"$${telNumber}": String)
  //      .check(status.is(200))
  //      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))
  //
  //  val postLLPYourTelNumberPage: HttpRequestBuilder =
  //    http("Post Telephone Number Page")
  //      .post(baseUrl + s"$${telNumber}": String)
  //      .formParam("""value""", """0123456789""")
  //      .formParam("csrfToken", s"$${csrfToken}")
  //      .check(status.is(303))
  //      .check(header("Location").is(s"$DDSHome/notification/contact-by-email").saveAs("haveEmail"))

}
