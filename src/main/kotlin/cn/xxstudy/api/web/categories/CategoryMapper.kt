package cn.xxstudy.api.web.categories

import cn.xxstudy.api.pojo.Category
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * @date: 2024/7/8 18:30
 * @author: TangRen
 * @remark:
 */
@Mapper
interface CategoryMapper : BaseMapper<Category>