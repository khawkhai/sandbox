package com.avisetech;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Value("${io.zeebe.tasklist.connectionString}")
    private String connectionString;

    @Autowired
    private ZeebeClientService zeebeClientService;

    @PostConstruct
    public void init() {
        log.info("Connecting to Zeebe broker '{}'", connectionString);
        zeebeClientService.connect(connectionString);
    }
}
