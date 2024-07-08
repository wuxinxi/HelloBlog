package cn.xxstudy.api.cache

/**
 * @date: 2024/7/8 21:13
 * @author: TangRen
 * @remark:
 */
class ThreadLocalUserCache : IUserCache {

    override fun getUserId(): Int {
        val user = ThreadLocalCache.get<Map<String, Any>>()
        return user["id"] as Int
    }

    override fun removeCache() {
        ThreadLocalCache.remove()
    }

    override fun setUser(value: Map<String, Any>) {
        ThreadLocalCache.set(value)
    }
}