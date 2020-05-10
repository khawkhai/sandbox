package com.avisetech.jobhandler;

import com.avisetech.base.job.SingleTaskJob;
import com.avisetech.base.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ShipWithInsuranceJob extends SingleTaskJob {

  @Autowired
  private NotificationService notificationService;

  @Override
  public Map<String, Object> jobReceive(long jobKey, Map<String, Object> variables) {
    System.out.println(">> Start Ship With Insurance <<");
    log.info(">>> {}", jobKey);
    var input = variables;
    return input;
  }

  @Override
  public Map<String, Object> jobComplete(long jobKey, Map<String, Object> variables) {
    var output = variables;
    System.out.println(">> Done Ship With Insurance <<");
    var input = variables;
    return output;
  }
}
