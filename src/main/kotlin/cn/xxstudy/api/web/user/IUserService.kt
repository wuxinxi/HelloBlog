package cn.xxstudy.api.web.user

import cn.xxstudy.api.pojo.User
import com.baomidou.mybatisplus.extension.service.IService

/**
 * @date: 2024/7/7 11:40
 * @author: TangRen
 * @remark:
 */
interface IUserService : IService<User> {
    fun register(user: User)

    fun login(email: String, password: String): User

}