package com.helios.rebate.controller

import com.helios.rebate.domain.common.WechatTextMessage
import com.helios.rebate.domain.template.WechatReplayTemplate
import com.helios.rebate.service.DtkService
import com.helios.rebate.service.WechatAccountService
import com.helios.rebate.service.WechatRecordService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/message/replay")
open class WechatController {

    @Autowired
    private lateinit var dtkService: DtkService
    @Autowired
    private lateinit var recordService: WechatRecordService
    @Autowired
    private lateinit var accountService: WechatAccountService

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    @ResponseBody
    @Throws(Exception::class)
    fun get(@RequestParam signature: String?,
            @RequestParam timestamp: String?,
            @RequestParam nonce: String?,
            @RequestParam echostr: String?): String {
        println("received one request,method:get")
        return echostr ?: ""
    }

    @RequestMapping(value = [""], method = [RequestMethod.POST], consumes = [MediaType.TEXT_XML_VALUE])
    @ResponseBody
    @Throws(Exception::class)
    fun post(@RequestBody req: WechatTextMessage): String {
        println("received one request,method:post")
        val content = req.content ?: ""
        val twdData = dtkService.twd2twd(content)
        val errorContent = "很抱歉，我们现在只支持解析淘口令，其他文本信息暂时无法理解，小多正在努力学习中，请谅解！"
        var template = errorContent
        val userName = req.fromUserName ?: ""
        val account = accountService.registerAccount(userName)
        if (twdData != null && account != null) {
            val itemId = twdData.itemId
            val itemInfo = itemId?.let { dtkService.getItemInfo(itemId) }
            if (itemInfo != null) {
                val commissionRate = twdData.maxCommissionRate ?: 0.0
                val couponInfo = twdData.couponInfo ?: ""
                val reservePrice = itemInfo.reservePrice?.toDoubleOrNull() ?: 0.0
                val zkFinalPrice = itemInfo.zkFinalPrice?.toDoubleOrNull() ?: 0.0
                val twd = twdData.tpwd ?: ""
                val title = itemInfo.title ?: ""
                template = when {
                    //有优惠券，有佣金
                    commissionRate > 0 && couponInfo.isNotEmpty() -> {
                        WechatReplayTemplate.replayWithCoupon(title, reservePrice, zkFinalPrice, couponInfo, commissionRate, twd)
                    }
                    commissionRate > 0 -> {
                        WechatReplayTemplate.replayWithCommission(title, reservePrice, zkFinalPrice, commissionRate, twd)
                    }
                    else -> errorContent
                }
                val userId = account.id
                recordService.insert(userId,content,itemId.toString(),twd)
            }
        }


        return "<xml>\n" +
                " <ToUserName>$userName</ToUserName>\n" +
                " <FromUserName>${req.toUserName}</FromUserName>\n" +
                " <CreateTime>${req.createTime}</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <Content><![CDATA[${template}]]></Content>\n" +
                " <MsgId>${req.msgId}</MsgId>\n" +
                "</xml>"
    }


}