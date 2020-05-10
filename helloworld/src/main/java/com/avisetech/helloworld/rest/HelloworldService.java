package com.avisetech.helloworld.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class HelloworldService {
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        log.info("sayHello");
        return "World";
    }
}
