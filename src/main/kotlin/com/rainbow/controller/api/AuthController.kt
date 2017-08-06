package com.rainbow.controller.api

import com.rainbow.entity.User
import com.rainbow.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

/**
 * Created by Administrator on 2017/8/6.
 */
@RestController
@RequestMapping("/api/v1/auth")
class AuthController {

    @Autowired
    lateinit private var userService: UserService

    @PostMapping("/login")
    fun login(@RequestBody map: Map<String, String>, session: HttpSession) {
        session.maxInactiveInterval = 0
        session.setAttribute("user", userService.login(map))
    }

    @PutMapping("/logout")
    fun logout(session: HttpSession) {
        session.invalidate()
    }

    @PostMapping("/info")
    fun info(@SessionAttribute(name = "user") user: User) = userService.info(user.uuid!!)
}