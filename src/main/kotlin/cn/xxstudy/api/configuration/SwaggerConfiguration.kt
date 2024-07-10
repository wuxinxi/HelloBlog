package cn.xxstudy.api.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * @date: 2024/7/10 15:19
 * @author: TangRen
 * @remark:
 */
@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("cn.xxstudy.api"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(
                ApiInfoBuilder()
                    .contact(Contact("LovelyCoder", "https://xxstudy.cn", "wuxinxizip@gmail.com"))
                    .title("API")
                    .description("练手 SpringBoo+Vue3 API").version("1.0.0").build()
            )
    }
}