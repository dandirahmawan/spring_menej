package com.menej.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FailedController {
    @GetMapping("/failed")
    public String failed(){
        return "failed";
    }
}
