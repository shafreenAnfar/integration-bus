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
package org.wso2.carbon.gateway.core.config.dsl.external.wuml;




import org.wso2.carbon.gateway.core.config.Parameter;
import org.wso2.carbon.gateway.core.config.ParameterHolder;
import org.wso2.carbon.gateway.core.config.dsl.external.StringParserUtil;
import org.wso2.carbon.gateway.core.config.dsl.external.WUMLConfigurationBuilder;
import org.wso2.carbon.gateway.core.config.dsl.external.inbound.InboundEndpointFactory;
import org.wso2.carbon.gateway.core.config.dsl.external.inbound.InboundEndpointType;
import org.wso2.carbon.gateway.core.config.dsl.external.outbound.OutboundEndpointFactory;
import org.wso2.carbon.gateway.core.config.dsl.external.outbound.OutboundEndpointType;
import org.wso2.carbon.gateway.core.config.dsl.external.wuml.generated.WUMLBaseListener;
import org.wso2.carbon.gateway.core.config.dsl.external.wuml.generated.WUMLParser;
import org.wso2.carbon.gateway.core.flow.Mediator;
import org.wso2.carbon.gateway.core.flow.MediatorProviderRegistry;
import org.wso2.carbon.gateway.core.flow.Pipeline;
import org.wso2.carbon.gateway.core.flow.mediators.builtin.FlowControllers.filter.Condition;
import org.wso2.carbon.gateway.core.flow.mediators.builtin.FlowControllers.filter.FilterMediator;
import org.wso2.carbon.gateway.core.flow.mediators.builtin.FlowControllers.filter.Source;
import org.wso2.carbon.gateway.core.inbound.InboundEndpoint;
import org.wso2.carbon.gateway.core.outbound.OutboundEndpoint;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Implementation class of the ANTLR generated listener class
 */
public class WUMLBaseListenerImpl extends WUMLBaseListener {

    WUMLConfigurationBuilder.IntegrationFlow integrationFlow;
    Stack<String> pipelineStack = new Stack<String>();
    Stack<FilterMediator> filterMediatorStack = new Stack<FilterMediator>();
    boolean ifMultiThenBlockStarted = false;
    boolean ifElseBlockStarted = false;

    public WUMLBaseListenerImpl() {
        this.integrationFlow = new WUMLConfigurationBuilder.IntegrationFlow("default");
    }

    public WUMLBaseListenerImpl(WUMLConfigurationBuilder.IntegrationFlow integrationFlow) {
        this.integrationFlow = integrationFlow;
    }

    public WUMLConfigurationBuilder.IntegrationFlow getIntegrationFlow() {
        return integrationFlow;
    }

    @Override
    public void exitScript(WUMLParser.ScriptContext ctx) {
        super.exitScript(ctx);
    }

    @Override
    public void exitHandler(WUMLParser.HandlerContext ctx) {
        super.exitHandler(ctx);
    }

    @Override
    public void exitStatementList(WUMLParser.StatementListContext ctx) {
        super.exitStatementList(ctx);
    }

    @Override
    public void exitStatement(WUMLParser.StatementContext ctx) {
        super.exitStatement(ctx);
    }

    @Override
    public void exitParticipantStatement(WUMLParser.ParticipantStatementContext ctx) {
        super.exitParticipantStatement(ctx);
    }

    @Override
    public void exitIntegrationFlowDefStatement(WUMLParser.IntegrationFlowDefStatementContext ctx) {
        //Create the integration flow when definition is found
        integrationFlow = new WUMLConfigurationBuilder.IntegrationFlow(ctx.IDENTIFIER().getText());
        super.exitIntegrationFlowDefStatement(ctx);
    }

    @Override
    public void exitTitleStatement(WUMLParser.TitleStatementContext ctx) {
        //Create the integration flow when definition is found
        integrationFlow = new WUMLConfigurationBuilder.IntegrationFlow(ctx.IDENTIFIER().getText());
        super.exitTitleStatement(ctx);
    }

    @Override
    public void exitInboundEndpointDefStatement(WUMLParser.InboundEndpointDefStatementContext ctx) {
        String protocolName = StringParserUtil.getValueWithinDoubleQuotes(ctx.inboundEndpointDef().
                PROTOCOLDEF().getText());
        int port = Integer.parseInt(StringParserUtil.getValueWithinBrackets(ctx.inboundEndpointDef().
                PORTDEF().getText()));
        String context = StringParserUtil.getValueWithinDoubleQuotes(ctx.inboundEndpointDef().CONTEXTDEF().getText());
        InboundEndpoint inboundEndpoint = InboundEndpointFactory.getInboundEndpoint(InboundEndpointType
                                                                                            .valueOf(protocolName), ctx.IDENTIFIER().getText(), port);
    /*    if (inboundEndpoint instanceof HTTPInboundEP) {
            ((HTTPInboundEP) inboundEndpoint).setContext(context);
        }*/
        integrationFlow.getEsbConfigHolder().setInboundEndpoint(inboundEndpoint);
        super.exitInboundEndpointDefStatement(ctx);
    }

