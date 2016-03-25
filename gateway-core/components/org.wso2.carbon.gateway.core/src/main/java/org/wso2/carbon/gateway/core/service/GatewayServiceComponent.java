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

package org.wso2.carbon.gateway.core.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.gateway.core.ServiceContextHolder;
import org.wso2.carbon.gateway.core.config.dsl.internal.DSLLoader;
import org.wso2.carbon.gateway.core.config.dsl.internal.JavaConfigurationBuilder;
import org.wso2.carbon.gateway.core.config.dsl.internal2.IntegrationSolution;
import org.wso2.carbon.gateway.core.flow.MediatorProvider;
import org.wso2.carbon.gateway.core.flow.MediatorProviderRegistry;
import org.wso2.carbon.gateway.core.inbound.InboundEPProvider;
import org.wso2.carbon.gateway.core.inbound.InboundEPProviderRegistry;
import org.wso2.carbon.gateway.core.outbound.OutboundEPProvider;
import org.wso2.carbon.gateway.core.outbound.OutboundEPProviderRegistry;
import org.wso2.carbon.messaging.TransportSender;

import java.util.ArrayList;
import java.util.List;


/**
 * Service component for Gateway.
 */
@Component(
        name = "GatewayServiceComponent",
        immediate = true
)
public class GatewayServiceComponent {


    private static List<JavaConfigurationBuilder> earlyDSLs = new ArrayList<>();

    private static boolean isInboundsReady, isOutboundsReady, isMediatorsReady = true;

    @Reference(
            name = "transport-sender",
            service = TransportSender.class,
            cardinality = ReferenceCardinality.OPTIONAL,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "removeTransportSender"
    )
    protected void addTransportSender(TransportSender transportSender) {
        ServiceContextHolder.getInstance().addTransportSender(transportSender);
    }

    protected void removeTransportSender(TransportSender transportSender) {
        ServiceContextHolder.getInstance().removeTransportSender(transportSender);
    }

    @Reference(
            name = "java-dsl-1",
            service = JavaConfigurationBuilder.class,
            cardinality = ReferenceCardinality.OPTIONAL,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "removeJavaDSLType1"
    )
    protected void addJavaDSLType1(JavaConfigurationBuilder dsl) {
        if (isInboundsReady && isOutboundsReady && isMediatorsReady) {
            DSLLoader.loadDSLType1(dsl);
        } else {
            earlyDSLs.add(dsl);
        }
    }

    protected void removeJavaDSLType1(JavaConfigurationBuilder dsl) {
    }


    @Reference(
            name = "java-dsl-2",
            service = IntegrationSolution.class,
            cardinality = ReferenceCardinality.OPTIONAL,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "removeJavaDSLType2"
    )
    protected void addJavaDSLType2(IntegrationSolution dsl) {
        DSLLoader.loadDSLType2(dsl);
    }

    protected void removeJavaDSLType2(IntegrationSolution dsl) {
    }

    @Reference(
            name = "Mediator-Service",
            service = MediatorProvider.class,
            cardinality = ReferenceCardinality.OPTIONAL,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "unregisterMediatorProvider"
    )
    protected void registerMediatorProvider(MediatorProvider mediatorProvider) {
        MediatorProviderRegistry.getInstance().registerMediatorProvider(mediatorProvider);
    }

    protected void unregisterMediatorProvider(MediatorProvider mediatorProvider) {
        MediatorProviderRegistry.getInstance().unregisterMediatorProvider(mediatorProvider);
    }

    public static void setInboundsReady(boolean inboundsReady) {
        isInboundsReady = inboundsReady;
        deployEarlyDSLs();
    }

    public static void setOutboundsReady(boolean outboundsReady) {
        isOutboundsReady = outboundsReady;
        deployEarlyDSLs();
    }

    public static void setMediatorsReady(boolean mediatorsReady) {
        isMediatorsReady = mediatorsReady;
        deployEarlyDSLs();
    }

    private static void deployEarlyDSLs() {
        if (isInboundsReady && isOutboundsReady && isMediatorsReady) {
            for (JavaConfigurationBuilder dsl : earlyDSLs) {
                DSLLoader.loadDSLType1(dsl);
            }
        }
    }


}
