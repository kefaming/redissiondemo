package com.example.demo.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 基本返回
 */
@Data
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = 8313588673394958399L;
    private int status = 200;
    private String message;
}
