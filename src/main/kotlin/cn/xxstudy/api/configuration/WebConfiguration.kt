package cn.xxstudy.api.configuration

import cn.xxstudy.api.interceptors.AuthorizationInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @date: 2024/7/8 13:46
 * @author: TangRen
 * @remark: 全局的配置类
 */
@Configuration
class WebConfiguration : WebMvcConfigurer {
    @Autowired
    private lateinit var authorizationInterceptor: AuthorizationInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(authorizationInterceptor)
            .excludePathPatterns("/user/login", "/user/register")
    }

}