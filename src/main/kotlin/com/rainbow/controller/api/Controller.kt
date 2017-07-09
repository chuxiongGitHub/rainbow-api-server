package com.rainbow.controller.api

import com.rainbow.service.TeacherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Administrator on 2017/7/9.
 */
@RestController
@RequestMapping("/api/v1/teacher")
class Controller {

    @Autowired
    lateinit var teacherService: TeacherService

    @GetMapping
    fun get() = teacherService.list()
}