package cn.xxstudy.api.web.categories

import cn.xxstudy.api.cache.UserCacheManager
import cn.xxstudy.api.configuration.exception.ApiRuntimeException
import cn.xxstudy.api.configuration.response.ResCode
import cn.xxstudy.api.pojo.Category
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * @date: 2024/7/8 18:31
 * @author: TangRen
 * @remark:
 */
@Service
class CategoryService(val mapper: CategoryMapper) : ServiceImpl<CategoryMapper, Category>(), ICategoryService {
    override fun create(category: Category) {
        val queryWrapper = QueryWrapper<Category>().eq("category_name", category.categoryName)
        if (mapper.exists(queryWrapper)) {
            throw ApiRuntimeException(ResCode.FAILED_CATEGORY_EXIST)
        }
        category.createUser = UserCacheManager.getUserId()
        if (mapper.insert(category) < 0) {
            throw ApiRuntimeException(ResCode.FAILED_DEFAULT)
        }
    }

    override fun update(category: Category) {
        val queryWrapper = QueryWrapper<Category>().eq("create_user", UserCacheManager.getUserId())
        if (!update(category, queryWrapper)) {
            throw ApiRuntimeException(ResCode.FAILED_DEFAULT)
        }
    }


    override fun getList(): List<Category> {
        val queryWrapper = QueryWrapper<Category>().eq("create_user", UserCacheManager.getUserId())
        return list(queryWrapper)
    }

}