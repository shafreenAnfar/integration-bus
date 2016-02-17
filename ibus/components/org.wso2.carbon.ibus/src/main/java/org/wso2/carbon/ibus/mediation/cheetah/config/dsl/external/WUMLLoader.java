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
package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.ibus.mediation.cheetah.config.CheetahConfigRegistry;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.JavaConfigurationBuilder;

public class WUMLLoader {

    private static final Logger log = LoggerFactory.getLogger(WUMLLoader.class);

    public static void loadDSL(WUMLConfigurationBuilder wumlConfigurationBuilder) {
        if (log.isDebugEnabled()) {
            log.debug("Loading Java DSL..");
        }
        // Call the DSL
        WUMLConfigurationBuilder.IntegrationFlow config = wumlConfigurationBuilder.configure();

        // Register the configuration
        CheetahConfigRegistry.getInstance().addESBConfig(config.getEsbConfigHolder());

    }
}
