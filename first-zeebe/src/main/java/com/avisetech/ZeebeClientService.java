package com.avisetech;

import com.avisetech.jobhandler.AcknowledgeInsuranceJob;
import com.avisetech.jobhandler.InitiatePaymentJob;
import com.avisetech.jobhandler.ShipWithInsuranceJob;
import com.avisetech.jobhandler.ShipWithoutInsuranceJob;
import io.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class ZeebeClientService {

  @Autowired
  private InitiatePaymentJob initiatePaymentJob;
  @Autowired
  private ShipWithInsuranceJob shipWithInsuranceJob;
  @Autowired
  private ShipWithoutInsuranceJob shipWithoutInsuranceJob;
  @Autowired
  private AcknowledgeInsuranceJob acknowledgeInsuranceJob;

  private ZeebeClient client;

  public void connect(String connectionString) {
    client = ZeebeClient.newClientBuilder()
            .brokerContactPoint(connectionString)
            .usePlaintext()
            .build();

    getClient()
            .newWorker()
            .jobType("initiate-payment")
            .handler(initiatePaymentJob)
            .name("Initiate Payment")
            .open();

    getClient()
            .newWorker()
            .jobType("ship-with-insurance")
            .handler(shipWithInsuranceJob)
            .name("Ship w/ Insurance")
            .open();

    getClient()
            .newWorker()
            .jobType("ship-without-insurance")
            .handler(shipWithoutInsuranceJob)
            .name("Ship w/o Insurance")
            .fetchVariables(Arrays.asList("orderId","orderValue"))
            .open();

    getClient()
            .newWorker()
            .jobType("acknowledge-insurance")
            .handler(acknowledgeInsuranceJob)
            .name("Acknowledge Insurance")
            .open();
  }

  public ZeebeClient getClient() {
    return client;
  }
}
