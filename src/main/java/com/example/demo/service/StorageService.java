package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.CommodityDTO;
import com.example.demo.model.Storage;
import com.example.demo.response.ObjectResponse;

/**
 * 库存服务
 */
public interface StorageService extends IService<Storage> {

    // 扣减库存
    ObjectResponse decreaseStorage(CommodityDTO commodityDTO);

    Storage selectByCommodityCode(CommodityDTO commodityDTO);


}
