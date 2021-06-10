package com.helios.rebate.domain.mapper

import com.helios.rebate.domain.entity.WechatAccountEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface WechatAccountMapper {
    fun selectByUser(@Param("userName") userName: String): WechatAccountEntity?

    fun upsert(entity: WechatAccountEntity)
}