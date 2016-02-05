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


import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.Mediator;
import org.wso2.carbon.messaging.CarbonCallback;
import org.wso2.carbon.messaging.CarbonMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * A Class representing collection of Mediators
 */
public class Sequence {


    private String name;
    private List<Mediator> mediators;

    public Sequence(String name) {
        this.mediators = new ArrayList<>();
        this.name = name;
    }

    public void addMediator(Mediator mediator) {
        mediators.add(mediator);
    }

    public List<Mediator> getMediators() {
        return mediators;
    }

    public String getName() {
        return name;
    }

    public boolean receive(CarbonMessage cMsg, CarbonCallback callback) {
        for (Mediator mediator : mediators) {
            try {
                mediator.receive(cMsg, callback);
            } catch (Exception e) {
                //TODO: Exception Handling
                return false;
            }
        }
        return true;
    }

}
