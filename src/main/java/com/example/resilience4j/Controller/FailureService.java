package com.example.resilience4j.Controller;

import org.springframework.stereotype.Service;

@Service
public class FailureService{
    public String getGreeting(){
        throw new RuntimeException();
    }
}