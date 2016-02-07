/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
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

package org.wso2.carbon.esb5.mediation.cheetah.inbound.builder;

import org.wso2.carbon.esb5.mediation.cheetah.config.ESBConfigHolder;
import org.wso2.carbon.esb5.mediation.cheetah.inbound.InboundEndpoint;
import org.wso2.carbon.esb5.mediation.cheetah.inbound.protocols.http.builder.HTTPInboundEPBuilder;

/**
 * A Builder class for Inbound Endpoint
 */
public class InboundEPBuilder {

    String name;
    InboundEndpoint inboundEndpoint;
    ESBConfigHolder parentConfig;
    String sequence;

    public static InboundEPBuilder inboundEndpoint(String name, ESBConfigHolder parentConfig) {
        return new InboundEPBuilder(name, parentConfig);
    }

    private InboundEPBuilder(String name, ESBConfigHolder parentConfig) {
        this.name = name;
        this.parentConfig = parentConfig;
    }

    public InboundEPBuilder http(HTTPInboundEPBuilder.Port port, HTTPInboundEPBuilder.Context context) {

        inboundEndpoint =
                HTTPInboundEPBuilder.http(name, port, context).getHttpInboundEP();
        inboundEndpoint.setSequence(sequence);
        parentConfig.setInboundEndpoint(inboundEndpoint);
        return this;
    }

    public void callSequence(String sequence) {
        this.sequence = sequence;

        if (inboundEndpoint != null) {
            inboundEndpoint.setSequence(sequence);
        }
    }

}
