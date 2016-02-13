/*
*  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.custom.dsl;


import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.ConfigurationBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Scope;

import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.inbound.http.HTTPInboundEPBuilder.*;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.outbound.http.HTTPOutboundEPBuilder.*;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.flow.mediators.FilterMediatorBuilder.*;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.flow.mediators.CallMediatorBuilder.*;



/**
 * Sample DSL
 */
public class MyDSL extends ConfigurationBuilder {

    public IntegrationFlow configure() {

        IntegrationFlow router = integrationFlow("MessageRouter");

        router.inboundEndpoint("inboundEP1").
                   http(port(8280), context("/sample/request")).
                   pipeline("pipeline1").
                   filter(condition(source("routeId", Scope.Transport), pattern("r1"))).
                   then(call("outboundEP1").respond()).
                   otherwise(call("outboundEP2")).respond();

        router.outboundEndpoint("outboundEP1").
                   http(uri("http://204.13.85.5:5050/service"));

        router.outboundEndpoint("outboundEP2").
                   http(uri("http://204.13.85.5:5050/service"));

        return router;

    }
}
