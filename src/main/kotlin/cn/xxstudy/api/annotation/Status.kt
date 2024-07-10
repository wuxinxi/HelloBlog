package cn.xxstudy.api.annotation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


/**
 * @date: 2024/7/9 17:25
 * @author: TangRen
 * @remark:
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [StatusConstraint::class])
@MustBeDocumented
annotation class Status(
    val status: IntArray = [0, 1, 2],
    val message: String = "Invalid status",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
