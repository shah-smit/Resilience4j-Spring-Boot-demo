package com.example.resilience4j.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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


