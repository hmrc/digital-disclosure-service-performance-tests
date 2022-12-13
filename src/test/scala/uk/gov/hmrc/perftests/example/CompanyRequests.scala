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

object CompanyRequests extends ServicesConfiguration {

  val postWhatIsDisclosureAboutPage_Company: HttpRequestBuilder =
    http("POST What Is This Disclosure About Page")
      .post(baseUrl + s"$${whatIsThisDisclosureAbout}": String)
      .formParam("""value""", """company""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/are-you-an-officer").saveAs("areYouAnOfficer"))

  val getAreYouAnOfficerPage: HttpRequestBuilder =
    http("GET Are You An Officer Page")
      .get(baseUrl + s"$${areYouAnOfficer}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postAreYouAnOfficerPage: HttpRequestBuilder =
    http("POST Are You An Officer Page")
      .post(baseUrl + s"$${areYouAnOfficer}": String)
      .formParam("""value""", """false""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/representing-organisation").saveAs("areYouRepresentingAnOrg"))

  val postOnshoreLiabilitiesPage_Company: HttpRequestBuilder =
    http("POST Onshore Liabilities Page")
      .post(baseUrl + s"$${onshore}": String)
      .formParam("""value""", """true""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/company-name").saveAs("companyName"))

  val getCompanyNamePage: HttpRequestBuilder =
    http("GET Company Name Page")
      .get(baseUrl + s"$${companyName}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCompanyNamePage: HttpRequestBuilder =
    http("POST Company Name Page")
      .post(baseUrl + s"$${companyName}": String)
      .formParam("""value""", """Test Name""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/company-registration").saveAs("companyRegistrationNumber"))

  val getCompanyRegistrationNumberPage: HttpRequestBuilder =
    http("GET Company Registration Number Page")
      .get(baseUrl + s"$${companyRegistrationNumber}": String)
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postCompanyRegistrationNumberPage: HttpRequestBuilder =
    http("POST Company Registration Number Page")
      .post(baseUrl + s"$${companyRegistrationNumber}": String)
      .formParam("""value""", """AB123456""")
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSHome/notification/company-address/lookup").saveAs("ddsLookup"))

  val getCompanyAddressLookupPage: HttpRequestBuilder =
    http("GET Address Lookup Page")
      .get(baseUrl + s"$${ddsLookup}": String)
      .check(status.is(303))
      .check(saveSubjectAddressLookupId)
      .check(header("Location").is(alfeUrl + s"/$${subjectAddressLookupId}" + "/begin").saveAs("alfeBegin"))


  val postALFECompanyConfirmPage: HttpRequestBuilder =
    http("POST ALFE Confirm Page")
      .post(baseUrlAddressLookupFrontend + s"$${alfeSubjectConfirm}": String)
      .formParam("csrfToken", s"$${csrfToken}")
      .check(status.is(303))
      .check(header("Location").is(s"$DDSUrl/notification/company-address/retrieve?id=$${subjectAddressLookupId}").saveAs("ddsRetrieveAddress"))

}
