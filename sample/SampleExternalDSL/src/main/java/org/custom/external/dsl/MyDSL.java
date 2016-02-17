/*
*  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
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

package org.custom.external.dsl;


import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.osgi.framework.BundleContext;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.WUMLConfigurationBuilder;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml.WUMLBaseListenerImpl;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml.generated.WUMLLexer;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml.generated.WUMLParser;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * Sample DSL
 */
public class MyDSL extends WUMLConfigurationBuilder {

    BundleContext bundleContext;

    public MyDSL(BundleContext bundleContext) {
        this.bundleContext = bundleContext;

    }

    public WUMLConfigurationBuilder.IntegrationFlow configure() {

        WUMLConfigurationBuilder.IntegrationFlow router = null;

//        // Reading the DSL script
//        InputStream is = ClassLoader
//                .getSystemResourceAsStream("service3.puml");


        // Loading the DSL script into the ANTLR stream.
        CharStream cs = null;
        try {
            URL url = bundleContext.getBundle().getResource("service3.puml");

            InputStream is = url.openConnection().getInputStream();

            cs = new ANTLRInputStream(is);

            // Passing the input to the lexer to create tokens
            WUMLLexer lexer = new WUMLLexer(cs);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Passing the tokens to the parser to create the parse trea.
            WUMLParser parser = new WUMLParser(tokens);

            // Adding the listener to facilitate walking through parse tree.
            WUMLBaseListenerImpl wumlBaseListener = new WUMLBaseListenerImpl();
            parser.addParseListener(wumlBaseListener);
            parser.script();
            router = wumlBaseListener.getIntegrationFlow();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return router;

    }
}
