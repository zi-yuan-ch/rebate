package com.helios.rebate.service

import com.helios.rebate.domain.entity.WechatAccountEntity
import com.helios.rebate.domain.mapper.WechatAccountMapper
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class WechatAccountService {
    @Resource
    private lateinit var accountMapper: WechatAccountMapper

    fun registerAccount(userName: String): WechatAccountEntity? {
        val entity = WechatAccountEntity().apply {
            this.userName = userName
        }
        accountMapper.upsert(entity)
        return accountMapper.selectByUser(userName)
    }
}