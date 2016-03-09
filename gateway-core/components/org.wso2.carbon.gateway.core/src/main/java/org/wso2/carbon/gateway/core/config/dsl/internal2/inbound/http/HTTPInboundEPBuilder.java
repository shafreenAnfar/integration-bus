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

package org.wso2.carbon.gateway.core.config.dsl.internal2.inbound.http;


import org.wso2.carbon.gateway.core.inbound.builtin.http.HTTPInboundEP;

/**
 * A Builder class for HTTP Inbound Endpoint
 */
public class HTTPInboundEPBuilder {

    private HTTPInboundEP httpInboundEP;

    public static HTTPInboundEP http(Port port, Context context) {
        HTTPInboundEP inboundEP = new HTTPInboundEP(port.getPort());
        inboundEP.setContext(context.getContext());
        return inboundEP;
    }


    public static Context context(String context) {
        return new Context(context);
    }

    public static Port port(int port) {
        return new Port(port);
    }


    /**
     * Port
     */
    public static class Port {
        int port;

        private Port(int port) {
            this.port = port;
        }

        public int getPort() {
            return port;
        }
    }

    /**
     * Context
     */
    public static class Context {
        String context;

        private Context(String context) {
            this.context = context;
        }

        public String getContext() {
            return context;
        }
    }


}
