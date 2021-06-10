package com.helios.rebate.domain.mapper

import com.helios.rebate.domain.entity.WechatChattingRecordsEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface WechatChattingRecordsMapper {
    fun insertRecord(entity: WechatChattingRecordsEntity)
}