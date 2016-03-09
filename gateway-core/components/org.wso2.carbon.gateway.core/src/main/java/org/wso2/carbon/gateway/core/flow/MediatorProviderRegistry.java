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

package org.wso2.carbon.gateway.core.flow;

import java.util.HashMap;
import java.util.Map;

public class MediatorProviderRegistry {

    private Map<String, MediatorProvider> mediatorProviders = new HashMap<>();

    private static MediatorProviderRegistry instance = new MediatorProviderRegistry();

    private MediatorProviderRegistry() {}

    public static MediatorProviderRegistry getInstance() {
        return instance;
    }

    public void registerMediatorProvider(MediatorProvider mediatorProvider) {
        mediatorProviders.put(mediatorProvider.getName(), mediatorProvider);
    }

    public void unregisterMediatorProvider(MediatorProvider mediatorProvider) {
        mediatorProviders.remove(mediatorProvider.getName());
    }

    public void unregisterMediatorProvider(String mediatorName) {
        mediatorProviders.remove(mediatorName);
    }

    public MediatorProvider getMediatorProvider(String name) {
        return mediatorProviders.get(name);
    }

}
