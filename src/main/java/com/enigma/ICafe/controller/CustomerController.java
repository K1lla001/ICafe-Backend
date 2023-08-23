package com.enigma.ICafe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    @GetMapping
    public String hello(){
        return "Hello Guys";
    }

}
