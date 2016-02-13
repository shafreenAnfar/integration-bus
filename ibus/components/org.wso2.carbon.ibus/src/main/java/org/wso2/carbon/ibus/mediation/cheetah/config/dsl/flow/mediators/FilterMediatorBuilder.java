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

import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Condition;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.FilterMediator;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Scope;
import org.wso2.carbon.ibus.mediation.cheetah.flow.mediators.filter.Source;

import java.util.regex.Pattern;

/**
 * A class that responsible for create FilterMediator
 */
public class FilterMediatorBuilder {


    public static ThenMediatorBuilder filter(Condition condition, MediatorCollectionBuilder mediatorCollectionBuilder) {
        return new ThenMediatorBuilder(new FilterMediator(condition), mediatorCollectionBuilder);
    }


    public static Condition condition(Source source, Pattern pattern) {
        return new Condition(source, pattern);
    }

    public static Source source(String key, Scope scope) {
        return new Source(scope, key);
    }

    public static Pattern pattern(String regex) {
        return Pattern.compile(regex);
    }


    /**
     * A util class
     */
    public static class ThenMediatorBuilder {

        private FilterMediator filterMediator;
        private MediatorCollectionBuilder mediatorCollectionBuilder;


        public ThenMediatorBuilder(FilterMediator filterMediator, MediatorCollectionBuilder mediatorCollectionBuilder) {
            this.filterMediator = filterMediator;
            this.mediatorCollectionBuilder = mediatorCollectionBuilder;
        }

        public OtherwiseMediatorBuilder then(MediatorCollectionBuilder mediatorCollection) {
            filterMediator.addthenMediators(mediatorCollection.getMediatorCollection());


            return new OtherwiseMediatorBuilder(filterMediator, mediatorCollectionBuilder);
        }
    }

    /**
     * A util class
     */
    public static class OtherwiseMediatorBuilder {

        private FilterMediator filterMediator;
        private MediatorCollectionBuilder mediatorCollectionBuilder;


        public OtherwiseMediatorBuilder(FilterMediator filterMediator,
                                        MediatorCollectionBuilder mediatorCollectionBuilder) {
            this.filterMediator = filterMediator;
            this.mediatorCollectionBuilder = mediatorCollectionBuilder;

        }


        public MediatorCollectionBuilder otherwise(MediatorCollectionBuilder collectionBuilder) {
            filterMediator.addotherwiseMediators(collectionBuilder.getMediatorCollection());
            mediatorCollectionBuilder.getMediatorCollection().addMediator(filterMediator);
            return mediatorCollectionBuilder;
        }

    }

}
