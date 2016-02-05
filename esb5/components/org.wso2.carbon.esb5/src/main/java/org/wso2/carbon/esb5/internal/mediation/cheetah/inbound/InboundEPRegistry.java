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
package org.wso2.carbon.esb5.internal.mediation.cheetah.inbound;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Register for Inbound Endpoints
 */
public class InboundEPRegistry {

    private static InboundEPRegistry instance = new InboundEPRegistry();

    private Map<String, InboundEndpoint> inboundEndpoints = new HashMap<String, InboundEndpoint>();

    private List<InboundEPRegistryObserver> observers = new ArrayList<InboundEPRegistryObserver>();


    public static InboundEPRegistry getInstance() {
        return instance;
    }

    public void registerEndpoint(String name, InboundEndpoint endpoint) {
        inboundEndpoints.put(name, endpoint);

        //Inform Observers
        for (InboundEPRegistryObserver observer : observers) {
            observer.endpointAdded(endpoint);
        }
    }

    public void unregisterEndpoint(InboundEndpoint endpoint) {

        inboundEndpoints.remove(endpoint.getName());

        //Inform Observers
        for (InboundEPRegistryObserver observer : observers) {
            observer.endpointRemoved(endpoint);
        }
    }

    public InboundEndpoint getEndpoint(String name) {
        return inboundEndpoints.get(name);
    }

    public void registerObserver(InboundEPRegistryObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(InboundEPRegistryObserver observer) {
        observers.remove(observer);
    }


}
