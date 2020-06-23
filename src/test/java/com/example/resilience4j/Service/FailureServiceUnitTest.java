package com.example.resilience4j.Service;


import com.example.resilience4j.Controller.FailureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FailureServiceUnitTest {

    @Autowired
    private FailureService failureService;

    @Test
    public void whenGetGreetingThenThrowRuntimeException(){
        assertThrows(RuntimeException.class, () -> failureService.getGreeting());
    }
}
