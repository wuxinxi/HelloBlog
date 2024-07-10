package cn.xxstudy.api.web.article

import cn.xxstudy.api.pojo.Article
import com.baomidou.mybatisplus.extension.service.IService

/**
 * @date: 2024/7/9 17:40
 * @author: TangRen
 * @remark:
 */
interface IArticleService : IService<Article> {

    fun create(article: Article)

    fun update(article: Article)

    fun getList(page: Int, limit: Int = 20): List<Article>

    fun delete(id: Long): Boolean

}