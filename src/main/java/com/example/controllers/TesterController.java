package com.example.controllers;

import com.example.Utils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesterController {
    @GetMapping("/testing")
    public String rdmString(){
        Utils util = new Utils();
        String random = util.RandomString(20);
        System.out.println(random);
        return random;
    }
}