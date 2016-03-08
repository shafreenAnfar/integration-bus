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

package org.wso2.carbon.ibus.kernel.config.dsl.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.ibus.kernel.config.ConfigRegistry;
import org.wso2.carbon.ibus.kernel.config.ESBConfigHolder;
import org.wso2.carbon.ibus.kernel.config.dsl.internal2.IntegrationSolution;

/**
 * This loads the JAVA DSLs
 */
public class DSLLoader {

    private static final Logger log = LoggerFactory.getLogger(DSLLoader.class);

    /**
     * Load Java DSL configuration file in type1
     *
     * @param javaConfigurationBuilder Java DSL
     */
    public static void loadDSLType1(JavaConfigurationBuilder javaConfigurationBuilder) {
        if (log.isDebugEnabled()) {
            log.debug("Loading Type 1 Java DSL ..");
        }
        // Call the DSL
        ESBConfigHolder esbConfigHolder =
                javaConfigurationBuilder.configure().getEsbConfigHolder();

        // Register the configuration
        ConfigRegistry.getInstance().addESBConfig(esbConfigHolder);

    }

    /**
     * Load Java DSL configuration file in type2
     *
     * @param integrationSolution Java DSL
     */
    public static void loadDSLType2(IntegrationSolution integrationSolution) {
        if (log.isDebugEnabled()) {
            log.debug("Loading Type 2 Java DSL ..");
        }
        // Call the DSL
       ESBConfigHolder esbConfigHolder = integrationSolution.configure();

        // Register the configuration
       ConfigRegistry.getInstance().addESBConfig(esbConfigHolder);

    }

}
