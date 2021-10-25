package com.example.demo.controller;

import com.example.demo.model.Serial;
import com.example.demo.service.SerialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private SerialService serialService;

    @RequestMapping("/hello")
    public String hello(HashMap<String, Object> map) {

        Serial serial = serialService.getSerialInfo("SEQ_USER_ID");

        return "hello";
    }

}
