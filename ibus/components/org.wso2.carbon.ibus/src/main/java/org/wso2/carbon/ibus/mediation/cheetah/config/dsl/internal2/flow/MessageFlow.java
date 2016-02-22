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
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow.Message;
import org.wso2.carbon.ibus.mediation.cheetah.flow.Pipeline;

/**
 * Representation of MessageFlow
 */
public class MessageFlow {

    private String name;

    private Message message;


    public MessageFlow(String name, ESBConfigHolder esbConfigHolder) {
        this.name = name;
        message = new Message(name, esbConfigHolder);
    }

    public Message getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
