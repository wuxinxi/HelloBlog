package cn.xxstudy.api.configuration.exception

import cn.xxstudy.api.configuration.response.ResBody
import cn.xxstudy.api.configuration.response.ResCode
import lombok.extern.slf4j.Slf4j
import org.apache.tomcat.util.buf.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException

/**
 * @date: 2024/7/6 23:04
 * @author: TangRen
 * @remark:
 */
@Slf4j
@RestControllerAdvice
@ResponseBody
class GlobalExceptionHandler {
    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ApiRuntimeException::class)
    fun apiRuntimeExceptionHandler(e: ApiRuntimeException): ResBody<String?>? {
        return ResBody.failed(e.resCode.code, e.resCode.msg)
    }

    /**
     * 自定义运行时异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AppRuntimeException::class)
    fun appRuntimeExceptionHandler(e: AppRuntimeException): ResBody<String?> {
        return ResBody.failed(e.code, e.message)
    }

    /**
     * 空指针异常定义为前端传参错误，返回400
     *
     * @param e NullPointerException
     * @return
     */
    @ExceptionHandler(NullPointerException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun nullPointerExceptionHandler(e: NullPointerException?): ResBody<String?>? {
        return ResBody.failed(ResCode.FAILED_400.code, e?.message ?: ResCode.FAILED_400.msg)
    }

    /**
     * 校验错误异常处理 1
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handler(e: MethodArgumentNotValidException): ResBody<String?>? {
        val bindingResult = e.bindingResult
        val error = bindingResult.allErrors.stream().findFirst().orElse(null)
        val messages = if (error == null) "unknown" else error.defaultMessage!!
        return ResBody.failed(ResCode.FAILED_400.code, messages)
    }

    /**
     * @Validated 校验错误异常处理 2
     */
    @ExceptionHandler(value = [ConstraintViolationException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handler(e: ConstraintViolationException): ResBody<String?>? {
        val msgList: MutableList<String> = ArrayList()
        for (constraintViolation in e.constraintViolations) {
            msgList.add(constraintViolation.message)
        }
        val messages = StringUtils.join(msgList, ';')
        return ResBody.failed(ResCode.FAILED_400.code, messages)
    }

    /**
     * @Validated 校验错误异常处理 3
     */
    @ExceptionHandler(BindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun bindExceptionHandler(e: BindException): ResBody<String?>? {
        val fieldErrors = e.bindingResult.fieldErrors
        val msgList = fieldErrors.stream()
            .map { obj: FieldError -> obj.defaultMessage }
            .collect(Collectors.toList())
        val messages = StringUtils.join(msgList, ';')
        return ResBody.failed(ResCode.FAILED_400.code, messages)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun methodNotAllowedHandler(): ResBody<String?>? {
        return ResBody.failed(ResCode.FAILED_405.code, ResCode.FAILED_405.msg)
    }


    /**
     * 处理404异常
     *
     * @param e NoHandlerFoundException
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun noHandlerFoundException(req: HttpServletRequest, e: Exception?): ResBody<String?>? {
        return ResBody.failed(ResCode.FAILED_404.code, ResCode.FAILED_404.msg)
    }

    /**
     * 处理其他异常
     *
     * @param e otherException
     * @return
     */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(e: Exception): ResBody<String?>? {
        println(e.message)
        return ResBody.failed(ResCode.FAILED_500.code, ResCode.FAILED_500.msg)
    }


}