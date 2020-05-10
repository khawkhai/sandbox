package com.avisetech.rest;

import com.avisetech.ZeebeClientService;
import com.avisetech.jobhandler.AcknowledgeInsuranceJob;
import com.avisetech.jobhandler.InitiatePaymentJob;
import com.avisetech.jobhandler.ShipWithInsuranceJob;
import com.sun.net.httpserver.Authenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1")
public class RestService {

    @Autowired
    private ZeebeClientService zeebeClient;

    @Autowired
    private InitiatePaymentJob initiatePaymentJob;
    @Autowired
    private ShipWithInsuranceJob shipWithInsuranceJob;
    @Autowired
    private AcknowledgeInsuranceJob acknowledgeInsuranceJob;

    @RequestMapping(path = "/initiate/{key}", method = RequestMethod.PUT)
    public ResponseEntity<String> initiatePayment(@PathVariable("key") long key) {
        log.info("PUT > Initiate Payment: " + key);
        initiatePaymentJob.done(key, null);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(path = "/shipwithinsurance/{key}", method = RequestMethod.PUT)
    public ResponseEntity<String> shipWithInsurance(@PathVariable("key") long key) {
        log.info("PUT > Ship with Insurance: " + key);
        shipWithInsuranceJob.done(key, null);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(path = "/acknowledgeinsurance/{key}", method = RequestMethod.PUT)
    public ResponseEntity<String> acknowledgeInsurance(@PathVariable("key") long key) {
        log.info("PUT > Acknowledge Insurance: " + key);
        acknowledgeInsuranceJob.done(key, null);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    //Async Endpoint
//    @RequestMapping(path = "/acknowledgeinsurance/{key}", method = RequestMethod.POST)
//    public ResponseEntity<String> acknowledgeInsurance(@PathVariable("key") long key) {
//        log.info("PUT > Acknowledge Insurance: " + key);
//        acknowledgeInsuranceJob.done(key, null);
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//    }

    // Signal Enpoint
    @RequestMapping(path = "/paymentreceived/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> paymentReceived(@PathVariable("id") String id) {
        log.info("POST > Payment Received: " + id);
        zeebeClient.getClient()
                .newPublishMessageCommand()
                .messageName("payment-received")
                .correlationKey(id)
                .send().join();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
