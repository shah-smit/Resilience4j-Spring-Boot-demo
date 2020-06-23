package com.example.resilience4j.Service;

import com.example.resilience4j.Base.AbstractCircuitBreakerTest;
import com.example.resilience4j.Controller.FailureService;
import com.example.resilience4j.Controller.HomeService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.collection.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class HomeServiceUnitTest extends AbstractCircuitBreakerTest {

    @Autowired
    HomeService service;

    @MockBean
    FailureService failureService;

    @Test
    public void WhenFailureServiceThrowsErrorTenTimesThenCircuitBreakerStateShouldChangeToOPEN() throws InterruptedException {
        when(failureService.getGreeting()).thenThrow(new RuntimeException());

        Stream.rangeClosed(1,10).forEach((count) -> service.getGreeting());

        checkHealthStatus(CB_INSTANCE_FailureService, CircuitBreaker.State.OPEN);

        Thread.sleep(5000);

        checkHealthStatus(CB_INSTANCE_FailureService, CircuitBreaker.State.HALF_OPEN);

        Stream.rangeClosed(1,10).forEach((count) -> service.getGreeting());

        checkHealthStatus(CB_INSTANCE_FailureService, CircuitBreaker.State.OPEN);
    }

    @Test
    public void whenStateGoesToHalfOpenAndSuccessFulStateThenStateBecomesClosed() throws InterruptedException {
        //given
        transitionToOpenState(CB_INSTANCE_FailureService);
        transitionToHalf_OpenState(CB_INSTANCE_FailureService);

        //testing whether the given set correct state
        checkHealthStatus(CB_INSTANCE_FailureService, CircuitBreaker.State.HALF_OPEN);

        //given
        when(failureService.getGreeting()).thenReturn("Success");

        //when
        Stream.rangeClosed(1,10).forEach((count) -> service.getGreeting());

        //then
        checkHealthStatus(CB_INSTANCE_FailureService, CircuitBreaker.State.CLOSED);
    }
}