package cn.xxstudy.api.web.article

import cn.xxstudy.api.pojo.Article
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * @date: 2024/7/9 17:41
 * @author: TangRen
 * @remark:
 */
@Mapper
interface ArticleMapper : BaseMapper<Article>