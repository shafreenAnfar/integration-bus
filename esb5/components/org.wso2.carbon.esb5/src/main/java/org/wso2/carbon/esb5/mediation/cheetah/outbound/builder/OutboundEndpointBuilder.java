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


import org.wso2.carbon.esb5.mediation.cheetah.config.dsl.ESBConfig;
import org.wso2.carbon.esb5.mediation.cheetah.outbound.OutboundEndpoint;
import org.wso2.carbon.esb5.mediation.cheetah.outbound.protocol.http.builder.HTTPOutboundEPBuilder;

/**
 * A Builder class for OutBound Endpoint
 */
public class OutboundEndpointBuilder {

    private OutboundEndpoint outboundEndpoint;
    private String name;
    private ESBConfig parentConfig;
    private int timeout;

    public static OutboundEndpointBuilder outboundEndpoint(String name, ESBConfig parentConfig) {
        return new OutboundEndpointBuilder(name, parentConfig);
    }

    private OutboundEndpointBuilder(String name, ESBConfig parentConfig) {
        this.name = name;
        this.parentConfig = parentConfig;
        outboundEndpoint = null;
    }

    public OutboundEndpointBuilder timeOut(int timeout) {
        this.timeout = timeout;
        if (outboundEndpoint != null) {
            outboundEndpoint.setTimeOut(timeout);
        }
        return this;
    }

    public OutboundEndpointBuilder http(HTTPOutboundEPBuilder.URI uri) {
        outboundEndpoint =
                HTTPOutboundEPBuilder.http(name, uri).getHttpOutboundEndpoint();
        outboundEndpoint.setTimeOut(timeout);
        parentConfig.addOutboundEndpoint(outboundEndpoint);
        return this;
    }


}
