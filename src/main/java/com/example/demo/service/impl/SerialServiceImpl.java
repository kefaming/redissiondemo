package com.example.demo.service.impl;

import com.example.demo.mapper.SerialMapper;
import com.example.demo.model.Serial;
import com.example.demo.service.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class SerialServiceImpl implements SerialService {

    @Resource
    private SerialMapper serialDao;

    public Long getNextValue(String serialId) {
        Map<String, Object> map = serialDao.getSerialNo(serialId);
        Long result = (Long) map.get("next_val");
        return result;
    }

    @Override
    public Serial getSerialInfo(String snoId) {
        return serialDao.getSerialInfo(snoId);
    }
}
