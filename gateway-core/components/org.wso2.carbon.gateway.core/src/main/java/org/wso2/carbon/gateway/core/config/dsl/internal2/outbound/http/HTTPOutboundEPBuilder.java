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

package org.wso2.carbon.gateway.core.config.dsl.internal2.outbound.http;


import org.wso2.carbon.gateway.core.outbound.builtin.HTTPOutboundEndpoint;

/**
 * HTTP Outbound Builder
 */
public class HTTPOutboundEPBuilder {


    public static HTTPOutboundEndpoint httpOutboundEndpoint(String name, URI uri) {
        return new HTTPOutboundEndpoint(name, uri.getUri());

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
