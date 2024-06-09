package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String showIndex4Get() {
        return "index";
    }
    
    @PostMapping("/")
    public String showIndex4Post() {
        return "index";
    }
}
