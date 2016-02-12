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

package org.wso2.carbon.esb5.mediation.cheetah.config.dsl.flow.mediators;

import org.wso2.carbon.esb5.mediation.cheetah.config.dsl.flow.PipelineBuilder;
import org.wso2.carbon.esb5.mediation.cheetah.flow.Mediator;
import org.wso2.carbon.esb5.mediation.cheetah.flow.Pipeline;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.filter.Condition;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.filter.FilterMediator;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.filter.Scope;
import org.wso2.carbon.esb5.mediation.cheetah.flow.mediators.filter.Source;

import java.util.regex.Pattern;

/**
 * A class that responsible for create FilterMediator
 */
public class FilterMediatorBuilder {

    public static ThenMediatorBuilder filter(Condition condition, Pipeline pipeline, PipelineBuilder sequenceBuilder) {
        return new ThenMediatorBuilder(new FilterMediator(condition), pipeline, sequenceBuilder);
    }


    public static FilterMediator filter(Condition condition) {
        return new FilterMediator(condition);
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
        private PipelineBuilder sequenceBuilder;
        private Pipeline pipeline;


        public ThenMediatorBuilder(FilterMediator filterMediator, Pipeline pipeline, PipelineBuilder sequenceBuilder) {
            this.filterMediator = filterMediator;
            this.sequenceBuilder = sequenceBuilder;
            this.pipeline = pipeline;
        }

        public OtherwiseMediatorBuilder then(Mediator... mediators) {
            filterMediator.then(mediators);
            return new OtherwiseMediatorBuilder(filterMediator, pipeline, sequenceBuilder);


        }


    }

    /**
     * A util class
     */
    public static class OtherwiseMediatorBuilder {

        private FilterMediator filterMediator;
        private PipelineBuilder sequenceBuilder;
        private Pipeline pipeline;

        public OtherwiseMediatorBuilder(FilterMediator filterMediator, Pipeline pipeline,
                                        PipelineBuilder sequenceBuilder) {
            this.filterMediator = filterMediator;
            this.sequenceBuilder = sequenceBuilder;
            this.pipeline = pipeline;
        }


        public PipelineBuilder otherwise(Mediator... mediators) {
            filterMediator.otherwise(mediators);
            pipeline.addMediator(filterMediator);
            return sequenceBuilder;
        }

    }

}
