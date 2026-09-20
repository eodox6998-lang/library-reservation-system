package com.example.demo;
import java.time.Clock;

import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;

@Service 
public class AuditLogger {
    private final Clock clock;

    AuditLogger(Clock clock){
        this.clock = clock;
    }
    public String log(String log){
        return clock.instant().toString() + " "+log;
    }
    @PreDestroy 
    public String stutDown(){
        return "Shutting down, flushing logs";
    }
}
