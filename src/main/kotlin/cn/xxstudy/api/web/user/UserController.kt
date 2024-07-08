package cn.xxstudy.api.web.user

import cn.xxstudy.api.pojo.User
import cn.xxstudy.api.utils.TokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

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

}