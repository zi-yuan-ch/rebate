package com.helios.rebate.service

import com.helios.rebate.domain.entity.WechatChattingRecordsEntity
import com.helios.rebate.domain.mapper.WechatChattingRecordsMapper
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class WechatRecordService {
    @Resource
    private lateinit var chattingMapper: WechatChattingRecordsMapper

    fun insert(userId: Long, content: String, goodsId: String?, url: String?) {
        val entity = WechatChattingRecordsEntity().apply {
            this.userId = userId
            this.content = content
            this.goodsId = goodsId
            this.url = url
        }
        chattingMapper.insertRecord(entity)
    }
}