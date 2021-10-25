package com.example.demo.response;

import java.io.Serializable;

/**
 * 封装返回参数
 */
public class ObjectResponse<T> extends BaseResponse implements Serializable {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
