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


import org.wso2.carbon.esb5.mediation.cheetah.config.CheetahConfigRegistry;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.Mediator;
import org.wso2.carbon.esb5.mediation.cheetah.flow.sequence.FlowController;
import org.wso2.carbon.esb5.mediation.cheetah.flow.sequence.Sequence;

/**
 * A class responsible for elseDefault the Sequence
 */
public class SequenceBuilder {

    private Sequence sequence;

    public static SequenceBuilder sequence(String name) {
        return new SequenceBuilder(name);
    }

    private SequenceBuilder(String name) {
        sequence = new Sequence(name);

    }

    public SequenceBuilder flowController(FlowController flowController) {
        sequence.addMediator(flowController);
        CheetahConfigRegistry.getInstance().registerSequence(sequence);
        return this;
    }

    public SequenceBuilder mediate(Mediator mediator) {
        sequence.addMediator(mediator);
        CheetahConfigRegistry.getInstance().registerSequence(sequence);
        return this;
    }

}
