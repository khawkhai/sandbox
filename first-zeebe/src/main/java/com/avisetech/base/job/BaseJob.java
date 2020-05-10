package com.avisetech.base.job;

import com.avisetech.ZeebeClientService;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.client.api.worker.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
public abstract class BaseJob implements JobHandler {

    @Autowired
    private ZeebeClientService zeebeClient;

    public abstract void handle(JobClient client, ActivatedJob job) throws Exception;

    final void start (ActivatedJob job) {
        log.info("Key : {}", job.getKey());
        log.info("Time : {}", Instant.now());
        log.info("Var : {}", job.getVariables());
    }

    final void complete(long jobKey, Map<String, Object> variables) {
        zeebeClient.getClient()
                .newCompleteCommand(jobKey)
                .variables(variables)
                .send().join();
    }

    final void fail(long jobKey, String message) {
        zeebeClient.getClient()
                .newFailCommand(jobKey)
                .retries(1)
                .errorMessage(message)
                .send().join();
    }

    final void exception(long jobKey, String code, String message) {
        zeebeClient.getClient()
                .newThrowErrorCommand(jobKey)
                .errorCode(code)
                .errorMessage(message)
                .send().join();
    }
}
