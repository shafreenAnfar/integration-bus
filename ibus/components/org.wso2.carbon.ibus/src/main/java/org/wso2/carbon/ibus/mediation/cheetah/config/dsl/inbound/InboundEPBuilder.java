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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.inbound;

import org.wso2.carbon.ibus.mediation.cheetah.config.ESBConfigHolder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.ConfigurationBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.flow.PipelineBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.inbound.http.HTTPInboundEPBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.InboundEndpoint;

/**
 * A Builder class for Inbound Endpoint
 */
public class InboundEPBuilder {

    InboundEndpoint inboundEndpoint;
    ESBConfigHolder parentConfig;
    String pipeline;
    ConfigurationBuilder.IntegrationFlow integrationFlow;

    public static InboundEPBuilder inboundEndpoint(ESBConfigHolder parentConfig,
                                                   ConfigurationBuilder.IntegrationFlow integrationFlow) {
        return new InboundEPBuilder(parentConfig, integrationFlow);
    }

    private InboundEPBuilder(ESBConfigHolder parentConfig,
                             ConfigurationBuilder.IntegrationFlow integrationFlow) {
        this.parentConfig = parentConfig;
        this.integrationFlow = integrationFlow;
    }

    public InboundEPBuilder http(String name, HTTPInboundEPBuilder.Port port, HTTPInboundEPBuilder.Context context) {

        inboundEndpoint =
                   HTTPInboundEPBuilder.http(name, port, context).getHttpInboundEP();
        inboundEndpoint.setPipeline(pipeline);
        parentConfig.setInboundEndpoint(inboundEndpoint);
        return this;
    }

    public InboundEPBuilder custom(InboundEndpoint inboundEndpoint) {
        this.inboundEndpoint = inboundEndpoint;
        inboundEndpoint.setPipeline(pipeline);
        parentConfig.setInboundEndpoint(inboundEndpoint);
        return this;
    }


    public PipelineBuilder pipeline(String pipeline) {
        this.pipeline = pipeline;

        if (inboundEndpoint != null) {
            inboundEndpoint.setPipeline(pipeline);
        }
        return integrationFlow.pipeline(pipeline);
    }

}
