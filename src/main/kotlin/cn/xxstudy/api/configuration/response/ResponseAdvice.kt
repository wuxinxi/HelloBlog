package cn.xxstudy.api.configuration.response

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

/**
 * @date: 2024/7/6 23:21
 * @author: TangRen
 * @remark:
 */
@RestControllerAdvice
class ResponseAdvice(val objectMapper: ObjectMapper) : ResponseBodyAdvice<Any> {

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>) = true

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {

        //Controller返回String,我们手动转换为json
        if (body is String) {
            response.headers.contentType = MediaType.APPLICATION_JSON
            return objectMapper.writeValueAsString(ResBody.success(body))
        }

        //Controller返回的就是ResBody
        if (body is ResBody<*>) {
            return body
        }

        //Controller返回的就是T对象
        return ResBody.success(body)
    }
}