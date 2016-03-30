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

package org.wso2.carbon.gateway.core.config;

import java.util.Base64;

public class ParamUtils {

    /**
     * Just a sample
     *
     * @return
     */
    public static String encode() {

        return Base64.getEncoder().encodeToString("name".getBytes()).concat(":").
                concat(Base64.getEncoder().encodeToString("httpInbound1".getBytes())).concat(",").
                concat(Base64.getEncoder().encodeToString("port".getBytes()).concat(":")).
                concat(Base64.getEncoder().encodeToString("8280".getBytes()));
    }

    public static ParameterHolder convertToParameters(String paramString) {

        ParameterHolder parameterHolder = new ParameterHolder();

        /* Sample Parameter String without encoding
            name:httpInbound1,port:8280

           With Encoding
            bmFtZQ==:aHR0cEluYm91bmQx,cG9ydA==:ODI4MA==

        */

        String[] params =  paramString.split(",");

        for (String param : params) {


            String name = new String(Base64.getDecoder().decode(param.split(":")[0]));
            String value = new String(Base64.getDecoder().decode(param.split(":")[1]));

            Parameter parameter = new Parameter(name, value);
            parameterHolder.addParameter(parameter);
        }

        return parameterHolder;
    }

}
