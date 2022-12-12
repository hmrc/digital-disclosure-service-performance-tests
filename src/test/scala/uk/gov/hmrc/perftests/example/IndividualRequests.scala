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
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.example.AddressLookupRequests.saveSubjectAddressLookupId
import uk.gov.hmrc.perftests.example.AuthLoginRequests._

object IndividualRequests extends ServicesConfiguration {

  val postWhatIsDisclosureAboutPage_Individual: HttpRequestBuilder =
    http("POST What Is This Disclosure About Page")
      .post(baseUrl + s"$${whatIsThisDisclosureAbout}": String)
      .formParam("""value""", """individual""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/are-you-the-individual").saveAs("areYouTheIndividual"))

  val getAreYouTheIndividualPage: HttpRequestBuilder =
    http("GET Are You The Individual Page")
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

  val getIndividualFullNamePage: HttpRequestBuilder =
    http("GET Individual Full Name Page")
      .get(baseUrl + s"$${individualName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postOnshoreLiabilitiesPage_Individual: HttpRequestBuilder =
    http("POST Onshore Liabilities Page")
      .post(baseUrl + s"$${onshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-full-name").saveAs("individualName"))

  val postIndividualFullNamePage: HttpRequestBuilder =
    http("POST Individual Full Name Page")
      .post(baseUrl + s"$${individualName}": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-date-of-birth").saveAs("dateOfBirth"))

  val getIndividualDateOfBirthPage: HttpRequestBuilder =
    http("GET Date Of Birth Page")
      .get(baseUrl + s"$${dateOfBirth}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIndividualDateOfBirthPage: HttpRequestBuilder =
    http("POST Date Of Birth Page")
      .post(baseUrl + s"$${dateOfBirth}": String)
      .formParam("""value.day""", """1""")
      .formParam("""value.month""", """1""")
      .formParam("""value.year""", """1990""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-main-occupation").saveAs("occupation"))

  val getIndividualOccupationPage: HttpRequestBuilder =
    http("GET Occupation Page")
      .get(baseUrl + s"$${occupation}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIndividualOccupationPage: HttpRequestBuilder =
    http("POST Occupation Page")
      .post(baseUrl + s"$${occupation}": String)
      .formParam("""value""", """Plumber""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-have-national-insurance-number").saveAs("haveNino"))

  val getIndividualHaveNinoPage: HttpRequestBuilder =
    http("GET Have NINO Page")
      .get(baseUrl + s"$${haveNino}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIndividualHaveNinoPage: HttpRequestBuilder =
    http("POST Have NINO Page")
      .post(baseUrl + s"$${haveNino}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-national-insurance-number").saveAs("nino"))

  val getIndividualNinoPage: HttpRequestBuilder =
    http("GET NINO Page")
      .get(baseUrl + s"$${nino}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIndividualNinoPage: HttpRequestBuilder =
    http("POST NINO Page")
      .post(baseUrl + s"$${nino}": String)
      .formParam("""value""", """JZ111111D""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-registered-for-vat").saveAs("regForVat"))

  val getIsIndividualRegForVATPage: HttpRequestBuilder =
    http("GET Registered For VAT Page")
      .get(baseUrl + s"$${regForVat}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIsIndividualRegForVATPage: HttpRequestBuilder =
    http("POST Registered For VAT Page")
      .post(baseUrl + s"$${regForVat}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-vat-registration").saveAs("vatNumber"))

  val getIndividualVATRegNumberPage: HttpRequestBuilder =
    http("GET VAT Registration Number Page")
      .get(baseUrl + s"$${vatNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIndividualVATRegNumberPage: HttpRequestBuilder =
    http("POST VAT Registration Number Page")
      .post(baseUrl + s"$${vatNumber}": String)
      .formParam("""value""", """123456789""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-self-assessment").saveAs("regForSA"))

  val getIsIndividualRegForSAPage: HttpRequestBuilder =
    http("GET Registered For SA Page")
      .get(baseUrl + s"$${regForSA}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIsIndividualRegForSAPage: HttpRequestBuilder =
    http("POST Registered For SA Page")
      .post(baseUrl + s"$${regForSA}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-utr").saveAs("utrNumber"))

  val getIndividualUTRPage: HttpRequestBuilder =
    http("GET SA UTR Page")
      .get(baseUrl + s"$${utrNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postIndividualUTRPage: HttpRequestBuilder =
    http("POST SA UTR Page")
      .post(baseUrl + s"$${utrNumber}": String)
      .formParam("""value""", """1234567890""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/individual-address/lookup").saveAs("ddsIndividualAddressLookup"))

  val getIndividualAddressLookupPage: HttpRequestBuilder =
    http("GET Individual Address Lookup Page")
      .get(baseUrl + s"$${ddsIndividualAddressLookup}": String)
      .check(status.is(303))
      .check(saveSubjectAddressLookupId)
      .check(header("Location").is(alfeUrl + s"/$${subjectAddressLookupId}"+"/begin").saveAs("alfeBegin"))

  val postALFEIndividualConfirmPage: HttpRequestBuilder =
    http("POST ALFE Individual Confirm Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeSubjectConfirm}": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSUrl/notification/individual-address/retrieve?id=$${subjectAddressLookupId}").saveAs("ddsRetrieveIndividualAddress"))

}
