package com.rainbow.controller.api

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.rainbow.entity.User
import com.rainbow.feign.UserClient
import com.rainbow.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@RestController
@RequestMapping("/api/v1/user")
class UserController {

    @Autowired
    lateinit private var userClient: UserClient

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/login")
    fun login(@RequestBody map: Map<String, String>) = userClient.login(map)


    @PostMapping("/create")
    fun save(@RequestBody user: User) = userService.save(user)

    @GetMapping("/list")
    fun list(
            @RequestParam(required = false, defaultValue = "") searchKey: String,
            @RequestParam(required = false) page: Int?,
            @RequestParam(defaultValue = "10") size: Int,
            @RequestParam(defaultValue = "createTime desc") sort: String): Any {
        if (page != null) {
            PageHelper.startPage<User>(page, size, sort)
        }
        val list = userService.list(searchKey)

        return if (page == null) mapOf("list" to list, "total" to list!!.size) else mapOf("list" to PageInfo(list), "total" to list!!.size)
    }
}