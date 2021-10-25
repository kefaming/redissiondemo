package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dto.CommodityDTO;
import com.example.demo.enums.RspStatusEnum;
import com.example.demo.mapper.StorageMapper;
import com.example.demo.model.Storage;
import com.example.demo.response.ObjectResponse;
import com.example.demo.service.StorageService;
import org.springframework.stereotype.Service;

/**
 *  库存服务实现类
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    @Override
    public ObjectResponse decreaseStorage(CommodityDTO commodityDTO) {

        Storage tStorage = baseMapper.selectByCommodityCode(commodityDTO.getCommodityCode());
        if(tStorage.getCount() < commodityDTO.getCount()){
            throw new RuntimeException("库存不足！");
        }

        int storage = baseMapper.decreaseStorage(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        ObjectResponse<Object> response = new ObjectResponse<>();
        if (storage > 0){
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }

    @Override
    public Storage selectByCommodityCode(CommodityDTO commodityDTO) {
        Storage storage = baseMapper.selectByCommodityCode(commodityDTO.getCommodityCode());
        if(storage.getCount() < commodityDTO.getCount()){
            throw new RuntimeException("库存不足！");
        }
        return storage;
    }
}
