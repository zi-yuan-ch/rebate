package com.helios.rebate.util

import java.security.MessageDigest
import java.util.*
import kotlin.experimental.and

/**
 * @Title: SignMD5Util
 * @Description: 签名相关工具类
 * @Version V1.0
 */
object SignMD5Util {
    /**
     * 获取签名的util
     * @param map 请求参数
     * @param secretKey 密钥
     * @return
     */
    fun getSignStr(map: TreeMap<String, String>, secretKey: String): String {
        if (map.size == 0) {
            return ""
        }
        val sb = StringBuffer("")
        val keySet: Set<String> = map.keys
        val iter = keySet.iterator()
        while (iter.hasNext()) {
            val key = iter.next()
            sb.append("&" + key + "=" + map[key])
        }
        sb.deleteCharAt(0)
        return sign(sb.toString(), secretKey)
    }

    /**
     * 获取签名的util
     * @param map 请求参数
     * @param secretKey 密钥
     * @return
     */
    fun getSignStrNew(map: TreeMap<String, String>, secretKey: String): String {
        if (map.size == 0) {
            return ""
        }
        val sb = StringBuilder()
        sb.append("appKey=")
        sb.append(map["appKey"])
        sb.append("&timer=")
        sb.append(map["timer"])
        sb.append("&nonce=")
        sb.append(map["nonce"])
        println(sb.toString())
        return sign(sb.toString(), secretKey)
    }

    fun sign(content: String, key: String): String {
        var signStr = ""
        signStr = "$content&key=$key"
        //MD5加密后，字符串所有字符转换为大写
        return MD5(signStr)!!.toUpperCase()
    }

    /**
     *
     * MD5加密算法
     *
     * @param s
     * @return
     * @see [类、类.方法、类.成员]
     */
    fun MD5(s: String): String? {
        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
        return try {
            val btInput = s.toByteArray(charset("utf-8"))
            val mdInst = MessageDigest.getInstance("MD5")
            mdInst.update(btInput)
            val md = mdInst.digest()
            val j = md.size
            val str = CharArray(j * 2)
            var k = 0
            for (i in 0 until j) {
                val byte0 = md[i]
                str[k++] = hexDigits[byte0.toInt().ushr(4) and 0xf]
                str[k++] = hexDigits[byte0.toInt() and 0xf]
            }
            String(str).toLowerCase()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}