package cn.xxstudy.api.web.categories

import cn.xxstudy.api.pojo.Category
import com.baomidou.mybatisplus.extension.service.IService

/**
 * @date: 2024/7/8 18:30
 * @author: TangRen
 * @remark:
 */
interface ICategoryService : IService<Category> {
    fun create(category: Category)

    fun update(category: Category)

    fun getList(): List<Category>
}