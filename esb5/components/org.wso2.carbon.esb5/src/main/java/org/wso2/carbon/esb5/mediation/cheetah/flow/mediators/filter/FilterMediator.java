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

package org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.esb5.mediation.cheetah.flow.FlowController;
import org.wso2.carbon.esb5.mediation.cheetah.flow.FlowControllerCallback;
import org.wso2.carbon.esb5.mediation.cheetah.flow.Mediator;
import org.wso2.carbon.esb5.mediation.cheetah.flow.MediatorCollection;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.filter.evaluator.Evaluator;
import org.wso2.carbon.messaging.CarbonCallback;
import org.wso2.carbon.messaging.CarbonMessage;

/**
 * Filter Mediator
 */
public class FilterMediator implements FlowController {

    private static final Logger log = LoggerFactory.getLogger(FilterMediator.class);

    private MediatorCollection childThenMediatorList = new MediatorCollection();
    private MediatorCollection childOtherwiseMediatorList = new MediatorCollection();

    private Condition condition;


    public FilterMediator(Condition condition) {
        this.condition = condition;
    }

    public FilterMediator then(Mediator... mediators) {
        for (Mediator mediator : mediators) {
            childThenMediatorList.addMediator(mediator);
        }
      return  this;
    }

    public FilterMediator otherwise(Mediator... mediators) {
        for (Mediator mediator : mediators) {
            childOtherwiseMediatorList.addMediator(mediator);
        }
       return this;
    }

    @Override
    public void setNext(Mediator nextMediator) {

    }

    @Override
    public Mediator getNext() {
        return null;
    }

    @Override
    public boolean receive(CarbonMessage carbonMessage, CarbonCallback carbonCallback)
               throws Exception {

        if (condition.getSource().getScope().equals(Scope.Transport)) {

            if (Evaluator.isHeaderMatched(carbonMessage, condition)) {
                childThenMediatorList.getFirstMediator().
                           receive(carbonMessage, new FlowControllerCallback(carbonCallback, this));
            } else {
                childOtherwiseMediatorList.getFirstMediator().
                           receive(carbonMessage, new FlowControllerCallback(carbonCallback, this));
            }
        }

        return true;
    }

}
