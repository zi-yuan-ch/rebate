package com.helios.rebate.domain.mapper

import com.helios.rebate.domain.entity.WechatGoodsRecordsEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface WechatGoodsRecordsMapper {
    fun insertRecord(entity: WechatGoodsRecordsEntity)
}