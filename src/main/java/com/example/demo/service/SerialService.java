package com.example.demo.service;

import com.example.demo.model.Serial;
import org.apache.ibatis.annotations.Param;

public interface SerialService {

    Long getNextValue(String serialId);

    Serial getSerialInfo(@Param("snoId") String snoId);

}
