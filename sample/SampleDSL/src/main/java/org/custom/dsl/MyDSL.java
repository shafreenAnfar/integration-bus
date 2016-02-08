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


import org.wso2.carbon.esb5.mediation.cheetah.config.dsl.ConfigurationBuilder;
import static org.wso2.carbon.esb5.mediation.cheetah.inbound.protocols.http.builder.HTTPInboundEPBuilder.*;
import static org.wso2.carbon.esb5.mediation.cheetah.outbound.protocol.http.builder.HTTPOutboundEPBuilder.*;


/**
 * Sample DSL
 */
public class MyDSL extends ConfigurationBuilder {

    public IntegrationFlow configure() {

        IntegrationFlow router = integrationFlow("MessageRouter");

        router.inboundEndpoint("inboundEP1").
                    http(port(9090), context("/sample/request")).
               pipeline("seq1").call("outboundEP1");

        /*
        router.pipeline("seq1").
                filter(condition("route==foo")).
                then(log().call("outboundEP1")).
                otherwise(call("outboundEP2")).respond();
        */

        router.outboundEndpoint("outboundEP1").
                http(uri("http://localhost:8080"));

        router.outboundEndpoint("outboundEP2").
                http(uri("http://localhost:8081"));

        return router;

    }
}
