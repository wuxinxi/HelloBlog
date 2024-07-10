package cn.xxstudy.api.web.article

import cn.xxstudy.api.cache.UserCacheManager
import cn.xxstudy.api.configuration.exception.ApiRuntimeException
import cn.xxstudy.api.configuration.response.ResCode
import cn.xxstudy.api.pojo.Article
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service

/**
 * @date: 2024/7/9 17:42
 * @author: TangRen
 * @remark:
 */
@Service
class ArticleService(val mapper: ArticleMapper) : ServiceImpl<ArticleMapper, Article>(), IArticleService {

    override fun create(article: Article) {
        article.createUser = UserCacheManager.getUserId()
        mapper.insert(article)
    }

    override fun update(article: Article) {
        val queryWrapper = KtQueryWrapper(Article::class.java)
            .eq(Article::createUser, UserCacheManager.getUserId())
            .eq(Article::id, article.id)
        if (mapper.update(article, queryWrapper) < 0) {
            throw ApiRuntimeException(ResCode.FAILED_DEFAULT)
        }
    }

    override fun getList(page: Int, limit: Int): List<Article> {
        val queryWrapper = KtQueryWrapper(Article::class.java)
            .eq(Article::createUser, UserCacheManager.getUserId())
        val pages = mapper.selectPage(Page(page.toLong(), limit.toLong()), queryWrapper)
        return pages.records
    }

    override fun delete(id: Long) = removeById(id)
}