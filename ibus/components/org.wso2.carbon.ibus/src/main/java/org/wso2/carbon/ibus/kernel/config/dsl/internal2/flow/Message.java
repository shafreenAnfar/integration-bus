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

package org.wso2.carbon.ibus.kernel.config.dsl.internal2.flow;


import org.wso2.carbon.ibus.kernel.config.ESBConfigHolder;
import org.wso2.carbon.ibus.kernel.config.dsl.internal2.flow.mediators.filter.FilterMediatorBuilder;
import org.wso2.carbon.ibus.kernel.flow.MediatorCollection;
import org.wso2.carbon.ibus.kernel.flow.Pipeline;
import org.wso2.carbon.ibus.kernel.flow.mediators.builtin.FlowControllers.filter.Source;
import org.wso2.carbon.ibus.kernel.flow.mediators.builtin.Manipulators.CallMediator;
import org.wso2.carbon.ibus.kernel.flow.mediators.builtin.Manipulators.RespondMediator;
import org.wso2.carbon.ibus.kernel.outbound.OutboundEndpoint;

import java.util.regex.Pattern;


/**
 * A class which represents the Message in a DSL
 */
public class Message {

    private String name;

    private Pipeline pipeline;

    private ESBConfigHolder esbConfigHolder;

    protected MediatorCollection mediatorCollection;

    public Message(String name, MediatorCollection mediatorCollection) {
        this.name = name;
        this.mediatorCollection = mediatorCollection;
    }

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
        if (pipeline != null) {
            pipeline.addMediator(callMediator);
        } else if (mediatorCollection != null) {
            mediatorCollection.addMediator(callMediator);
        }

        return this;
    }

    public FilterMediatorBuilder.ThenMediatorBuilder filter(Source source, Pattern pattern) {
        if (pipeline != null) {
            return FilterMediatorBuilder.filter(source, pattern, this, pipeline.getMediators());
        } else if (mediatorCollection != null) {
            return FilterMediatorBuilder.filter(source, pattern, this, mediatorCollection);
        }
        return null;
    }

    public Message respond() {
        RespondMediator respondMediator = new RespondMediator();
        if (pipeline != null) {
            pipeline.addMediator(respondMediator);
        } else if (mediatorCollection != null) {
            mediatorCollection.addMediator(respondMediator);
        }
        return this;
    }


}
