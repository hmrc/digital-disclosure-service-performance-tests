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


//
//  setup("post-home-page", "POST Home Page") withRequests postHomePage
//
//  setup("post-vat-return-period", "Post vat return period") withRequests postVatReturnPeriod
//
//  setup("get-turnover-page", "Get turnover page") withRequests getTurnoverPage

  runSimulation()
}
