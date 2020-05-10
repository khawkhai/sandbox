package com.avisetech.base.job;

import com.avisetech.base.service.TaskService;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class SyncServiceJob extends BaseJob {

    @Override
    public final void handle(JobClient client, ActivatedJob job) throws Exception {
        start(job);
        Map<String, Object> output = jobReceive(job.getKey(), job.getVariablesAsMap());
        complete(job.getKey(), output);
    }

    public abstract Map<String, Object> jobReceive(long jobKey, Map<String, Object> variables);
}
