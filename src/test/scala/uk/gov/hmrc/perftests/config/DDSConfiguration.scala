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

package uk.gov.hmrc.perftests.config

import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait DDSConfiguration extends ServicesConfiguration {

  val baseUrlAddressLookupFrontend: String = baseUrlFor("address-lookup-frontend")
  val alfeUrlLookup                        = "/lookup-address"
  val alfeUrl: String                      = baseUrlAddressLookupFrontend + "/lookup-address"
  val postcode: String                     = readProperty("services.address-lookup-frontend.postcode")
  val baseUrl: String                      = baseUrlFor("digital-disclosure")
  val DDSHome: String                      = "/digital-disclosure"
  val DDSUrl: String                       = baseUrl + DDSHome
  val loginUrl: String                     = baseUrlFor("auth-login-stub")
}
