package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Serial implements Serializable {

    private static final long serialVersionUID = -8213456619813700539L;

    // 序列标识
    private String serialId;

    // 最小数值
    private Long minVal;

    // 最大数值
    private Long maxVal;

    // 上次数值
    private Long lastVal;

    // 物理日期
    private Integer physicalDate;

    // 备注信息
    private String remark;

}
