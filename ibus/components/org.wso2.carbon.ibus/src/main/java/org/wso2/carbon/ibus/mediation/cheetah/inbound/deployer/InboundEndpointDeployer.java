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

package org.wso2.carbon.ibus.mediation.cheetah.inbound.deployer;

import org.wso2.carbon.ibus.mediation.cheetah.inbound.InboundEndpoint;
import org.wso2.carbon.ibus.mediation.cheetah.inbound.protocols.http.HTTPInboundEP;
import org.wso2.carbon.kernel.deployment.Artifact;
import org.wso2.carbon.kernel.deployment.ArtifactType;
import org.wso2.carbon.kernel.deployment.exception.CarbonDeploymentException;
import org.wso2.carbon.messaging.ArtifactDeployer;
import org.wso2.carbon.messaging.TransportListener;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * An InboundEndpoint Deployer class
 */
public class InboundEndpointDeployer implements ArtifactDeployer {

    private Map<String, TransportListener> listenerMap = new HashMap<>();


    @Override
    public void registerTransportListener(String id, TransportListener transportListener) {
        listenerMap.put(id, transportListener);
    }

    @Override
    public void init() {

    }

    @Override
    public Object deploy(Artifact artifact) throws CarbonDeploymentException {
        InboundEndpoint inboundEndpoint = createInboundEndpoint(artifact);
        if (inboundEndpoint instanceof HTTPInboundEP) {
            int port = ((HTTPInboundEP) inboundEndpoint).getPort();
            String host = ((HTTPInboundEP) inboundEndpoint).getHost();
            String id = ((HTTPInboundEP) inboundEndpoint).getBindListenerId();
            TransportListener transportListener = listenerMap.get(id);
            if (transportListener != null) {
                transportListener.listen(host, port);
            }
        }

        return null;
    }

    @Override
    public void undeploy(Object o) throws CarbonDeploymentException {

    }

    @Override
    public Object update(Artifact artifact) throws CarbonDeploymentException {
        return null;
    }

    @Override
    public URL getLocation() {
        return null;
    }

    @Override
    public ArtifactType getArtifactType() {
        return null;
    }

    public InboundEndpoint createInboundEndpoint(Artifact artifact) {
        return null;
    }
}
