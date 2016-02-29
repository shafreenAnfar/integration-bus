/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 * <p>
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.internal.inbound.http;


import org.wso2.carbon.ibus.mediation.cheetah.inbound.protocols.http.HTTPInboundEP;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.protocols.http.HTTPSInboundEP;

/**
 * A class which is responsible for create HTTPS inbound endpoints.
 */
public class HTTPSInboundEPBuilder extends HTTPInboundEPBuilder {


    public static HTTPInboundEP https(Port port, Context context, KeyStoreFile keysSoreFile,
                                      KeyStorePass keyStorePass) {
        HTTPSInboundEP inboundEP = new HTTPSInboundEP(port.getPort());
        inboundEP.setKeyStorePass(keyStorePass.getKeystorePass());
        inboundEP.setKeyStoreFile(keysSoreFile.getKeyStoreFile());
        inboundEP.setContext(context.getContext());
        return inboundEP;
    }

    public static KeyStorePass keystorepass(String keystorePass) {
        return new KeyStorePass(keystorePass);
    }

    public static KeyStoreFile keystorefile(String keyStoreFile) {
        return new KeyStoreFile(keyStoreFile);
    }


    /**
     * A class which is responsible for keyStorePass
     */
    public static class KeyStorePass {

        private String keystorePass;

        public KeyStorePass(String password) {
            this.keystorePass = password;
        }


        public String getKeystorePass() {
            return keystorePass;
        }
    }


    /**
     * A class which is responsible for represent keyStoreFile path
     */
    public static class KeyStoreFile {
        private String keyStoreFile;

        public KeyStoreFile(String keyStoreFile) {
            this.keyStoreFile = keyStoreFile;
        }

        public String getKeyStoreFile() {
            return keyStoreFile;
        }
    }


}
