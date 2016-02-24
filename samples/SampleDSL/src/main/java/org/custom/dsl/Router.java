/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 * <p>
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.custom.dsl;

import org.wso2.carbon.ibus.mediation.cheetah.config.ESBConfigHolder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.IntegrationSolution;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow.Message;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Scope;
import org.wso2.carbon.ibus.mediation.cheetah.outbound.OutboundEndpoint;

import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow.mediators.filter.FilterMediatorBuilder.pattern;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow.mediators.filter.FilterMediatorBuilder.source;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.inbound.http.HTTPInboundEPBuilder.context;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.inbound.http.HTTPInboundEPBuilder.http;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.inbound.http.HTTPInboundEPBuilder.port;
import static org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.outbound.http.HTTPOutboundEPBuilder.uri;


/**
 * Sample Internal DSL in  method 2
 */
public class Router extends IntegrationSolution {

    @Override
    public ESBConfigHolder configure() {

        OutboundEndpoint outboundEndpoint = defineHTTPOutboundEndpoint("outbound1",
                                                                       uri("http://localhost:8280/backend1"));
        OutboundEndpoint outboundEndpoint2 = defineHTTPOutboundEndpoint("outbound1",
                                                                        uri("http://localhost:8280/backend2"));

        Message message = receiveFrom(http(port(7777), context("/router"))).
                   directTo(defineMessageFlow("pipeline1")).getMessage();

        message.
                   filter(source("$header.routeId"), pattern("r1")).
                   then().
                   call(outboundEndpoint).end().
                   otherwise().
                   call(outboundEndpoint2).end().
                   respond();

        return getConfiguration();
    }
}
