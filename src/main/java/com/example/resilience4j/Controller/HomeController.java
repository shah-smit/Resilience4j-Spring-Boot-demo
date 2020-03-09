package com.example.resilience4j.Controller;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private HomeService service;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String greeting() {
        return service.getGreeting();
    }

}


