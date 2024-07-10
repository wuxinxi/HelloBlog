package cn.xxstudy.api.configuration.exception

/**
 * @date: 2024/7/10 14:24
 * @author: TangRen
 * @remark:
 */
class AppRuntimeException(val code: Int = 500, override val message: String) : RuntimeException(message)