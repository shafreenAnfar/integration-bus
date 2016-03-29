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

package org.wso2.carbon.gateway.core.config.dsl.external;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.gateway.core.config.dsl.external.deployer.IFlowDeployer;
import org.wso2.carbon.gateway.core.inbound.ProviderRegistry;
import org.wso2.carbon.kernel.deployment.Deployer;

@Component(
        name = "org.wso2.carbon.gateway.core.config.dsl.external.ExternalDSLServiceComponent",
        immediate = true
)
public class ExternalDSLServiceComponent {


    @Activate
    protected void activate(BundleContext bundleContext) {
        bundleContext.registerService(Deployer.class, new IFlowDeployer(), null);

    }

    @Reference(
            name = "inbound-provider-registry-service",
            service = ProviderRegistry.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "removeInboundProviderRegistry"
    )
    protected void addInboundProviderRegistry(ProviderRegistry registry) {
    }

    protected void removeInboundProviderRegistry(ProviderRegistry registry) {
    }

    @Reference(
            name = "outbound-provider-registry-service",
            service = org.wso2.carbon.gateway.core.outbound.ProviderRegistry.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "removeOutboundProviderRegistry"
    )
    protected void addOutboundProviderRegistry(org.wso2.carbon.gateway.core.outbound.ProviderRegistry registry) {
    }

    protected void removeOutboundProviderRegistry(org.wso2.carbon.gateway.core.outbound.ProviderRegistry registry) {
    }

    @Reference(
            name = "mediator-provider-registry-service",
            service = org.wso2.carbon.gateway.core.flow.ProviderRegistry.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "removeMediatorProviderRegistry"
    )
    protected void addMediatorProviderRegistry(org.wso2.carbon.gateway.core.flow.ProviderRegistry registry) {
    }

    protected void removeMediatorProviderRegistry(org.wso2.carbon.gateway.core.flow.ProviderRegistry registry) {
    }

}
