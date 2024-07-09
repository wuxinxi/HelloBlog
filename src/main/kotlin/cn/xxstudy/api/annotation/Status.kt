package cn.xxstudy.api.annotation

import javax.validation.Constraint


/**
 * @date: 2024/7/9 17:25
 * @author: TangRen
 * @remark:
 */
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [StatusConstraint::class])
annotation class Status
