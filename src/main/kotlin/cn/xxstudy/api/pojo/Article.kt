package cn.xxstudy.api.pojo

import cn.xxstudy.api.annotation.Status
import javax.validation.constraints.NotEmpty
import javax.validation.groups.Default

/**
 * @date: 2024/7/9 17:16
 * @author: TangRen
 * @remark:
 */
data class Article(
    @field:NotEmpty(message = "文章主键不能为空", groups = [Update::class])
    var id: Int? = null,
    @field:NotEmpty(message = "文章标题不能为空")
    var title: String? = null,
    @field:NotEmpty(message = "文章标题不能为空")
    var content: String? = null,
    var createUser: Int? = null,
    @Status
    var state: Int? = null,
    var categoryId: Int? = null
)

interface Create : Default {}

interface Update {}
