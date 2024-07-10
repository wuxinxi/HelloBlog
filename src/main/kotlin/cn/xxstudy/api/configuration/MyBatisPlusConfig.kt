package cn.xxstudy.api.configuration

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @date: 2024/7/10 12:05
 * @author: TangRen
 * @remark:分页配置
 */
@Configuration
class MyBatisPlusConfig {

    @Bean
    fun interceptor(): MybatisPlusInterceptor {
        val interceptor = MybatisPlusInterceptor()
        //防止全表更新删除的拦截器
        interceptor.addInnerInterceptor(BlockAttackInnerInterceptor())
        //分页拦截器
        interceptor.addInnerInterceptor(PaginationInnerInterceptor())
        return interceptor
    }
}