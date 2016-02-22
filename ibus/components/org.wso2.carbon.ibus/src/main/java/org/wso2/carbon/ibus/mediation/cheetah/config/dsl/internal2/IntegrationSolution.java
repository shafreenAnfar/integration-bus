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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2;


import org.wso2.carbon.ibus.mediation.cheetah.config.CheetahConfigRegistry;
import org.wso2.carbon.ibus.mediation.cheetah.config.ESBConfigHolder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow.Message;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow.MessageFlow;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.InboundEndpoint;
import org.wso2.carbon.ibus.mediation.cheetah.outbound.OutboundEndpoint;

/**
 * A class that represents the IntegrationSolution
 */
public abstract class IntegrationSolution {

    private ESBConfigHolder esbConfigHolder;


    public abstract void configure();


    private String getClassName() {
        return this.getClass().getName();
    }


    public OutboundEndpoint defineHTTPOutboundEndpoint(OutboundEndpoint outboundEndpoint) {

        getEsbConfigHolder().addOutboundEndpoint(outboundEndpoint);
        return outboundEndpoint;
    }

    public MessageFlow defineMessageFlow(String name) {
        return new MessageFlow(name, esbConfigHolder);
    }

    public MessageTunnel receiveFrom(InboundEndpoint inboundEndpoint) {
        return new MessageTunnel(inboundEndpoint);
    }


    private ESBConfigHolder getEsbConfigHolder() {
        if (esbConfigHolder == null && !(CheetahConfigRegistry.getInstance().getESBConfig(getClassName()) == null)) {
            esbConfigHolder = CheetahConfigRegistry.getInstance().getESBConfig(getClassName());
            return esbConfigHolder;
        } else {
            esbConfigHolder = new ESBConfigHolder(getClassName());
            return esbConfigHolder;
        }

    }

    /**
     * Message Tunnel which maps the incoming message to Pipeline
     */
    public class MessageTunnel {

        private InboundEndpoint inboundEndpoint;


        private MessageTunnel(InboundEndpoint inboundEndpoint) {
            this.inboundEndpoint = inboundEndpoint;
        }

        public MessageFlow directTo(MessageFlow messageFlow) {
            inboundEndpoint.setPipeline(messageFlow.getName());
            return messageFlow;
        }

    }


}
