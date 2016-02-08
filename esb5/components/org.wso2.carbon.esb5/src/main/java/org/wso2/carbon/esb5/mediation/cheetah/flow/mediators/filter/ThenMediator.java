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

package org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.filter;

import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.Mediator;
import org.wso2.carbon.messaging.CarbonCallback;
import org.wso2.carbon.messaging.CarbonMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Then Mediator
 */
public class ThenMediator implements Mediator {

    private List<Mediator> mediators = new ArrayList<>();

    public void addMediator(Mediator mediator) {
        mediators.add(mediator);
    }

    public List<Mediator> getMediators() {
        return mediators;
    }


    @Override
    public CarbonCallback receive(CarbonMessage cMsg, CarbonCallback callback)
            throws Exception {
        for (Mediator mediator : mediators) {
            try {
                mediator.receive(cMsg, callback);
            } catch (Exception e) {
                //TODO: Exception Handling
                return callback;
            }
        }
        return callback;
    }


}
