package com.example.demo.mapper;

import com.example.demo.model.Serial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface SerialMapper {

    // 获取序号（调用存储过程）
    Map<String, Object> getSerialNo(@Param("snoId") String snoId);

    // 查询序列表实体类
    Serial getSerialInfo(@Param("snoId") String snoId);

    //修改序列表上次值
    int updLastValue(@Param("lastVal") Long lastVal,@Param("serialId") String serialId,@Param("physicalDate") Long physicalDate);

}

