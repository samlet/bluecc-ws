package com.bluecc.ws.charts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.linecorp.armeria.common.logging.LogLevel;
import com.linecorp.armeria.server.annotation.Header;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.ProducesJson;
import com.linecorp.armeria.server.annotation.RequestObject;
import com.linecorp.armeria.server.annotation.decorator.LoggingDecorator;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.internal.common.WorkflowExecutionUtils;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.bluecc.ws.charts.common.WorkflowConstants.DOMAIN;

@LoggingDecorator(
        requestLogLevel = LogLevel.INFO,            // Log every request sent to this service at INFO level.
        successfulResponseLogLevel = LogLevel.INFO  // Log every response sent from this service at INFO level.
)
public class WorkflowInfo {
    private static final Logger logger = LoggerFactory.getLogger(WorkflowInfo.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    IWorkflowService cadenceService = new WorkflowServiceTChannel(ClientOptions.defaultInstance());

    @Post("/timestamp")
    @ProducesJson
    public GenericResponse timestamp(@Header String xWorkload,
                                     @Header String xToken,
                                     @RequestObject RequestInfo input) {
        final String caller = input.getCaller();
        logger.info("caller is {}, workload: {}, token: {}", caller, xWorkload, xToken);
        return new GenericResponse(GenericResponse.SUCCESS, caller,
                ImmutableMap.of("ts", System.currentTimeMillis()));
    }

    public static class WorkflowRequestInfo{
        private String caller;
        private String workflowId;
        private String runId;

        public String getWorkflowId() {
            return workflowId;
        }

        public void setWorkflowId(String workflowId) {
            this.workflowId = workflowId;
        }

        public String getRunId() {
            return runId;
        }

        public void setRunId(String runId) {
            this.runId = runId;
        }

        public String getCaller() {
            return caller;
        }

        public void setCaller(String caller) {
            this.caller = caller;
        }

        public boolean hasRunId(){
            return runId!=null && !runId.isEmpty();
        }
    }

    @Post("/history")
    @ProducesJson
    public GenericResponse history(@Header String xWorkload,
                                     @Header String xToken,
                                     @RequestObject WorkflowRequestInfo input) {
        final String caller = input.getCaller();
        logger.info("caller is {}, workload: {}, token: {}", caller, xWorkload, xToken);

        WorkflowExecution workflowExecution = new WorkflowExecution();
        String workflowId = input.getWorkflowId();
        workflowExecution.setWorkflowId(workflowId);
        if(input.hasRunId()) {
            String runId = input.getRunId();
            workflowExecution.setRunId(runId);
        }
        String result= WorkflowExecutionUtils.prettyPrintHistory(cadenceService, DOMAIN, workflowExecution, true);

        return new GenericResponse(GenericResponse.SUCCESS, caller,
                ImmutableMap.of("history", result));
    }
}
