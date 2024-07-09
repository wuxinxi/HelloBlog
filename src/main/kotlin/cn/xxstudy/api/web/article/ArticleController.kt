package cn.xxstudy.api.web.article

import cn.xxstudy.api.pojo.Article
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.websocket.server.PathParam

/**
 * @date: 2024/7/8 13:44
 * @author: TangRen
 * @remark:
 */
@RestController
@RequestMapping("/api")
class ArticleController {

    @Autowired
    private lateinit var articleService: ArticleService

    @GetMapping("/article")
    fun getList(@PathParam("page") page: Int, limit: Int = 25): List<Article> = articleService.getList(page, limit)

    @PostMapping("/article")
    fun create(@Valid @RequestBody article: Article) = articleService.create(article)

    @PatchMapping("/article")
    fun update(article: Article) = articleService.update(article)

    @DeleteMapping("/article/{id}")
    fun delete(@PathVariable("id") id: Int) = articleService.delete(id)

}