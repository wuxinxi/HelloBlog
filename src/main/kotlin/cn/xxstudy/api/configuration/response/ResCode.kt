package cn.xxstudy.api.configuration.response

/**
 * @date: 2024/7/6 22:59
 * @author: TangRen
 * @remark:
 */
enum class ResCode(val code: Int, val msg: String) {
    OK(200, "OK"),
    FAILED_400(400, "请求失败，参数错误请检查重试"),
    FAILED_401(401, "Token无效"),
    FAILED_404(404, "资源不存在"),
    FAILED_405(405, "请求方式错误"),
    FAILED_USER_EXIST(406, "用户已存在"),
    FAILED_DEFAULT(407, "操作失败请稍后重试"),
    FAILED_USER_NOT_EXIST(408, "用户名或密码错误"),
    FAILED_CATEGORY_EXIST(409, "类别已存在"),
    FAILED_500(500, "服务器错误");
}