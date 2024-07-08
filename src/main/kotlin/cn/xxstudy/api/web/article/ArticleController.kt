package cn.xxstudy.api.web.article

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @date: 2024/7/8 13:44
 * @author: TangRen
 * @remark:
 */
@RestController
@RequestMapping("/article")
class ArticleController {

    @GetMapping("/list")
    fun fetchList(): String {
        return "OK"
    }
}