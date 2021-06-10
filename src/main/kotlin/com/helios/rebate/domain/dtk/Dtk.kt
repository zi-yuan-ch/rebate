package com.helios.rebate.domain.dtk

class DtkTwd2TwdResp {
    var requestId: String? = null
    var time: Long? = null
    var code: Int? = null
    var msg: String? = null
    var data: DtkTwd2TwdData? = null

}

class DtkTwd2TwdData {
    //商品ID
    var itemId: Long? = null
    //淘口令
    var tpwd: String? = null
    //针对iOS14版本，增加对应能解析的长口令
    var longTpwd: String? = null
    //佣金比例
    var maxCommissionRate: Double? = null
    //当传入请求参数channelId、specialId、externalId时，该字段展示预估最低佣金率(%)(接联盟通知，官方比价订单佣金调整正式生效时间推迟至7月22日)
    var minCommissionRate: String? = null
    //原始链接
    var originUrl: String? = null
    //商品标题
    var title: String? = null
    //商品优惠券推广链接
    var couponClickUrl: String? = null
    //优惠券面额
    var couponInfo: String? = null
    //优惠券开始时间
    var couponStartTime: String? = null
    //优惠券结束时间
    var couponEndTime: String? = null
    //优惠券总量
    var couponTotalCount: Long? = null
    //优惠券剩余量
    var couponRemainCount: Long? = null
    //短链接
    var shortUrl: String? = null

}

class TbParseContentResp {
    var time: Long? = null
    var code: Int? = null
    var msg: String? = null
    var data: TbParseContentData? = null
}

class TbParseContentData {
    var goodsId: String? = null //商品ID
    var originUrl: String? = null //商品链接
    var originType: String? = null //二合一券
    var commissionRate: Double? = null
    var commissionType: String? = null
    var originInfo: TbParseOriginInfo? = null
}

class TbParseOriginInfo {
    var title: String? = null //商品标题
    var shopName: String? = null //店铺名
    var shopLogo: String? = null //店铺LOGO图
    var image: String? = null //商品主图
    var startTime: String? = null //券开始时间
    var endTime: String? = null //券结束时间
    var amount: Int? = null //券金额
    var startFee: Int? = null //券门槛金额
    var price: Double? = null //商品价格
    var activityId: String? = null //券ID
    var pid: String? = null //PID
    var status: Int? = null //券状态。0:可用; 非0:不可用
}