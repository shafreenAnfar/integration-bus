/*
 * Copyright (c) 2015, WSO2 Inc. (http://wso2.com) All Rights Reserved.
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

package org.wso2.carbon.esb5.mediation.cheetah.config;



import org.wso2.carbon.esb5.mediation.cheetah.flow.sequence.Sequence;
import org.wso2.carbon.esb5.mediation.cheetah.inbound.InboundEndpoint;
import org.wso2.carbon.esb5.mediation.cheetah.outbound.OutboundEndpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class that represents the Cheetah configuration
 */
public class CheetahConfigRegistry {


    private static CheetahConfigRegistry cheetahConfigRegistry = new CheetahConfigRegistry();

    private Map<String, InboundEndpoint> inboundEndpoints = new HashMap<>();

    private Map<String, Sequence> sequenceMap = new HashMap<>();

    private Map<String, OutboundEndpoint> outBoundEndpointMap = new HashMap<>();

    private List<ConfigRegistryObserver> observers = new ArrayList<>();


    public static CheetahConfigRegistry getInstance() {
        return cheetahConfigRegistry;
    }


    private CheetahConfigRegistry() {

    }

    public void registerInboundEndpoint(String name, InboundEndpoint inboundEndpoint) {
        inboundEndpoints.put(name, inboundEndpoint);

        //Inform Observers
        for (ConfigRegistryObserver observer : observers) {
            observer.endpointAdded(inboundEndpoint);
        }
    }

    public void unregisterInboundEndpoint(InboundEndpoint inboundEndpoint) {

        inboundEndpoints.remove(inboundEndpoint.getName());

        //Inform Observers
        for (ConfigRegistryObserver observer : observers) {
            observer.endpointRemoved(inboundEndpoint);
        }
    }

    public InboundEndpoint getInboundEndpoint(String name) {
        return inboundEndpoints.get(name);
    }

    public void registerObserver(ConfigRegistryObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(ConfigRegistryObserver observer) {
        observers.remove(observer);
    }


    public void addSequence(Sequence sequence) {
        sequenceMap.put(sequence.getName(), sequence);
    }


    public Sequence getSequence(String name) {
        return sequenceMap.get(name);
    }

    public OutboundEndpoint getOutboundEndpoint(String key) {
        return outBoundEndpointMap.get(key);
    }

    public void registerOutboundEndpoint(String key, OutboundEndpoint outboundEndpoint) {
        outBoundEndpointMap.put(key, outboundEndpoint);
    }

}
