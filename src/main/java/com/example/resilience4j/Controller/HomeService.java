package com.example.resilience4j.Controller;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService{

    @Autowired
    private FailureService failureService;

    @Autowired
    private CircuitBreakerRegistry registry;

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public String getGreeting(){
        return failureService.getGreeting();
    }

    public String fallback(Exception e){
        return registry.circuitBreaker("backendA").getState().name();
    }
}