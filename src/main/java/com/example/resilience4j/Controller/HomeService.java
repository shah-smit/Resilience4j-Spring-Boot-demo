package com.example.resilience4j.Controller;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService{

    private static final String FAILURE_SERVICE = "FailureService";

    @Autowired
    private FailureService failureService;

    @Autowired
    private CircuitBreakerRegistry registry;

    @CircuitBreaker(name = FAILURE_SERVICE, fallbackMethod = "fallback")
    public String getGreeting(){
        return failureService.getGreeting();
    }

    public String fallback(Exception e){
        return registry.circuitBreaker(FAILURE_SERVICE).getState().name();
    }
}