package com.rainbow.interceptors

import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Administrator on 2017/8/6.
 */
@Component
class AuthInterceptors : HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        if (request.session.getAttribute("user") == null) {
            response.sendRedirect("/auth")
            return false
        }
        return true
    }
}