package cn.xxstudy.api.web.user

import cn.xxstudy.api.configuration.exception.ApiRuntimeException
import cn.xxstudy.api.configuration.response.ResCode
import cn.xxstudy.api.pojo.User
import cn.xxstudy.api.utils.MD5Util
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.tomcat.util.security.MD5Encoder
import org.springframework.stereotype.Service

/**
 * @date: 2024/7/7 11:41
 * @author: TangRen
 * @remark:
 */
@Service
class UserService(val mapper: UserMapper) : ServiceImpl<UserMapper, User>(), IUserService {
    override fun register(user: User) {
        val queryWrapper = QueryWrapper<User>().eq("email", user.email)
        if (mapper.exists(queryWrapper)) {
            throw ApiRuntimeException(ResCode.FAILED_USER_EXIST)
        }
        user.password = MD5Util.encode(user.password)
        if (mapper.insert(user) < 0) {
            throw ApiRuntimeException(ResCode.FAILED_DEFAULT)
        }
    }

    override fun login(email: String, password: String): User {
        val queryWrapper = QueryWrapper<User>()
            .eq("email", email)
            .eq("password", MD5Util.encode(password))
        return mapper.selectOne(queryWrapper) ?: throw ApiRuntimeException(ResCode.FAILED_USER_NOT_EXIST)

    }
}