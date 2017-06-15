package com.rainbow.feign

import org.springframework.cloud.netflix.feign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@FeignClient("order", url = "\${rainbow.api.user.url}", configuration = arrayOf(FeignConfig::class))
interface UserClient {

    //用户登录
    @RequestMapping("/api/v1/user/login", method = arrayOf(RequestMethod.POST))
    fun login(@RequestBody map: Map<String, String>): Any
}