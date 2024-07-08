package cn.xxstudy.api.utils

import java.security.MessageDigest
import kotlin.experimental.and

/**
 * @date: 2024/7/7 11:43
 * @author: TangRen
 * @remark:
 */
object MD5Util {

    fun encode(payload: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bytes = md.digest(payload.toByteArray())
        val sb = StringBuilder()
        for (b in bytes) {
            sb.append(String.format("%02x", b and 0xff.toByte()))
        }
        return sb.toString()
    }
}