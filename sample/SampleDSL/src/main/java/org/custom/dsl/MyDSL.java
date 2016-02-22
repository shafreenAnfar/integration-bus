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


import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.inbound.http.HTTPInboundEPBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.flow.AbstractMediator;

import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.JavaConfigurationBuilder;

import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Scope;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.protocols.http.HTTPInboundEP;
import org.wso2.carbon.messaging.CarbonCallback;
import org.wso2.carbon.messaging.CarbonMessage;

import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.flow.mediators.CallMediatorBuilder.call;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.flow.mediators.FilterMediatorBuilder.condition;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.flow.mediators.FilterMediatorBuilder.pattern;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.flow.mediators.FilterMediatorBuilder.source;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.inbound.http.HTTPInboundEPBuilder.context;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.inbound.http.HTTPInboundEPBuilder.*;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.outbound.http.HTTPOutboundEPBuilder.httpOutboundEndpoint;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.outbound.http.HTTPOutboundEPBuilder.uri;


/**
 * Sample DSL
 */
public class MyDSL extends JavaConfigurationBuilder {

    public IntegrationFlow configure() {

        IntegrationFlow router = integrationFlow("MessageRouter");


        router.inboundEndpoint("inboundEndpoint1", http(port(8280), context("/sample/request"))).
                   pipeline("pipeline1").onError("epipe").
                   filter(condition(source("routeId", Scope.Transport), pattern("r1"))).
                   then(call("outboundEp1")).
                   otherwise(call("outboundEp2")).
                   respond();


        /** New Format

         router.inboundEndpoint("inboundEndpoint1", http(port(8280), context("/sample/request"))).
         pipeline("pipeline1", onError("epipe")).
         filter(condition(source("routeId", Scope.Transport), pattern("r1"))).
         then(process(mymediator()).call("outboundEp1")).
         otherwise(call("outboundEp2")).
         respond();

         */


        router.pipeline("ePipe").respond();

        router.outboundEndpoint(httpOutboundEndpoint("outboundEp1", uri("http://localhost:9000/service")));

        router.outboundEndpoint(httpOutboundEndpoint("outboundEp2", uri("http://204.13.85.5:5050/service")));

        router.outboundEndpoint(httpOutboundEndpoint("outboundEp3", uri("http://204.13.85.6:5050/service")));

        return router;

    }


    private class MyInbound extends HTTPInboundEP {

        public MyInbound(String name, HTTPInboundEPBuilder.Port port) {
            super(name, port.getPort());
        }

        @Override
        public boolean canReceive(CarbonMessage cMsg) {
            return true;
        }
    }


    private class MyCustomMediator extends AbstractMediator {

        @Override
        public boolean receive(CarbonMessage carbonMessage, CarbonCallback carbonCallback) throws Exception {
            System.out.println("###############################My Custom Mediator###########################");
            getNext().receive(carbonMessage, carbonCallback);
            return false;
        }
    }
}
