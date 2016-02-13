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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.outbound.http;

import org.wso2.carbon.ibus.mediation.cheetah.outbound.protocol.http.HTTPOutboundEndpoint;

/**
 * HTTP Outbound Builder
 */
public class HTTPOutboundEPBuilder {

    private HTTPOutboundEndpoint httpOutboundEndpoint;

    public static HTTPOutboundEPBuilder http(String name, URI uri) {
        return new HTTPOutboundEPBuilder(name, uri);

    }

    private HTTPOutboundEPBuilder(String name, URI uri) {
        httpOutboundEndpoint = new HTTPOutboundEndpoint(name, uri.getUri());
    }

    public HTTPOutboundEndpoint getHttpOutboundEndpoint() {
        return httpOutboundEndpoint;
    }


    public static URI uri(String uri) {
        return new URI(uri);
    }

    /**
     * URI
     */
    public static class URI {
        String uri;

        private URI(String uri) {
            this.uri = uri;
        }

        public String getUri() {
            return uri;
        }
    }

}
