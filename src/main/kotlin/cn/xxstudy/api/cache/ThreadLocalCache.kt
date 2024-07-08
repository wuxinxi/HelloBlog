package cn.xxstudy.api.cache

/**
 * @date: 2024/7/8 19:07
 * @author: TangRen
 * @remark:
 */
object ThreadLocalCache {
    private val threadLocal = ThreadLocal<Any>()


    fun <T> get(): T = threadLocal.get() as T

    fun set(value: Any) {
        threadLocal.set(value)
    }

    fun remove() {
        threadLocal.remove()
    }
}