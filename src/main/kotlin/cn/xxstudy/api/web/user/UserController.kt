package cn.xxstudy.api.web.user

import cn.xxstudy.api.pojo.User
import cn.xxstudy.api.utils.TokenUtil
import cn.xxstudy.api.vo.AvatarVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @date: 2024/7/7 11:57
 * @author: TangRen
 * @remark:
 */
@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var tokenUtil: TokenUtil

    @PostMapping("/register")
    fun register(@Valid @RequestBody user: User) {
        userService.register(user)
    }

    @PostMapping("/login")
    fun login(
        @Valid
        @NotEmpty(message = "邮箱不能为空")
        @Email(message = "邮箱格式错误")
        email: String,
        @Valid @NotEmpty(message = "密码不能为空")
        password: String
    ): String {
        val user = userService.login(email, password)
        return tokenUtil.generateToken(user.id, user.email)
    }

    @GetMapping("/profile")
    fun profile(): User? = userService.profile()

    @PatchMapping("/avatar")
    fun uploadAvatar(
        @RequestParam(name = "icon") @Valid @NotNull(message = "缺少图片资源") avatarIcon: MultipartFile
    ): AvatarVo =
        userService.uploadAvatar(avatarIcon)

}