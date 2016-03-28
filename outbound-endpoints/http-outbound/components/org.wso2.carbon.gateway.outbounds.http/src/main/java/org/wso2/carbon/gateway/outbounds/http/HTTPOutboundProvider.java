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

package org.wso2.carbon.gateway.outbounds.http;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.wso2.carbon.gateway.core.outbound.OutboundEPProvider;
import org.wso2.carbon.gateway.core.outbound.OutboundEndpoint;

@Component(
        name = "org.wso2.carbon.gateway.outbounds.http.HTTPOutboundProvider",
        immediate = true,
        service = OutboundEPProvider.class
)
public class HTTPOutboundProvider implements OutboundEPProvider {

    @Activate
    protected void start(BundleContext bundleContext) {
    }

    @Override
    public String getProtocol() {
        return "http";
    }

    @Override
    public OutboundEndpoint getEndpoint() {
        return new HTTPOutboundEndpoint();
    }
}
