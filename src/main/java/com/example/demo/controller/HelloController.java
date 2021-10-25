package com.example.demo.controller;

import com.example.demo.dto.CommodityDTO;
import com.example.demo.model.Serial;
import com.example.demo.model.Storage;
import com.example.demo.service.SerialService;
import com.example.demo.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Slf4j
@Controller
public class HelloController {

    @Autowired
    private SerialService serialService;

    @Autowired
    private StorageService storageService;

    /**
     * http://localhost:19222/hello?commodityCode=C201901140001&count=10
     */
    @RequestMapping("/hello")
    public String hello(HashMap<String, Object> map, CommodityDTO commodityDTO) {
        Serial serial = serialService.getSerialInfo("SEQ_USER_ID");
        Storage storage = storageService.selectByCommodityCode(commodityDTO);
        return "hello";
    }

}
