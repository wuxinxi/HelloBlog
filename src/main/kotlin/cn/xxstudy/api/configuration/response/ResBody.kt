package cn.xxstudy.api.configuration.response

/**
 * @date: 2024/7/6 23:12
 * @author: TangRen
 * @remark:
 */
class ResBody<T>(
    var code: Int = 0, var msg: String = "", val data: T? = null,
    val timestamp: Long = System.currentTimeMillis()
) {
    companion object {
        fun <T> success(data: T): ResBody<T> {
            return ResBody(
                code = ResCode.OK.code,
                msg = ResCode.OK.msg,
                data = data
            )
        }

        fun <T> failed(code: Int, msg: String): ResBody<T> {
            return ResBody(
                code = code,
                msg = msg
            )
        }
    }
}