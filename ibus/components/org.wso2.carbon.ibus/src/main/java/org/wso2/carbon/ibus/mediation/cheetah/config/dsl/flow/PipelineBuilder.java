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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.flow;


import org.wso2.carbon.ibus.mediation.cheetah.config.ESBConfigHolder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.flow.mediators.FilterMediatorBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.flow.mediators.MediatorCollectionBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.flow.mediators.RespondMediatorBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.flow.Mediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.Pipeline;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.CallMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Condition;


/**
 * A class Pipeline Builder
 */
public class PipelineBuilder {

    private Pipeline pipeline;

    private MediatorCollectionBuilder mediatorCollectionBuilder;

    public static PipelineBuilder pipeline(String name, ESBConfigHolder parentConfig) {
        return new PipelineBuilder(name, parentConfig);
    }

    private PipelineBuilder(String name, ESBConfigHolder parentConfig) {
        mediatorCollectionBuilder = new MediatorCollectionBuilder();
        pipeline = new Pipeline(name, mediatorCollectionBuilder.getMediatorCollection());
        parentConfig.addPipeline(pipeline);
    }

    public MediatorCollectionBuilder call(String endpointKey) {
        mediatorCollectionBuilder.getMediatorCollection().addMediator(new CallMediator(endpointKey));
        return mediatorCollectionBuilder;
    }

    public MediatorCollectionBuilder customMediator(Mediator mediator) {
        mediatorCollectionBuilder.getMediatorCollection().addMediator(mediator);
        return mediatorCollectionBuilder;
    }

    public void respond() {
        mediatorCollectionBuilder.getMediatorCollection().addMediator(RespondMediatorBuilder.respond());
    }


    public FilterMediatorBuilder.ThenMediatorBuilder filter(Condition condition) {
        return FilterMediatorBuilder.filter(condition, mediatorCollectionBuilder);
    }


}
