package cn.xxstudy.api.web.user

import cn.xxstudy.api.pojo.User
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

/**
 * @date: 2024/7/7 11:37
 * @author: TangRen
 * @remark:
 */
@Mapper
interface UserMapper : BaseMapper<User>