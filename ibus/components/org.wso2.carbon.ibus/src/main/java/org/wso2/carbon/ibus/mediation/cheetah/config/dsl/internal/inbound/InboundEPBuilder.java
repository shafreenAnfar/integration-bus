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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.inbound;

import org.wso2.carbon.ibus.mediation.cheetah.config.ESBConfigHolder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.JavaConfigurationBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.flow.PipelineBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.InboundEndpoint;

/**
 * A Builder class for Inbound Endpoint
 */
public class InboundEPBuilder {

    ESBConfigHolder parentConfig;
    String pipeline;
    JavaConfigurationBuilder.IntegrationFlow integrationFlow;


    public static InboundEPBuilder inboundEndpoint(String name, ESBConfigHolder parentConfig,
                                                   JavaConfigurationBuilder.IntegrationFlow integrationFlow,
                                                   InboundEndpoint inboundEndpoint) {
        inboundEndpoint.setName(name);
        parentConfig.setInboundEndpoint(inboundEndpoint);

        return new InboundEPBuilder(parentConfig, integrationFlow);
    }

    private InboundEPBuilder(ESBConfigHolder parentConfig,
                             JavaConfigurationBuilder.IntegrationFlow integrationFlow) {
        this.parentConfig = parentConfig;
        this.integrationFlow = integrationFlow;
    }



    public PipelineBuilder pipeline(String pipeline) {
        this.pipeline = pipeline;

        if (parentConfig.getInboundEndpoint() != null) {
            parentConfig.getInboundEndpoint().setPipeline(pipeline);
        }
        return integrationFlow.pipeline(pipeline);
    }

}
