package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Slf4j
@Controller
public class UserController {

    @RequestMapping("/hello")
    public String hello(HashMap<String, Object> map) {
        return "hello";
    }

}
