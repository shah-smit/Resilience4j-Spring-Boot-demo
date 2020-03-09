package com.example.resilience4j;

import com.example.resilience4j.Controller.FailureService;
import com.example.resilience4j.Controller.HomeController;
import com.example.resilience4j.Controller.HomeService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.collection.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HomeServiceUnitTest extends AbstractCircuitBreakerTest {

    @Autowired
    HomeService service;

    @MockBean
    FailureService failureService;

    @Test
    public void WhenFailureServiceThrowsErrorTenTimesThenCircuitBreakerStateShouldChangeToOPEN(){
        when(failureService.getGreeting()).thenThrow(new RuntimeException());

        Stream.rangeClosed(1,10).forEach((count) -> service.getGreeting());

        checkHealthStatus(BACKEND_A, CircuitBreaker.State.OPEN);
    }
}
