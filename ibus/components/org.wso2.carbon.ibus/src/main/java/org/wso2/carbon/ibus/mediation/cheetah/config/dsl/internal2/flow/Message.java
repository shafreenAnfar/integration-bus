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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow;


import org.wso2.carbon.ibus.mediation.cheetah.config.ESBConfigHolder;
import org.wso2.carbon.ibus.mediation.cheetah.flow.Pipeline;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.CallMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.RespondMediator;
import org.wso2.carbon.ibus.mediation.cheetah.outbound.OutboundEndpoint;

public class Message {

    private String name;

    private Pipeline pipeline;

    private ESBConfigHolder esbConfigHolder;

    public Message(String name, ESBConfigHolder esbConfigHolder) {
        this.name = name;
        this.esbConfigHolder = esbConfigHolder;
        pipeline = new Pipeline(name);
        this.esbConfigHolder.addPipeline(pipeline);
    }

    public Message log() {
        return this;
    }

    public Message call(OutboundEndpoint outboundEndpoint) {
        CallMediator callMediator = new CallMediator(outboundEndpoint);
        pipeline.addMediator(callMediator);
        return this;
    }

    public Message respond() {
        RespondMediator respondMediator = new RespondMediator();
        pipeline.addMediator(respondMediator);
        return this;
    }


}
