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

package org.wso2.carbon.gateway.core.inbound;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.gateway.core.service.GatewayServiceComponent;
import org.wso2.carbon.kernel.startupresolver.RequiredCapabilityListener;


@Component(
        name = "org.wso2.carbon.gateway.core.inbound.InboundServiceComponent",
        immediate = true,
        property = {
                "capability-name=org.wso2.carbon.gateway.core.inbound.InboundEPProvider",
                "component-key=inbound-provider"
        }
)
public class InboundServiceComponent implements RequiredCapabilityListener {

    private static final Logger logger = LoggerFactory.getLogger(InboundServiceComponent.class);

    @Activate
    protected void activate(BundleContext bundleContext) {
        // Nothing to do
    }

    @Override
    public void onAllRequiredCapabilitiesAvailable() {
      if (logger.isDebugEnabled()) {
          logger.debug("All Inbound Providers available");
      }

      logger.info("#############  All Inbound Providers available");
      GatewayServiceComponent.setInboundsReady(true);

    }

    @Reference(
            name = "InboundEndpoint-Service",
            service = InboundEPProvider.class,
            cardinality = ReferenceCardinality.OPTIONAL,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "removeInboundProvider"
    )
    protected void addInboundProvider(InboundEPProvider inboundEndpointProvider) {
        InboundEPProviderRegistry.getInstance().registerInboundEPProvider(inboundEndpointProvider);
    }

    protected void removeInboundProvider(InboundEPProvider inboundEndpointProvider) {
        InboundEPProviderRegistry.getInstance().unregisterInboundEPProvider(inboundEndpointProvider);
    }

}
