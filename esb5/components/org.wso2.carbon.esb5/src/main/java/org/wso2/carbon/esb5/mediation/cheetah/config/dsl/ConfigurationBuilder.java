/*
 * Copyright (c) 2015, WSO2 Inc. (http://wso2.com) All Rights Reserved.
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

package org.wso2.carbon.esb5.mediation.cheetah.config.dsl;


import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.call.CallMediator;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.call.builder.CallMediatorBuilder;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.headerrouter.builder.HeaderBasedRouterBuilder;
import org.wso2.carbon.esb5.mediation.cheetah.flow.sequence.builder.SequenceBuilder;
import org.wso2.carbon.esb5.mediation.cheetah.inbound.protocols.http.builder.HTTPInboundEPBuilder;
import org.wso2.carbon.esb5.mediation.cheetah.outbound.builder.OutboundEndpointBuilder;

/**
 * A class that used to create entire configuration.Anyone can extend this and overwrite configure method with
 * relevant configuration
 */
public abstract class ConfigurationBuilder {


    public abstract void configure();


    public SequenceBuilder sequence(String name) {
        return SequenceBuilder.sequence(name);
    }

    public OutboundEndpointBuilder outboundEndpoint(String name, String epr) {
        return OutboundEndpointBuilder.outboundEndpoint(name, epr);
    }

    public HTTPInboundEPBuilder httpInboundEndpoint(String name, int port) {
        return HTTPInboundEPBuilder.httpInboundEndpoint(name, port);
    }

    public HeaderBasedRouterBuilder headerBasedRouter() {
        return HeaderBasedRouterBuilder.headerbasedrouter();
    }

    public CallMediator call(String key) {
        return CallMediatorBuilder.call(key);
    }

}
