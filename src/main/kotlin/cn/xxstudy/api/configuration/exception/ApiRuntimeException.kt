package cn.xxstudy.api.configuration.exception

import cn.xxstudy.api.configuration.response.ResCode

/**
 * @date: 2024/7/6 22:57
 * @author: TangRen
 * @remark:
 */
class ApiRuntimeException(val resCode: ResCode) : RuntimeException(resCode.msg)