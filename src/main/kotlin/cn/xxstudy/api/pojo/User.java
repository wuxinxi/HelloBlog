package cn.xxstudy.api.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @date: 2024/7/7 11:33
 * @author: TangRen
 * @remark:
 */
@Getter
@Setter
public class User {
    public int id;
    @NotBlank(message = "缺少用户名")
    private String username;
    @NotBlank(message = "缺少密码")
    public String password;
    private String nickname;
    @Email(message = "邮箱格式错误")
    @NotBlank(message = "缺少邮箱")
    public String email;
    public String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
