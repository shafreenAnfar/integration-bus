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

package org.wso2.carbon.esb5.mediation.cheetah.outbound.builder;


import org.wso2.carbon.esb5.mediation.cheetah.config.CheetahConfigRegistry;
import org.wso2.carbon.esb5.mediation.cheetah.outbound.OutboundEndpoint;

/**
 * A Builder class for OutBound Endpoint
 */
public class OutboundEndpointBuilder {


    private OutboundEndpoint outboundEndpoint;

    public static OutboundEndpointBuilder outboundEndpoint(String name, String epr) {
        return new OutboundEndpointBuilder(name, epr);
    }

    private OutboundEndpointBuilder(String name, String epr) {
        outboundEndpoint = new OutboundEndpoint(name, epr);
        CheetahConfigRegistry.getInstance().registerOutboundEndpoint(outboundEndpoint.getName(), outboundEndpoint);
    }

    public OutboundEndpointBuilder timeOut(int value) {
        outboundEndpoint.setTimeOut(value);
        CheetahConfigRegistry.getInstance().registerOutboundEndpoint(outboundEndpoint.getName(), outboundEndpoint);
        return this;
    }


}
