/**
 *作者 陈彩红 创建时间： 2017/6/14.
 */
package com.rainbow.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 创建者：陈彩红 on 2017/6/14
 *每日进步一小点.
 */
@RestController
@RequestMapping("/api/v1/student")
class StudentController {

    @Autowired
    lateinit private var userMapper:UserMapp

}