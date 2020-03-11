package com.example.resilience4j.Service;

import com.example.resilience4j.Base.AbstractCircuitBreakerTest;
import com.example.resilience4j.Controller.FailureService;
import com.example.resilience4j.Controller.HomeService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.collection.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class HomeServiceUnitTest extends AbstractCircuitBreakerTest {

    @Autowired
    HomeService service;

    @MockBean
    FailureService failureService;

    @Test
    public void WhenFailureServiceThrowsErrorTenTimesThenCircuitBreakerStateShouldChangeToOPEN(){
        when(failureService.getGreeting()).thenThrow(new RuntimeException());

        Stream.rangeClosed(1,10).forEach((count) -> service.getGreeting());

        checkHealthStatus(CB_INSTANCE_FailureService, CircuitBreaker.State.OPEN);
    }
}
