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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow.mediators.filter;

import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal2.flow.Message;
import org.wso2.carbon.ibus.mediation.cheetah.flow.MediatorCollection;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Condition;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.FilterMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Scope;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Source;

import java.util.regex.Pattern;

/**
 * A class that responsible for create FilterMediator
 */
public class FilterMediatorBuilder {


    public static ThenMediatorBuilder filter(Source source, Pattern pattern, Message message,
                                             MediatorCollection mediatorCollection) {
        FilterMediator filterMediator = new FilterMediator(source, pattern);
        mediatorCollection.addMediator(filterMediator);
        return new ThenMediatorBuilder(filterMediator, message);
    }


    public static Condition condition(Source source, Pattern pattern) {
        return new Condition(source, pattern);
    }

    public static Source source(String value) {
        return new Source(value);
    }

    public static Pattern pattern(String regex) {
        return Pattern.compile(regex);
    }


    /**
     * A util class
     */
    public static class ThenMediatorBuilder {

        private FilterMediator filterMediator;
        private Message message;


        public ThenMediatorBuilder(FilterMediator filterMediator, Message message) {
            this.filterMediator = filterMediator;
            this.message = message;

        }

        public ThenMessage then() {
            MediatorCollection mediatorCollection = new MediatorCollection();
            OtherwiseMediatorBuilder otherwiseMediatorBuilder = new OtherwiseMediatorBuilder(filterMediator, message);
            ThenMessage filterMessage = new ThenMessage("thenMessage", mediatorCollection, otherwiseMediatorBuilder);
            filterMediator.addthenMediators(mediatorCollection);
            return filterMessage;
        }
    }

    /**
     * A util class
     */
    public static class OtherwiseMediatorBuilder {

        private FilterMediator filterMediator;

        private Message message;


        public OtherwiseMediatorBuilder(FilterMediator filterMediator,
                                        Message message) {
            this.filterMediator = filterMediator;
            this.message = message;

        }


        public OtherwiseMessage otherwise() {
            MediatorCollection mediatorCollection = new MediatorCollection();
            OtherwiseMessage otherwiseMessage = new OtherwiseMessage("otherwiseMessage", mediatorCollection, message);
            filterMediator.addotherwiseMediators(mediatorCollection);
            return otherwiseMessage;
        }

    }

}