    @Override
    public void exitPipelineDefStatement(WUMLParser.PipelineDefStatementContext ctx) {
        Pipeline pipeline = new Pipeline(ctx.IDENTIFIER().getText());
        integrationFlow.getEsbConfigHolder().addPipeline(pipeline);
        super.exitPipelineDefStatement(ctx);
    }

    @Override
    public void exitOutboundEndpointDefStatement(WUMLParser.OutboundEndpointDefStatementContext ctx) {
        String protocolName = StringParserUtil.getValueWithinDoubleQuotes(ctx.outboundEndpointDef().
                PROTOCOLDEF().getText());
        String uri = StringParserUtil.getValueWithinDoubleQuotes(ctx.outboundEndpointDef().HOSTDEF().getText());
        OutboundEndpoint outboundEndpoint = OutboundEndpointFactory.getOutboundEndpoint(OutboundEndpointType
                                                                                                .valueOf(protocolName), ctx.IDENTIFIER().getText(), uri);
        integrationFlow.getEsbConfigHolder().addOutboundEndpoint(outboundEndpoint);
        super.exitOutboundEndpointDefStatement(ctx);
    }

    @Override
    public void exitInboundEndpointDef(WUMLParser.InboundEndpointDefContext ctx) {
        super.exitInboundEndpointDef(ctx);
    }

    @Override
    public void exitPipelineDef(WUMLParser.PipelineDefContext ctx) {
        super.exitPipelineDef(ctx);
    }

    @Override
    public void exitOutboundEndpointDef(WUMLParser.OutboundEndpointDefContext ctx) {
        super.exitOutboundEndpointDef(ctx);
    }

    @Override
    public void exitIntegrationFlowDef(WUMLParser.IntegrationFlowDefContext ctx) {
        super.exitIntegrationFlowDef(ctx);
    }

    @Override
    public void exitMediatorStatement(WUMLParser.MediatorStatementContext ctx) {
        super.exitMediatorStatement(ctx);
    }

    @Override
    public void exitMediatorDef(WUMLParser.MediatorDefContext ctx) {
        //String mediatorName = ctx.MEDIATORNAME().getText();
        String mediatorName = ctx.IDENTIFIER().getText();
        String configurations = StringParserUtil.getValueWithinDoubleQuotes(ctx.ARGUMENTLISTDEF().getText());
        Mediator mediator = MediatorProviderRegistry.getInstance().getMediator(mediatorName);

        // mediator.setParameters(configurations);
        if (ifMultiThenBlockStarted) {
            filterMediatorStack.peek().addThenMediator(mediator);

        } else if (ifElseBlockStarted) {
            filterMediatorStack.peek().addOtherwiseMediator(mediator);

        } else {
            integrationFlow.getEsbConfigHolder().getPipeline(pipelineStack.peek()).addMediator(mediator);
        }
        super.exitMediatorDef(ctx);
    }

    @Override
    public void exitConditionDef(WUMLParser.ConditionDefContext ctx) {
        super.exitConditionDef(ctx);
    }

    @Override
    public void exitRoutingStatement(WUMLParser.RoutingStatementContext ctx) {
        super.exitRoutingStatement(ctx);
    }

    @Override
    public void exitInvokeFromSource(WUMLParser.InvokeFromSourceContext ctx) {
        //String inbountEndpointName = ctx.INBOUNDENDPOINTNAME().getText();
        String pipelineName = ctx.IDENTIFIER(1).getText();
        integrationFlow.getEsbConfigHolder().getInboundEndpoint().setPipeline(pipelineName);
        pipelineStack.push(pipelineName);
        //activePipeline = pipelineName;
        super.exitInvokeFromSource(ctx);
    }

    @Override
    public void exitInvokeToTarget(WUMLParser.InvokeToTargetContext ctx) {
        Mediator mediator = MediatorProviderRegistry.getInstance().getMediator("call");

        ParameterHolder parameterHolder = new ParameterHolder();
        parameterHolder.addParameter(new Parameter("endpointKey",ctx.IDENTIFIER(1).getText()));

        mediator.setParameters(parameterHolder);
        if(ifMultiThenBlockStarted) {
            filterMediatorStack.peek().addThenMediator(mediator);

        } else if(ifElseBlockStarted) {
            filterMediatorStack.peek().addOtherwiseMediator(mediator);

        } else {
//            String mediatorName = StringParserUtil.getValueWithinDoubleQuotes(ctx.MEDIATORNAMESTRINGX().getText());
//            String configurations = StringParserUtil.getValueWithinDoubleQuotes(ctx.CONFIGSDEF().getText());
//            Mediator mediator = MediatorFactory.getMediator(MediatorType.valueOf(mediatorName), configurations);
            integrationFlow.getEsbConfigHolder().getPipeline(pipelineStack.peek()).addMediator(mediator);
        }
        pipelineStack.pop();
        super.exitInvokeToTarget(ctx);
    }

