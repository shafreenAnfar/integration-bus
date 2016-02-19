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
package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.flow;


import org.wso2.carbon.ibus.mediation.cheetah.flow.Mediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.CallMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.EnrichMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.LogMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.RespondMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.TransformMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.FilterMediator;

/**
 * Factory class to generate mediators according to the provided type
 */
public class MediatorFactory {

    /**
     * Factory method to get a mediator object from name
     * @param mediatorType type of the mediator
     * @return mediator
     */

    public static Mediator getMediator(MediatorType mediatorType, String configs) {
        Mediator mediator = null;
        switch (mediatorType) {
            case call:
                mediator = new CallMediator(configs);
                break;

            case filter:
                mediator = new FilterMediator();
                break;

            case respond:
                mediator = new RespondMediator();
                break;
            case log:
                mediator = new LogMediator(configs);
                break;
            case enrich:
                mediator = new EnrichMediator();
                break;
            case transform:
                mediator = new TransformMediator();
                break;
            default:
                break;
        }
        return mediator;
    }

}
