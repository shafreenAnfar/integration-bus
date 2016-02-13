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

package org.wso2.carbon.ibus.mediation.cheetah.outbound.protocol.http;

import org.wso2.carbon.ibus.mediation.cheetah.outbound.OutboundEndpoint;

/**
 * HTTP Outbound Endpoint
 */
public class HTTPOutboundEndpoint extends OutboundEndpoint {

    private String uri;

    public HTTPOutboundEndpoint(String name, String uri) {
        super(name);
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
