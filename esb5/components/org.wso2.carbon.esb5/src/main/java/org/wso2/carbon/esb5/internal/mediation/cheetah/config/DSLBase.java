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

package org.wso2.carbon.esb5.internal.mediation.cheetah.config;

import org.wso2.carbon.esb5.internal.mediation.cheetah.inbound.InboundEPRegistry;
import org.wso2.carbon.esb5.internal.mediation.cheetah.inbound.http.HTTPInboundEP;

/**
 * Base class for DSL Configuration
 */
public abstract class DSLBase {

    public void endpoint(String name, int port, String context) {
        HTTPInboundEP ep1 = new HTTPInboundEP(name);
        ep1.setPort(port);
        ep1.setContext(context);
        InboundEPRegistry.getInstance().registerEndpoint(name, ep1);
    }

    public abstract void configure();


}
