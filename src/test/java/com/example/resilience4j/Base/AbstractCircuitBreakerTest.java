package com.example.resilience4j.Base;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractCircuitBreakerTest {

    protected static final String CB_INSTANCE_FailureService = "FailureService";

    @Autowired
    public CircuitBreakerRegistry registry;

    @BeforeEach
    public void setup(){
        transitionToClosedState(CB_INSTANCE_FailureService);
    }

    protected void checkHealthStatus(String circuitBreakerName, CircuitBreaker.State state) {
        CircuitBreaker circuitBreaker = registry.circuitBreaker(circuitBreakerName);
        assertThat(circuitBreaker.getState()).isEqualTo(state);
    }

    protected void transitionToOpenState(String circuitBreakerName) {
        CircuitBreaker circuitBreaker = registry.circuitBreaker(circuitBreakerName);
        if(!circuitBreaker.getState().equals(CircuitBreaker.State.OPEN)){
            circuitBreaker.transitionToOpenState();
        }
    }

    protected void transitionToHalf_OpenState(String circuitBreakerName) {
        CircuitBreaker circuitBreaker = registry.circuitBreaker(circuitBreakerName);
        if(!circuitBreaker.getState().equals(CircuitBreaker.State.HALF_OPEN)){
            circuitBreaker.transitionToHalfOpenState();
        }
    }

    protected void transitionToClosedState(String circuitBreakerName) {
        CircuitBreaker circuitBreaker = registry.circuitBreaker(circuitBreakerName);
        if(!circuitBreaker.getState().equals(CircuitBreaker.State.CLOSED)){
            circuitBreaker.transitionToClosedState();
        }
    }
}