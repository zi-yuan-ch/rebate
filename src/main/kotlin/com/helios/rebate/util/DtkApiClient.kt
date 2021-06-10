package com.helios.rebate.util

import java.net.URISyntaxException
import java.util.*

/**
 * @projectName:dtk-items-openapi
 * @createTime: 2019年11月28日14:13:21
 * @description:
 */
object DtkApiClient {
    fun sendReqNew(url: String?, secret: String?, paraMap: TreeMap<String, String>): String {
        if (null == url || "" == url) {
            return "请求地址不能为空"
        }
        if (null == secret || "" == secret) {
            return "secret不能为空"
        }
        if (null == paraMap || paraMap.size < 1) {
            return "参数不能为空"
        }
        val timer = System.currentTimeMillis().toString()
        paraMap["timer"] = timer
        paraMap["nonce"] = "110505"
        paraMap["signRan"] = SignMD5Util.getSignStrNew(paraMap, secret)
        var data = ""
        try {
            data = HttpUtil.httpGetRequest(url, paraMap)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        return data
    }

    fun sendReq(url: String, secret: String, paraMap: TreeMap<String, String>): String {
        if (null == url || "" == url) {
            return "请求地址不能为空"
        }
        if (null == secret || "" == secret) {
            return "secret不能为空"
        }
        if (null == paraMap || paraMap.size < 1) {
            return "参数不能为空"
        }
        paraMap["sign"] = com.helios.rebate.util.SignMD5Util.getSignStr(paraMap, secret)
        var data = ""
        try {
            data = HttpUtil.httpGetRequest(url, paraMap)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        return data
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val url = "https://openapi.dataoke.com/api/goods/nine/op-goods-list"
        val appKey = "5f115e3f5ec30"
        val appSecret = "d4cd646cf905e27b13c1519390233c36"
        val paraMap = TreeMap<String, String>()
        paraMap["version"] = "v2.0.0"
        paraMap["appKey"] = appKey
        paraMap["pageId"] = "1"
        paraMap["pageSize"] = "20"
        //        paraMap.put("keyWords", "男装");
        paraMap["nineCid"] = "1"
        val data = sendReq(url, appSecret, paraMap)
        println(data)
    }
}