package com.avisetech.jobhandler;

import com.avisetech.base.job.GroupTaskJob;
import com.avisetech.base.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class AcknowledgeInsuranceJob extends GroupTaskJob {

  @Autowired
  private NotificationService notificationService;

  @Override
  public Map<String, Object> jobReceive(long jobKey, Map<String, Object> variables) {
    System.out.println(">> Start Acknowledge Insurance <<");
    log.info(">>> {}", jobKey);
    Map<String, Object> input = variables;
    return input;
  }

  @Override
  public Map<String, Object> jobComplete(long jobKey, Map<String, Object> variables) {
    Map<String, Object> output = variables;
    System.out.println(">> Acknowledge Insurance <<");
    Map<String, Object> input = variables;
    return output;
  }
}
