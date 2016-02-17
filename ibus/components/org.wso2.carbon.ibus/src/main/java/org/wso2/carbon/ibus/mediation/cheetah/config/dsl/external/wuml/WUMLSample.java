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
package org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml.generated.WUMLLexer;
import org.wso2.carbon.ibus.mediation.cheetah.config.dsl.external.wuml.generated.WUMLParser;

import java.io.IOException;
import java.io.InputStream;

/**
 * Sample to test the parser
 */
public class WUMLSample {
    public static void main(String[] args) throws IOException {

        // Reading the DSL script
        InputStream is = ClassLoader
                .getSystemResourceAsStream("service3.puml");

        // Loading the DSL script into the ANTLR stream.
        CharStream cs = new ANTLRInputStream(is);

        // Passing the input to the lexer to create tokens
        WUMLLexer lexer = new WUMLLexer(cs);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Passing the tokens to the parser to create the parse trea.
        WUMLParser parser = new WUMLParser(tokens);

        // Adding the listener to facilitate walking through parse tree.
        parser.addParseListener(new WUMLBaseListenerImpl());

        parser.script();


    }
}
