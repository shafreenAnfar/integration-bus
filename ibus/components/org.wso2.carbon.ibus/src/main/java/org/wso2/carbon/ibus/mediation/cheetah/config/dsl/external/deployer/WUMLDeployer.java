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

package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.deployer;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.carbon.ibus.mediation.cheetah.config.CheetahConfigRegistry;
import org.wso2.carbon.ibus.mediation.cheetah.config.ESBConfigHolder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.WUMLConfigurationBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml.WUMLBaseListenerImpl;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml.generated.WUMLLexer;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml.generated.WUMLParser;
import org.wso2.carbon.kernel.deployment.Artifact;
import org.wso2.carbon.kernel.deployment.ArtifactType;
import org.wso2.carbon.kernel.deployment.Deployer;
import org.wso2.carbon.kernel.deployment.exception.CarbonDeploymentException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * A class responsible for read the WUML files and deploy them to the runtime Object model.
 */
public class WUMLDeployer implements Deployer {


    private ArtifactType artifactType;
    private URL directoryLocation;

    private Map<String, ESBConfigHolder> artifactMap = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(WUMLDeployer.class);

    public static final String EXTERNAL_DSL_CONFIGS_DIRECTORY = "wuml";


    @Override
    public void init() {
        artifactType = new ArtifactType<String>("puml");
        try {
            directoryLocation = new URL("file:" + EXTERNAL_DSL_CONFIGS_DIRECTORY);
        } catch (MalformedURLException e) {
            logger.error("Error while initializing directoryLocation" + directoryLocation.getPath(), e);
        }

    }

    @Override
    public Object deploy(Artifact artifact) throws CarbonDeploymentException {
        File file = artifact.getFile();

        try {
            InputStream inputStream = new FileInputStream(file);
            updateESBConfig(file.getName(), inputStream);
        } catch (FileNotFoundException e) {
            logger.error("Error While Creating InputStream from file " + file.getName());
        }


        return artifact.getFile().getName();
    }

    @Override
    public void undeploy(Object o) throws CarbonDeploymentException {
        ESBConfigHolder configHolder = artifactMap.remove((String) o);
        CheetahConfigRegistry.getInstance().removeESBConfig(configHolder);
    }

    @Override
    public Object update(Artifact artifact) throws CarbonDeploymentException {
        File file = artifact.getFile();
        try {
            InputStream inputStream = new FileInputStream(file);
            updateESBConfig(file.getName(), inputStream);
        } catch (FileNotFoundException e) {
            logger.error("Error while creating inputStream from file " + file.getName());
        }

        return artifact.getFile().getName();
    }

    @Override
    public URL getLocation() {
        return directoryLocation;
    }

    @Override
    public ArtifactType getArtifactType() {
        return artifactType;
    }

    private void updateESBConfig(String key, InputStream inputStream) {
        try {
            CharStream cs = new ANTLRInputStream(inputStream);

            // Passing the input to the lexer to create tokens
            WUMLLexer lexer = new WUMLLexer(cs);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Passing the tokens to the parser to create the parse trea.
            WUMLParser parser = new WUMLParser(tokens);

            // Adding the listener to facilitate walking through parse tree.
            WUMLBaseListenerImpl wumlBaseListener = new WUMLBaseListenerImpl();

            parser.addParseListener(wumlBaseListener);

            parser.script();

            WUMLConfigurationBuilder.IntegrationFlow integrationFlow = wumlBaseListener.getIntegrationFlow();
            ESBConfigHolder esbConfigHolder = integrationFlow.getEsbConfigHolder();
            if (esbConfigHolder != null) {
                artifactMap.put(key, esbConfigHolder);
                CheetahConfigRegistry.getInstance().addESBConfig(esbConfigHolder);
            }

        } catch (IOException e) {
            logger.error("Error while creating cheeta object model", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("Cannot close the input stream", e);
                }
            }
        }
    }

}
