package cn.xxstudy.api.web.categories

import cn.xxstudy.api.pojo.Category
import cn.xxstudy.api.vo.CategoryChangeVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotEmpty

/**
 * @date: 2024/7/8 18:26
 * @author: TangRen
 * @remark:
 */
@RestController
@RequestMapping("/api")
@Validated
class CategoryController {

    @Autowired
    private lateinit var categoryService: CategoryService

    @PostMapping("/category")
    fun create(@NotEmpty(message = "categoryName不能为空") categoryName: String, categoryAlias: String?) {
        categoryService.create(Category(categoryName = categoryName, categoryAlias = categoryAlias))
    }

    @GetMapping("/category")
    fun getList() = categoryService.getList()

    @GetMapping("/category/{id}")
    fun getCategory(@PathVariable("id") id: Int): Category = categoryService.getById(id)

    @DeleteMapping("/category/{id}")
    fun delete(@PathVariable(name = "id") id: Int) = categoryService.delete(id)

    @PatchMapping("/category")
    fun update(@RequestBody vo: CategoryChangeVo) {
        categoryService.update(Category(categoryName = vo.categoryName, categoryAlias = vo.categoryAlisa, id = vo.id))
    }
}