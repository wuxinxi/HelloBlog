package cn.xxstudy.api.annotation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * @date: 2024/7/9 17:27
 * @author: TangRen
 * @remark: 状态
 * 0: draft
 * 1: published
 * 2: deleted
 *
 */
class StatusConstraint : ConstraintValidator<Status, Int> {

    override fun isValid(status: Int, constraint: ConstraintValidatorContext) = status in intArrayOf(0, 1, 2)

}