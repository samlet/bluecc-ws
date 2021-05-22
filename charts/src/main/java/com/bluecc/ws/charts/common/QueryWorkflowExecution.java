/*
 *  Copyright 2012-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *  Modifications copyright (C) 2017 Uber Technologies, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"). You may not
 *  use this file except in compliance with the License. A copy of the License is
 *  located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 *  or in the "license" file accompanying this file. This file is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package com.bluecc.ws.charts.common;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.client.WorkflowStub;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;

import java.util.Optional;

/**
 * Queries a workflow execution using the Cadence query API. Cadence redirects a query to any
 * currently running workflow worker for the workflow type of the requested workflow execution.
 *
 * @author fateev
 */
public class QueryWorkflowExecution {

  public static void main(String[] args) throws Exception {
    if (args.length < 2 || args.length > 3) {
      System.err.println(
          "Usage: java "
              + QueryWorkflowExecution.class.getName()
              + " <queryType> <workflowId> [<runId>]");
      System.exit(1);
    }
    IWorkflowService cadenceService = new WorkflowServiceTChannel(ClientOptions.defaultInstance());

    String queryType = args[0];

    WorkflowExecution workflowExecution = new WorkflowExecution();
    String workflowId = args[1];
    workflowExecution.setWorkflowId(workflowId);
    if (args.length == 3) {
      String runId = args[1];
      workflowExecution.setRunId(runId);
    }
    WorkflowClient client =
        WorkflowClient.newInstance(
            cadenceService, WorkflowClientOptions.newBuilder().setDomain(WorkflowConstants.DOMAIN).build());
    WorkflowStub workflow = client.newUntypedWorkflowStub(workflowExecution, Optional.empty());
    String result = workflow.query(queryType, String.class);

    System.out.println("Query result for " + workflowExecution + ":");
    System.out.println(result);
  }
}
