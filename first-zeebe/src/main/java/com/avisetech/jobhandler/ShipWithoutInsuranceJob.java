package com.avisetech.jobhandler;

import com.avisetech.base.job.SyncServiceJob;
import com.avisetech.base.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ShipWithoutInsuranceJob extends SyncServiceJob {

  @Autowired
  private NotificationService notificationService;

  @Override
  public Map<String, Object> jobReceive(long jobKey, Map<String, Object> variables) {
    System.out.println(">> Ship Without Insurance <<");

//    val customHeaders = job.getCustomHeaders();
//    val variables = job.getVariablesAsMap();
//
//    final String name = (String) customHeaders.getOrDefault("name", job.getElementId());
//    log.info(name);

    System.out.println(">> CALLING ASYNC");
    // TODO:Hardcoded
    var output = new HashMap<String,Object>();
    output.put("isSuccessful",true);
    output.put("result", "hello");
    //
    notificationService.pushNotification("SUCCESSFUL");
    return output;
  }
}
