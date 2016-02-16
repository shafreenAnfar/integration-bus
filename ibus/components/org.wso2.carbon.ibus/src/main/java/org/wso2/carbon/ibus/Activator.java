/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package org.wso2.carbon.ibus;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.ibus.mediation.cheetah.CheetahMessageProcessor;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.DispatcherRegistry;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.deployer.InboundEndpointDeployer;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.protocols.http.HTTPInboundEPDispatcher;
import org.wso2.carbon.messaging.ArtifactDeployer;
import org.wso2.carbon.messaging.CarbonMessageProcessor;

import java.io.File;

/**
 * OSGi Bundle Activator of the Cheetah Carbon component.
 */
public class Activator implements BundleActivator {

    private static final Logger log = LoggerFactory.getLogger(Activator.class);

    public static final String CHEETAH_DSL_CONFIGS_DIRECTORY = "conf" + File.separator + "cheetah"
                                                               + File.separator + "dsl";

    public void start(BundleContext bundleContext) throws Exception {

        try {
            log.info("Starting Cheetah...!");

            //Creating the processor and registering the service
            CheetahMessageProcessor engine = new CheetahMessageProcessor();
            bundleContext.registerService(CarbonMessageProcessor.class, engine, null);
            bundleContext.registerService(ArtifactDeployer.class, InboundEndpointDeployer.getInstance(), null);

            //Registering dispatchers
            DispatcherRegistry.getInstance().registerDispatcher("http", HTTPInboundEPDispatcher.getInstance());

        } catch (Exception ex) {
            String msg = "Error while loading Cheetah";
            log.error(msg, ex);
            throw new RuntimeException(msg, ex);
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {

    }


}
