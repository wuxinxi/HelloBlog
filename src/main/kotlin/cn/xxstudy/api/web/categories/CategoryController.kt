package cn.xxstudy.api.web.categories

import cn.xxstudy.api.pojo.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotEmpty

/**
 * @date: 2024/7/8 18:26
 * @author: TangRen
 * @remark:
 */
@RestController
@RequestMapping("/category")
class CategoryController {

    @Autowired
    private lateinit var categoryService: CategoryService

    @PostMapping("/create")
    fun create(@NotEmpty(message = "categoryName不能为空") categoryName: String, categoryAlias: String?) {
        categoryService.create(Category(categoryName, categoryAlias))
    }
}