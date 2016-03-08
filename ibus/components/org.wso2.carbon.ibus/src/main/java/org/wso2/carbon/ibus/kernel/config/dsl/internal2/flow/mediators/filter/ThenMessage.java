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

package org.wso2.carbon.ibus.kernel.config.dsl.internal2.flow.mediators.filter;

import org.wso2.carbon.ibus.kernel.config.dsl.internal2.flow.Message;
import org.wso2.carbon.ibus.kernel.flow.MediatorCollection;
import org.wso2.carbon.ibus.kernel.flow.mediators.builtin.Manipulators.CallMediator;
import org.wso2.carbon.ibus.kernel.outbound.OutboundEndpoint;

/**
 * A class that represents otherwise branch  messages inside Filter Mediator
 */
public class ThenMessage extends Message {

    private FilterMediatorBuilder.OtherwiseMediatorBuilder otherwiseMediatorBuilder;

    public ThenMessage(String name,
                       MediatorCollection mediatorCollection,
                       FilterMediatorBuilder.OtherwiseMediatorBuilder otherwiseMediatorBuilder) {
        super(name, mediatorCollection);
        this.otherwiseMediatorBuilder = otherwiseMediatorBuilder;

    }

    public FilterMediatorBuilder.OtherwiseMediatorBuilder end() {
        return otherwiseMediatorBuilder;
    }

    @Override
    public ThenMessage call(OutboundEndpoint outboundEndpoint) {
        CallMediator callMediator = new CallMediator(outboundEndpoint);
        mediatorCollection.addMediator(callMediator);
        return this;
    }
}
