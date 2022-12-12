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
import io.gatling.http.check.HttpCheck
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.example.AuthLoginRequests._

object AddressLookupRequests extends ServicesConfiguration {

  val addressLookupIdPattern: String = """.*/lookup-address/(.*)/.*"""
  val saveSubjectAddressLookupId: HttpCheck = headerRegex("location", addressLookupIdPattern).saveAs("subjectAddressLookupId")
  private val saveYourAddressLookupId: HttpCheck = headerRegex("location", addressLookupIdPattern).saveAs("yourAddressLookupId")

  val getALFESubjectBeginPage: HttpRequestBuilder =
    http("GET Address Lookup FrontEnd Subject Begin Page")
      .get(s"$${alfeBegin}": String)
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${subjectAddressLookupId}" + "/country-picker").saveAs("alfeSubjectCountryLookup"))

  val getALFESubjectCountryPickerPage: HttpRequestBuilder =
    http("GET Address Lookup FrontEnd Subject Country Picker Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeSubjectCountryLookup}": String)
      .check(status.is(200))

  val postALFESubjectCountryPickerPage: HttpRequestBuilder =
    http("POST ALFE Subject Country Picker Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeSubjectCountryLookup}": String)
      .formParam("""countryCodeAutocomplete""", """France""")
      .formParam("""countryCode""", """FR""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${subjectAddressLookupId}" + "/international/edit").saveAs("alfeSubjectAddressEdit"))

  val getALFESubjectAddressEditPage: HttpRequestBuilder =
    http("GET ALFE Subject Address Edit Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeSubjectAddressEdit}": String)
      .check(status.is(200))

  val postALFESubjectAddressEditPage: HttpRequestBuilder =
    http("POST ALFE Subject Address Edit Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeSubjectAddressEdit}": String)
      .formParam("""line1""", """France Address Line 1""")
      .formParam("""line2""", """France Address Line 2""")
      .formParam("""town""", """France Test Town""")
      .formParam("""countryCode""", """FR""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${subjectAddressLookupId}" + "/international/confirm").saveAs("alfeSubjectConfirm"))

  val getALFESubjectConfirmPage: HttpRequestBuilder =
    http("GET ALFE Subject Confirm Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeSubjectConfirm}": String)
      .check(status.is(200))

  val getYourAddressLookupPage: HttpRequestBuilder =
    http("GET Your Address Lookup Page")
      .get(baseUrl + s"$${ddsYourAddressLookup}": String)
      .check(status.is(303))
      .check(saveYourAddressLookupId)
      .check(header("Location").is(alfeUrl + s"/$${yourAddressLookupId}"+"/begin").saveAs("alfeYourBegin"))

  val getALFEYourBeginPage: HttpRequestBuilder =
    http("GET Address Lookup FrontEnd Your Begin Page")
      .get(s"$${alfeYourBegin}": String)
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${yourAddressLookupId}"+"/country-picker").saveAs("alfeYourCountryLookup"))

  val getALFEYourCountryPickerPage: HttpRequestBuilder =
    http("GET Address Lookup FrontEnd Your Country Picker Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeYourCountryLookup}": String)
      .check(status.is(200))

  val postALFEYourCountryPickerPage: HttpRequestBuilder =
    http("POST ALFE Your Country Picker Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeYourCountryLookup}": String)
      .formParam("""countryCodeAutocomplete""", """Spain""")
      .formParam("""countryCode""", """ES""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${yourAddressLookupId}"+"/international/edit").saveAs("alfeYourAddressEdit"))

  val getALFEYourAddressEditPage: HttpRequestBuilder =
    http("GET ALFE Your Address Edit Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeYourAddressEdit}": String)
      .check(status.is(200))

  val postALFEYourAddressEditPage: HttpRequestBuilder =
    http("POST ALFE Your Address Edit Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeYourAddressEdit}": String)
      .formParam("""line1""", """Spain Address Line 1""")
      .formParam("""line2""", """Spain Address Line 2""")
      .formParam("""town""", """Spain Town""")
      .formParam("""countryCode""", """ES""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(alfeUrlLookup + s"/$${yourAddressLookupId}"+"/international/confirm").saveAs("alfeYourConfirm"))

  val getALFEYourAddressConfirmPage: HttpRequestBuilder =
    http("GET ALFE Your Confirm Page")
      .get(baseUrlAddressLookupFrontend + s"$${alfeYourConfirm}": String)
      .check(status.is(200))

  val postALFEYourAddressConfirmPage: HttpRequestBuilder =
    http("POST ALFE You Confirm Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeYourConfirm}": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSUrl/notification/your-address/retrieve?id=$${yourAddressLookupId}").saveAs("ddsRetrieveYourAddress"))

}
