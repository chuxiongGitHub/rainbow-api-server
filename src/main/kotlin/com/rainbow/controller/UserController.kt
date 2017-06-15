package com.rainbow.controller

import com.rainbow.feign.UserClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@RestController
@RequestMapping("/api/v1/user")
class UserController {

    @Autowired
    lateinit private var userClient:UserClient

    @PostMapping("/login")
    fun login(@RequestBody map:Map<String,String>)=userClient.login(map)
}