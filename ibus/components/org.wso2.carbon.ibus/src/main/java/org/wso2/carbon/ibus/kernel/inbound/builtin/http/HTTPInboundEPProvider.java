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

package org.wso2.carbon.ibus.kernel.inbound.builtin.http;

import org.wso2.carbon.ibus.kernel.inbound.Dispatcher;
import org.wso2.carbon.ibus.kernel.inbound.InboundDeployer;
import org.wso2.carbon.ibus.kernel.inbound.InboundEPProvider;
import org.wso2.carbon.ibus.kernel.inbound.InboundEndpoint;
import org.wso2.carbon.messaging.TransportListenerManager;

public class HTTPInboundEPProvider implements InboundEPProvider{
    @Override
    public String getProtocol() {
        return "http";
    }

    @Override
    public InboundDeployer getInboundDeployer() {
        return HTTPListenerManager.getInstance();
    }

    @Override
    public InboundEndpoint getInboundEndpoint() {
        return new HTTPInboundEP();
    }

    @Override
    public Dispatcher getInboundEndpointDispatcher() {
        return HTTPInboundEPDispatcher.getInstance();
    }
}
