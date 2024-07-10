package cn.xxstudy.api.web.user

import cn.xxstudy.api.cache.UserCacheManager
import cn.xxstudy.api.configuration.exception.ApiRuntimeException
import cn.xxstudy.api.configuration.response.ResCode
import cn.xxstudy.api.pojo.User
import cn.xxstudy.api.utils.ImageUtil
import cn.xxstudy.api.utils.MD5Util
import cn.xxstudy.api.vo.AvatarVo
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.apache.tomcat.util.security.MD5Encoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

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

    override fun profile(): User? {
        return mapper.selectById(UserCacheManager.getUserId())?.apply { password = "" }
    }

    override fun uploadAvatar(file: MultipartFile): AvatarVo {
        val url = ImageUtil.upload(file, UserCacheManager.getUserId().toString())
        val user = User()
        user.id = UserCacheManager.getUserId()
        user.userPic = url
        if (mapper.updateById(user) <= 0) {
            throw ApiRuntimeException(ResCode.FAILED_DEFAULT)
        }
        return AvatarVo(url)
    }


}