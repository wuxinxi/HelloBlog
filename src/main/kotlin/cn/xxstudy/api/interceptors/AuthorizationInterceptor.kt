package cn.xxstudy.api.interceptors

import cn.xxstudy.api.cache.ThreadLocalCache
import cn.xxstudy.api.cache.UserCacheManager
import cn.xxstudy.api.configuration.response.ResBody
import cn.xxstudy.api.configuration.response.ResCode
import cn.xxstudy.api.utils.TokenUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @date: 2024/7/8 13:49
 * @author: TangRen
 * @remark: token 校验
 */
@Component
class AuthorizationInterceptor(val objectMapper: ObjectMapper) : HandlerInterceptor {
    @Autowired
    lateinit var tokenUtil: TokenUtil
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader("Authorization")
        val user = tokenUtil.parseToken(token)
        if (user == null) {
            response.status = 401
            response.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE
            val res = ResBody.failed<String>(ResCode.FAILED_401.code, ResCode.FAILED_401.msg)
            response.writer.write(objectMapper.writeValueAsString(res))
            return false
        }
        //存储用户信息到 cache 中做到数据线程隔离
        UserCacheManager.setUser(user)
        return super.preHandle(request, response, handler)
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        //api 调用结束后移除，释放内存
        UserCacheManager.removeCache()
        super.afterCompletion(request, response, handler, ex)
    }
}