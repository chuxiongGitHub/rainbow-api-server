package com.rainbow.interceptors

import com.fasterxml.jackson.databind.ObjectMapper
import com.rainbow.commons.NeedAuth
import com.rainbow.commons.exception.ApiException
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Administrator on 2017/7/8.
 */
@Component
class ApiInterceptor : HandlerInterceptorAdapter() {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if (handler is HandlerMethod && handler.hasMethodAnnotation(NeedAuth::class.java)) {
            try {
                val appId = if (request.getHeader("RAINBOW-DEBUG") != "true") {
                    request.getHeader("R-APP-ID") ?: throw ApiException("R-APP-ID不能为空")
                } else {
                    "101"
                }
                request.setAttribute("appId", appId)
                return true
            } catch (e: ApiException) {
                response.status = 400
                response.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE
                val stream = response.outputStream
                stream.write(ObjectMapper().writeValueAsBytes(mapOf("success" to false, "message" to (e.message ?: "未知错误"))))
                return false
            }
        } else {
            return true
        }
    }
}