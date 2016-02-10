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

package org.wso2.carbon.esb5.mediation.cheetah.flow.sequence;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.esb5.mediation.cheetah.flow.Mediator;
import org.wso2.carbon.esb5.mediation.cheetah.flow.MediatorCollection;
import org.wso2.carbon.messaging.CarbonCallback;
import org.wso2.carbon.messaging.CarbonMessage;

/**
 * A Class representing collection of Mediators
 */
public class Sequence {

    private String name;

    MediatorCollection mediators;

    private static final Logger log = LoggerFactory.getLogger(Sequence.class);


    public Sequence() {
        mediators = new MediatorCollection();
    }

    public boolean receive(CarbonMessage carbonMessage, CarbonCallback carbonCallback) {

        try {
            mediators.getFirstMediator().receive(carbonMessage, carbonCallback);
        } catch (Exception e) {
            log.error("Error while mediating", e);
        }
        return true;
    }

    public Sequence(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addMediator(Mediator mediator) {
        mediators.addMediator(mediator);
    }


}
