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
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.headerrouter.HeaderBasedRouter;


/**
 * Sample DSL
 */
public class MyDSL extends ConfigurationBuilder {

    public void configure() {


        httpInboundEndpoint("inboundEP1", 9090, "seq1").context("/sample/request");

        HeaderBasedRouter headerBasedRouter = headerBasedRouter().
                ifHeader("routeId").
                equals("r1").
                then(call("outboundEP1")).
                ifHeader("routeId").
                equals("r2").
                then(call("outboundEP2"))
                .elseDefault(call("outboundEP3"));

        sequence("seq1").flowController(headerBasedRouter);

        outboundEndpoint("outboundEP1", "http://localhost:8080/service");
        outboundEndpoint("outboundEP2", "http://localhost:8081/service");
        outboundEndpoint("outboundEP3", "http://localhost:8082/service");

    }
}
