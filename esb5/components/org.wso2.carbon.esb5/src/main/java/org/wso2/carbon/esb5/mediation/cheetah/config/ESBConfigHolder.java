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

package org.wso2.carbon.esb5.mediation.cheetah.config;

import org.wso2.carbon.esb5.mediation.cheetah.flow.sequence.Sequence;
import org.wso2.carbon.esb5.mediation.cheetah.inbound.InboundEndpoint;
import org.wso2.carbon.esb5.mediation.cheetah.outbound.OutboundEndpoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Object Model which holds configurations related to a one ESB process
 */
public class ESBConfigHolder {

    private String name;

    private InboundEndpoint inboundEndpoint;

    private Map<String, Sequence> sequences = new HashMap<>();

    private Map<String, OutboundEndpoint> outboundEndpoints = new HashMap<>();


    public ESBConfigHolder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InboundEndpoint getInboundEndpoint() {
        return inboundEndpoint;
    }

    public void setInboundEndpoint(
            InboundEndpoint inboundEndpoint) {
        this.inboundEndpoint = inboundEndpoint;
    }

    public Sequence getSequence(String name) {
        return sequences.get(name);
    }

    public void addSequence(Sequence sequence) {
        sequences.put(sequence.getName(), sequence);
    }

    public Map<String, Sequence> getSequences() {
        return sequences;
    }

    public Map<String, OutboundEndpoint> getOutboundEndpoints() {
        return outboundEndpoints;
    }

    public OutboundEndpoint getOutboundEndpoint(String name) {
        return outboundEndpoints.get(name);
    }

    public void addOutboundEndpoint(OutboundEndpoint outboundEndpoint) {
        outboundEndpoints.put(outboundEndpoint.getName(), outboundEndpoint);
    }
}
