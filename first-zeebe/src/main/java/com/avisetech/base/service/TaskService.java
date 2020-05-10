package com.avisetech.base.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskService {

    public void createTask(long jobKey) {
        log.info(">>> Created Task: {} <<<", jobKey);
    }

    public void createGroupTask(long jobKey) {
        log.info(">>> Created Group Task: {} <<<", jobKey);
    }

    public void completeTask() {
        log.info("Completed Task");
    }

    public void reassignTask() {
        log.info("Reassigned Task");
    }

    public void withdrawTask() {
        log.info("Withdrawn Task");
    }
}
