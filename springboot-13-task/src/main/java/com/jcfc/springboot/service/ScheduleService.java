package com.jcfc.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Scheduled(cron = "0 * * * * MON-SAT")
    public void hello() {
        System.out.println("hello...");
    }

}
