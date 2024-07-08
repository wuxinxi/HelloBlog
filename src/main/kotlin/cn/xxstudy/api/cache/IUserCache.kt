package cn.xxstudy.api.cache

/**
 * @date: 2024/7/8 21:12
 * @author: TangRen
 * @remark:
 */
interface IUserCache {
    fun getUserId(): Int

    fun removeCache()

    fun setUser(value: Map<String, Any>)
}