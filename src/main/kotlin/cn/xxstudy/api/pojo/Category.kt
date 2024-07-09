package cn.xxstudy.api.pojo

import java.time.LocalDateTime

/**
 * @date: 2024/7/9 16:41
 * @author: TangRen
 * @remark:
 */
data class Category(
    var id: Int? = null,
    var categoryName: String? = null,
    var categoryAlias: String? = null,
    var createUser: Int? = null,
    var createTime: LocalDateTime? = null,
    var updateTime: LocalDateTime? = null
)