    @Override
    public void exitInvokeFromTarget(WUMLParser.InvokeFromTargetContext ctx) {
        String pipelineName = ctx.IDENTIFIER(1).getText();
        pipelineStack.push(pipelineName);
        super.exitInvokeFromTarget(ctx);
    }

    @Override
    public void exitInvokeToSource(WUMLParser.InvokeToSourceContext ctx) {
        Mediator mediator = MediatorProviderRegistry.getInstance().getMediator("respond");
        if(ifMultiThenBlockStarted) {
            filterMediatorStack.peek().addThenMediator(mediator);

        } else if(ifElseBlockStarted) {
            filterMediatorStack.peek().addOtherwiseMediator(mediator);

        } else {
//            String mediatorName = StringParserUtil.getValueWithinDoubleQuotes(ctx.MEDIATORNAMESTRINGX().getText());
//            String configurations = StringParserUtil.getValueWithinDoubleQuotes(ctx.CONFIGSDEF().getText());
//            Mediator mediator = MediatorFactory.getMediator(MediatorType.valueOf(mediatorName), configurations);
            integrationFlow.getEsbConfigHolder().getPipeline(pipelineStack.peek()).addMediator(mediator);
        }
        pipelineStack.pop();
        super.exitInvokeToSource(ctx);
    }

    @Override
    public void exitParallelStatement(WUMLParser.ParallelStatementContext ctx) {
        super.exitParallelStatement(ctx);
    }

    @Override
    public void exitParMultiThenBlock(WUMLParser.ParMultiThenBlockContext ctx) {
        super.exitParMultiThenBlock(ctx);
    }

    @Override
    public void exitParElseBlock(WUMLParser.ParElseBlockContext ctx) {
        super.exitParElseBlock(ctx);
    }

    @Override
    public void exitIfStatement(WUMLParser.IfStatementContext ctx) {
        //ctx.expression().EXPRESSIONX()
        ifMultiThenBlockStarted = false;
        ifElseBlockStarted = false;
        if (!filterMediatorStack.isEmpty()) {
            filterMediatorStack.pop();
        }
        super.exitIfStatement(ctx);
    }

    @Override
    public void exitConditionStatement(WUMLParser.ConditionStatementContext ctx) {
        String sourceDefinition = StringParserUtil.getValueWithinDoubleQuotes(ctx.conditionDef().SOURCEDEF().getText());
        Source source = new Source(sourceDefinition);
        Condition condition =
                new Condition(source,
                              Pattern.compile(
                                      StringParserUtil.getValueWithinDoubleQuotes(
                                              ctx.conditionDef().PATTERNDEF().getText())));
        FilterMediator filterMediator = new FilterMediator(condition);
        integrationFlow.getEsbConfigHolder().getPipeline(pipelineStack.peek()).addMediator(filterMediator);
        filterMediatorStack.push(filterMediator);
        super.exitConditionStatement(ctx);
    }

    @Override
    public void enterIfMultiThenBlock(WUMLParser.IfMultiThenBlockContext ctx) {
        ifMultiThenBlockStarted = true;
        super.enterIfMultiThenBlock(ctx);
    }

    @Override
    public void enterIfElseBlock(WUMLParser.IfElseBlockContext ctx) {
        ifMultiThenBlockStarted = false;
        ifElseBlockStarted = true;
        super.enterIfElseBlock(ctx);
    }

    @Override
    public void exitIfMultiThenBlock(WUMLParser.IfMultiThenBlockContext ctx) {
        ifMultiThenBlockStarted = false;
        super.exitIfMultiThenBlock(ctx);
    }

    @Override
    public void exitIfElseBlock(WUMLParser.IfElseBlockContext ctx) {
        ifElseBlockStarted = false;
        super.exitIfElseBlock(ctx);
    }

    @Override
    public void exitGroupStatement(WUMLParser.GroupStatementContext ctx) {
        super.exitGroupStatement(ctx);
    }

    @Override
    public void exitLoopStatement(WUMLParser.LoopStatementContext ctx) {
        super.exitLoopStatement(ctx);
    }

    @Override
    public void exitRefStatement(WUMLParser.RefStatementContext ctx) {
        pipelineStack.push(ctx.IDENTIFIER().getText());
        super.exitRefStatement(ctx);
    }

    @Override
    public void exitExpression(WUMLParser.ExpressionContext ctx) {
        super.exitExpression(ctx);
    }


}