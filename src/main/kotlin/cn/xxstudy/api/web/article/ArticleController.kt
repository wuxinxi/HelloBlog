package cn.xxstudy.api.web.article

import cn.xxstudy.api.configuration.response.ResBody
import cn.xxstudy.api.configuration.response.ResCode
import cn.xxstudy.api.pojo.Article
import cn.xxstudy.api.pojo.Update
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

/**
 * @date: 2024/7/8 13:44
 * @author: TangRen
 * @remark:
 */
@RestController
@RequestMapping("/api")
@Validated
class ArticleController {

    @Autowired
    private lateinit var articleService: ArticleService

    @GetMapping("/article")
    fun getList(
        @RequestParam("page", defaultValue = "1") @NotNull(message = "Page不能缺省") page: Int,
        @RequestParam("limit", defaultValue = "25") limit: Int
    ): List<Article> = articleService.getList(page, limit)

    @PostMapping("/article")
    fun create(@Valid @RequestBody article: Article) = articleService.create(article)

    @PatchMapping("/article")
    fun update(@RequestBody @Validated(Update::class) article: Article) = articleService.update(article)

    @DeleteMapping("/article/{id}")
    fun delete(@PathVariable("id") id: Long): ResBody<Boolean> {
        if (articleService.delete(id)) {
            return ResBody.success(true)
        }
        return ResBody.failed(ResCode.FAILED_404.code, ResCode.FAILED_404.msg)
    }

}