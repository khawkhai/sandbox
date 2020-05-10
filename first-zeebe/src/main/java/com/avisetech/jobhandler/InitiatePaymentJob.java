package com.avisetech.jobhandler;

import com.avisetech.base.job.SingleTaskJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class InitiatePaymentJob extends SingleTaskJob {

  @Override
  public Map<String, Object> jobReceive(long jobKey, Map<String, Object> variables) {
    System.out.println(">> Start Initiate Payment <<");
    log.info(">>> {}", jobKey);
    var input = variables;
    return input;
  }

  @Override
  public Map<String, Object> jobComplete(long jobKey, Map<String, Object> variables) {
    var output = variables;
    System.out.println(">> Done Initiate Payment <<");
    return output;
  }
}
