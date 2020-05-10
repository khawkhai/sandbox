package com.avisetech.base.job;

import com.avisetech.base.service.TaskService;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.client.api.worker.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class SingleTaskJob extends BaseJob {

    @Autowired
    private TaskService taskService;

    @Override
    public final void handle(JobClient client, ActivatedJob job) throws Exception {
        start(job);
        Map<String, Object> input = jobReceive(job.getKey(), job.getVariablesAsMap());
        taskService.createTask(job.getKey());
    }

    public final void done(long jobKey, Map<String, Object> variables) {
        Map<String, Object> output = jobComplete(jobKey, variables);
        // TODO:Hardcoded
        Map<String, Object> test = new HashMap<>();
        test.put("outcome","SUBMIT");
        test.put("actor", "khai");
        output = test;
        //
        complete(jobKey, output);
    }

    public abstract Map<String, Object> jobReceive(long jobKey, Map<String, Object> variables);

    public abstract Map<String, Object> jobComplete(long jobKey, Map<String, Object> variables);
}
