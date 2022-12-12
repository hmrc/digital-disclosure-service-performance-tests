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

object EstateRequests extends ServicesConfiguration {

  private val lookupRegexp         = "(.*)/begin"
  val addressLookupIdPattern: String = """.*/lookup-address/(.*)/.*"""
  val saveAddressLookupId: HttpCheck = headerRegex("location", addressLookupIdPattern).saveAs("addressLookupId")
  val saveAddressLookupUrl: CheckBuilder[HttpHeaderRegexCheckType, Response, String] = headerRegex("Location", lookupRegexp).saveAs("lookupAddressLocation")




  val getHomePage: HttpRequestBuilder =
    http("Navigate to Home Page")
      .get(DDSUrl)
      .check(status.is(200))

  val getUsingThisServicePage: HttpRequestBuilder =
    http("Get Using This Service Page")
      .get(DDSUrl + "/using-this-service": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postUsingThisServicePage: HttpRequestBuilder =
    http("POST Using This Service Page")
      .post(DDSUrl + "/using-this-service": String)
      .formParam("""value""", """yes""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/letter-from-hmrc").saveAs("usingThisService"))

  val getLetterFromHMRCPage: HttpRequestBuilder =
  http("Get Letter From HMRC Page")
    .get(DDSUrl + "/notification/letter-from-hmrc": String)
    .check(status.is(200))
    .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postLetterFromHMRCPage: HttpRequestBuilder =
    http("POST Letter From HMRC Page")
    .post(DDSUrl + "/notification/letter-from-hmrc": String)
    .formParam("""value""", """true""")
    .formParam("csrfToken", s"$${csrfToken}")
    .check(status.is(303))
    .check(header("Location").is(s"$DDSHome/notification/hmrc-letter-reference").saveAs("letterReference"))

  val getHmrcLetterReferencePage: HttpRequestBuilder =
    http("Get HMRC Letter Ref Page")
      .get(baseUrl + s"$${letterReference}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postHmrcLetterReferencePage: HttpRequestBuilder =
    http("POST HMRC Letter Ref Page")
      .post(baseUrl + s"$${letterReference}": String)
      .formParam("""value""", """CFSS1234567""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/what-is-this-disclosure-about").saveAs("whatIsThisDisclosureAbout"))

  val getWhatIsDisclosureAboutPage: HttpRequestBuilder =
    http("Get What Is This Disclosure About Page")
      .get(baseUrl + s"$${letterReference}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postWhatIsDisclosureAboutPage: HttpRequestBuilder =
    http("POST What Is This Disclosure About Page")
      .post(baseUrl + s"$${whatIsThisDisclosureAbout}": String)
      .formParam("""value""", """individual""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/are-you-the-individual").saveAs("areYouTheIndividual"))

  val getAreYouTheIndividualPage: HttpRequestBuilder =
    http("Get Are You The Individual Page")
      .get(baseUrl + s"$${areYouTheIndividual}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postAreYouTheIndividualPage: HttpRequestBuilder =
    http("POST Are You The Individual Page")
      .post(baseUrl + s"$${areYouTheIndividual}": String)
      .formParam("""value""", """false""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/representing-organisation").saveAs("areYouRepresentingAnOrg"))

  val getAreYouRepresentingAnOrgPage: HttpRequestBuilder =
    http("Get Are You Representing An Org Page")
      .get(baseUrl + s"$${areYouRepresentingAnOrg}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postAreYouRepresentingAnOrgPage: HttpRequestBuilder =
    http("POST Are You Representing An Org Page")
      .post(baseUrl + s"$${areYouRepresentingAnOrg}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/representing-organisation-name").saveAs("orgName"))

  val getOrgNamePage: HttpRequestBuilder =
    http("Get Org Name Page")
      .get(baseUrl + s"$${orgName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postOrgNamePage: HttpRequestBuilder =
    http("POST Org Name Page")
      .post(baseUrl + s"$${orgName}": String)
      .formParam("""value""", """TestOrg""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/disclose-offshore-liabilities").saveAs("offshore"))

  val getOffshoreLiabilitiesPage: HttpRequestBuilder =
    http("Get Offshore Liabilities Page")
      .get(baseUrl + s"$${offshore}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postOffshoreLiabilitiesPage: HttpRequestBuilder =
    http("POST Offshore Liabilities Page")
      .post(baseUrl + s"$${offshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/disclose-onshore-liabilities").saveAs("onshore"))

  val getOnshoreLiabilitiesPage: HttpRequestBuilder =
    http("Get Onshore Liabilities Page")
      .get(baseUrl + s"$${onshore}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postOnshoreLiabilitiesPage: HttpRequestBuilder =
    http("POST Offshore Liabilities Page")
      .post(baseUrl + s"$${onshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-full-name").saveAs("individualName"))

  val getIndividualFullNamePage: HttpRequestBuilder =
    http("Get Individual Full Name Page")
      .get(baseUrl + s"$${individualName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIndividualFullNamePage: HttpRequestBuilder =
    http("POST Individual Full Name Page")
      .post(baseUrl + s"$${individualName}": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-date-of-birth").saveAs("dateOfBirth"))

  val getDateOfBirthPage: HttpRequestBuilder =
    http("Get Date Of Birth Page")
      .get(baseUrl + s"$${dateOfBirth}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postDateOfBirthPage: HttpRequestBuilder =
    http("POST Date Of Birth Page")
      .post(baseUrl + s"$${dateOfBirth}": String)
      .formParam("""value.day""", """1""")
      .formParam("""value.month""", """1""")
      .formParam("""value.year""", """1990""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-main-occupation").saveAs("occupation"))

  val getOccupationPage: HttpRequestBuilder =
    http("Get Occupation Page")
      .get(baseUrl + s"$${occupation}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postOccupationPage: HttpRequestBuilder =
    http("POST Occupation Page")
      .post(baseUrl + s"$${occupation}": String)
      .formParam("""value""", """Plumber""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-have-national-insurance-number").saveAs("haveNino"))

  val getHaveNinoPage: HttpRequestBuilder =
    http("Get Have NINO Page")
      .get(baseUrl + s"$${haveNino}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postHaveNinoPage: HttpRequestBuilder =
    http("POST Have NINO Page")
      .post(baseUrl + s"$${haveNino}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-national-insurance-number").saveAs("nino"))

  val getNinoPage: HttpRequestBuilder =
    http("Get NINO Page")
      .get(baseUrl + s"$${nino}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postNinoPage: HttpRequestBuilder =
    http("POST NINO Page")
      .post(baseUrl + s"$${nino}": String)
      .formParam("""value""", """JZ111111D""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-registered-for-vat").saveAs("regForVat"))

  val getIsRegForVATPage: HttpRequestBuilder =
    http("Get Registered For VAT Page")
      .get(baseUrl + s"$${regForVat}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIsRegForVATPage: HttpRequestBuilder =
    http("POST Registered For VAT Page")
      .post(baseUrl + s"$${regForVat}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-vat-registration").saveAs("vatNumber"))

  val getVATRegNumberPage: HttpRequestBuilder =
    http("Get VAT Registration Number Page")
      .get(baseUrl + s"$${vatNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postVATRegNumberPage: HttpRequestBuilder =
    http("POST VAT Registration Number Page")
      .post(baseUrl + s"$${vatNumber}": String)
      .formParam("""value""", """123456789""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-self-assessment").saveAs("regForSA"))

  val getIsRegForSAPage: HttpRequestBuilder =
    http("Get Registered For SA Page")
      .get(baseUrl + s"$${regForSA}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIsRegForSAPage: HttpRequestBuilder =
    http("Get Registered For SA Page")
      .post(baseUrl + s"$${regForSA}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-utr").saveAs("utrNumber"))

  val getUTRPage: HttpRequestBuilder =
    http("Get SA UTR Page")
      .get(baseUrl + s"$${utrNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postUTRPage: HttpRequestBuilder =
    http("Post SA UTR Page")
      .post(baseUrl + s"$${utrNumber}": String)
      .formParam("""value""", """1234567890""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-address/lookup").saveAs("ddsLookup"))

  val getAddressLookupPage: HttpRequestBuilder =
    http("Get Address Lookup Page")
      .get(baseUrl + s"$${ddsLookup}": String)
      .check(status.is(303))
      .check(saveAddressLookupId)
      .check(header("Location").is(alfeUrl + s"/$${addressLookupId}"+"/begin").saveAs("alfeBegin"))

  val getALFEBeginPage: HttpRequestBuilder =
    http("Get Address Lookup FrontEnd Begin Page")
      .get(s"$${alfeBegin}": String)
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${addressLookupId}"+"/country-picker").saveAs("alfeCountryLookup"))

  val getALFECountryPickerPage: HttpRequestBuilder =
    http("Get Address Lookup FrontEnd Country Picker Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeCountryLookup}": String)
      .check(status.is(200))

  val postALFECountryPickerPage: HttpRequestBuilder =
    http("Post ALFE Country Picker Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeCountryLookup}": String)
      .formParam("""countryCode""", """France""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${addressLookupId}"+"/international/edit").saveAs("alfeAddressEdit"))

  val getALFEAddressEditPage: HttpRequestBuilder =
    http("Get ALFE Address Edit Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeAddressEdit}": String)
      .check(status.is(200))

  val postALFEAddressEditPage: HttpRequestBuilder =
    http("Post ALFE Address Edit Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeAddressEdit}": String)
      .formParam("""line1""", """Address Line 1""")
      .formParam("""line2""", """Address Line 2""")
      .formParam("""town""", """Test Town""")
      .formParam("""countryCode""", """FR""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${addressLookupId}"+"/international/confirm").saveAs("alfeConfirm"))

  val getALFEConfirmPage: HttpRequestBuilder =
    http("Get ALFE Confirm Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeConfirm}": String)
      .check(status.is(200))

  val postALFEConfirmPage: HttpRequestBuilder =
    http("Post ALFE Confirm Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeConfirm}": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSUrl/notification/individual-address/retrieve?id=$${addressLookupId}").saveAs("ddsRetrieveAddress"))

  val getYourFullNamePage: HttpRequestBuilder =
    http("Get Your Full Name Page")
      .get(DDSUrl + "/notification/your-full-name": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postYourFullNamePage: HttpRequestBuilder =
    http("Post Your Full Name Page")
      .post(DDSUrl + "/notification/your-full-name": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/your-telephone-number").saveAs("telNumber"))

  val getYourTelNumberPage: HttpRequestBuilder =
    http("Get Telephone Number Page")
      .get(baseUrl + s"$${telNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postYourTelNumberPage: HttpRequestBuilder =
    http("Post Telephone Number Page")
      .post(baseUrl + s"$${telNumber}": String)
      .formParam("""value""", """0123456789""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/your-telephone-number").saveAs("haveEmail"))

}
