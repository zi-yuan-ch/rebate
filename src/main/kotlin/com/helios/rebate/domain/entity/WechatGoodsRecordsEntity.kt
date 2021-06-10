package com.helios.rebate.domain.entity

import com.helios.rebate.domain.common.PromoteChannel
import java.util.*

class WechatGoodsRecordsEntity {
    var id: Long = 0L
    var userId: Long = 0L
    var chattingId: Long = 0L
    var goodsId: Long = 0L
    var channel: PromoteChannel? = null
    var pid: String? = null
    var url: String? = null
    var createTime: Date? = null
}