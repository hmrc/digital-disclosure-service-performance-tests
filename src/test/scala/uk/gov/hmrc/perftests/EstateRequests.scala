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

import io.gatling.core.Predef._
import io.gatling.core.check.CheckBuilder
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import io.gatling.http.check.header.HttpHeaderRegexCheckType
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import AddressLookupRequests.saveSubjectAddressLookupId
import AuthLoginRequests._

object EstateRequests extends ServicesConfiguration {

  val postWhatIsDisclosureAboutPage_Estate: HttpRequestBuilder =
    http("POST What Is This Disclosure About Page")
      .post(baseUrl + s"$${whatIsThisDisclosureAbout}": String)
      .formParam("""value""", """estate""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/are-you-the-executor").saveAs("areYouTheExecutor"))

  val getAreYouTheEstateExecutorPage: HttpRequestBuilder =
    http("GET Are You The Executor Page")
      .get(baseUrl + s"$${areYouTheExecutor}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postAreYouTheEstateExecutorPage: HttpRequestBuilder =
    http("POST Are You The Executor Page")
      .post(baseUrl + s"$${areYouTheExecutor}": String)
      .formParam("""value""", """false""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/representing-organisation").saveAs("areYouRepresentingAnOrg"))

  val postOnshoreLiabilitiesPage_Estate: HttpRequestBuilder =
    http("POST Onshore Liabilities Page")
      .post(baseUrl + s"$${onshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-individual-full-name").saveAs("individualName"))

  val getEstateIndividualFullNamePage: HttpRequestBuilder =
    http("GET Individual Full Name Page")
      .get(baseUrl + s"$${individualName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualFullNamePage: HttpRequestBuilder =
    http("POST Individual Full Name Page")
      .post(baseUrl + s"$${individualName}": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-individual-date-of-birth").saveAs("dateOfBirth"))

  val getEstateIndividualDateOfBirthPage: HttpRequestBuilder =
    http("GET Date Of Birth Page")
      .get(baseUrl + s"$${dateOfBirth}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualDateOfBirthPage: HttpRequestBuilder =
    http("POST Date Of Birth Page")
      .post(baseUrl + s"$${dateOfBirth}": String)
      .formParam("""value.day""", """1""")
      .formParam("""value.month""", """1""")
      .formParam("""value.year""", """1990""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-individual-main-occupation").saveAs("occupation"))

  val getEstateIndividualOccupationPage: HttpRequestBuilder =
    http("GET Occupation Page")
      .get(baseUrl + s"$${occupation}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualOccupationPage: HttpRequestBuilder =
    http("POST Occupation Page")
      .post(baseUrl + s"$${occupation}": String)
      .formParam("""value""", """Plumber""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-have-national-insurance-number").saveAs("haveNino"))

  val getEstateIndividualHadNinoPage: HttpRequestBuilder =
    http("GET Have NINO Page")
      .get(baseUrl + s"$${haveNino}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualHadNinoPage: HttpRequestBuilder =
    http("POST Have NINO Page")
      .post(baseUrl + s"$${haveNino}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-individual-national-insurance-number").saveAs("nino"))

  val getEstateIndividualNinoPage: HttpRequestBuilder =
    http("GET NINO Page")
      .get(baseUrl + s"$${nino}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualNinoPage: HttpRequestBuilder =
    http("POST NINO Page")
      .post(baseUrl + s"$${nino}": String)
      .formParam("""value""", """JZ111111D""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-individual-registered-for-vat").saveAs("regForVat"))

  val getEstateIndividualWasRegForVATPage: HttpRequestBuilder =
    http("GET Registered For VAT Page")
      .get(baseUrl + s"$${regForVat}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualWasRegForVATPage: HttpRequestBuilder =
    http("POST Registered For VAT Page")
      .post(baseUrl + s"$${regForVat}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-individual-vat-registration").saveAs("vatNumber"))

  val getEstateIndividualVATRegNumberPage: HttpRequestBuilder =
    http("GET VAT Registration Number Page")
      .get(baseUrl + s"$${vatNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualVATRegNumberPage: HttpRequestBuilder =
    http("POST VAT Registration Number Page")
      .post(baseUrl + s"$${vatNumber}": String)
      .formParam("""value""", """123456789""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-individual-self-assessment").saveAs("regForSA"))

  val getEstateIndividualWasRegForSAPage: HttpRequestBuilder =
    http("GET Registered For SA Page")
      .get(baseUrl + s"$${regForSA}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualWasRegForSAPage: HttpRequestBuilder =
    http("POST Registered For SA Page")
      .post(baseUrl + s"$${regForSA}": String)
      .formParam("""value""", """yesIKnow""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estates-individual-utr").saveAs("utrNumber"))

  val getEstateIndividualUTRPage: HttpRequestBuilder =
    http("GET SA UTR Page")
      .get(baseUrl + s"$${utrNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postEstateIndividualUTRPage: HttpRequestBuilder =
    http("POST SA UTR Page")
      .post(baseUrl + s"$${utrNumber}": String)
      .formParam("""value""", """1234567890""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/estate-address/lookup").saveAs("ddsLookup"))

  val getEstateAddressLookupPage: HttpRequestBuilder =
    http("GET Address Lookup Page")
      .get(baseUrl + s"$${ddsLookup}": String)
      .check(status.is(303))
      .check(saveSubjectAddressLookupId)
      .check(header("Location").is(alfeUrl + s"/$${subjectAddressLookupId}"+"/begin").saveAs("alfeBegin"))

  val postALFEEstateConfirmPage: HttpRequestBuilder =
    http("POST ALFE Confirm Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeSubjectConfirm}": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSUrl/notification/estate-address/retrieve?id=$${subjectAddressLookupId}").saveAs("ddsRetrieveAddress"))

}
