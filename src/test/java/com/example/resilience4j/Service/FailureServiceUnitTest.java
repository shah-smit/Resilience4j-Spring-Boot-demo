package com.example.resilience4j.Service;


import com.example.resilience4j.Controller.FailureService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FailureServiceUnitTest {

    @Autowired
    private FailureService failureService;

    @Test(expected = RuntimeException.class)
    public void whenGetGreetingThenThrowRuntimeException(){
        failureService.getGreeting();
    }
}
