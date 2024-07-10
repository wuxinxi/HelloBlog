package cn.xxstudy.api.web.user

import cn.xxstudy.api.pojo.User
import cn.xxstudy.api.vo.AvatarVo
import com.baomidou.mybatisplus.extension.service.IService
import org.springframework.web.multipart.MultipartFile

/**
 * @date: 2024/7/7 11:40
 * @author: TangRen
 * @remark:
 */
interface IUserService : IService<User> {
    fun register(user: User)

    fun login(email: String, password: String): User

    fun profile(): User?

    fun uploadAvatar(file: MultipartFile): AvatarVo

}