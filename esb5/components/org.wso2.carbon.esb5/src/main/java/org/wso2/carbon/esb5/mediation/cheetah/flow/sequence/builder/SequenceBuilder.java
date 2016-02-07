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

package org.wso2.carbon.esb5.mediation.cheetah.flow.sequence.builder;


import org.wso2.carbon.esb5.mediation.cheetah.config.dsl.ESBConfig;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.call.builder.CallMediatorBuilder;
import org.wso2.carbon.esb5.mediation.cheetah.flow.sequence.Sequence;

/**
 * A class Sequence Builder
 */
public class SequenceBuilder {

    private Sequence sequence;

    public static SequenceBuilder sequence(String name, ESBConfig parentConfig) {
        return new SequenceBuilder(name, parentConfig);
    }

    private SequenceBuilder(String name, ESBConfig parentConfig) {
        sequence = new Sequence(name);
        parentConfig.addSequence(sequence);
    }

    public SequenceBuilder call(String endpointKey) {
        sequence.addMediator(CallMediatorBuilder.call(endpointKey));
        return this;
    }


}
