package cn.xxstudy.api.cache

/**
 * @date: 2024/7/8 21:15
 * @author: TangRen
 * @remark:
 */
object UserCacheManager : IUserCache {

    private val userCache = ThreadLocalUserCache()

    override fun getUserId() = userCache.getUserId()

    override fun removeCache() {
        userCache.removeCache()
    }

    override fun setUser(value: Map<String, Any>) {
        userCache.setUser(value)
    }
}