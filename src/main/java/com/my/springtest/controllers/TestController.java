package com.my.springtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/hi")
    public String sayHello() {
        return "hello_world";
    }
}
