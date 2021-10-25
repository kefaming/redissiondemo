package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Storage;
import org.apache.ibatis.annotations.Param;

public interface StorageMapper extends BaseMapper<Storage> {

    /**
     * 扣减商品库存
     */
    int decreaseStorage(@Param("commodityCode") String commodityCode, @Param("count") Integer count);

    Storage selectByCommodityCode(@Param("commodityCode") String commodityCode);

}
