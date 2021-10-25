package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品信息
 */
@Data
public class CommodityDTO implements Serializable {

    private Integer id;

    private String commodityCode;

    private String name;

    private Integer count;
}
