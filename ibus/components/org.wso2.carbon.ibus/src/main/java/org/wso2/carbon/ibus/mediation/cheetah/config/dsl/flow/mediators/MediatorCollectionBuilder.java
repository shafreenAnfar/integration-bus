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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.flow.mediators;

import org.wso2.carbon.ibus.mediation.cheetah.flow.MediatorCollection;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.CallMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Condition;

/**
 * A class that responsible for build collection of mediators
 */
public class MediatorCollectionBuilder {


    private MediatorCollection mediatorCollection;


    public MediatorCollectionBuilder call(String endpointKey) {
        mediatorCollection.addMediator(new CallMediator(endpointKey));
        return this;
    }


    public MediatorCollectionBuilder respond() {
        mediatorCollection.addMediator(RespondMediatorBuilder.respond());
        return this;

    }


    public FilterMediatorBuilder.ThenMediatorBuilder filter(Condition condition) {
        return FilterMediatorBuilder.filter(condition, this);
    }

    public MediatorCollectionBuilder() {
        this.mediatorCollection = new MediatorCollection();
    }

    public MediatorCollection getMediatorCollection() {
        return mediatorCollection;
    }
}
