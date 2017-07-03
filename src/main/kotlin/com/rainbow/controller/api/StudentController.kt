/**
 *作者 陈彩红 创建时间： 2017/6/14.
 */
package com.rainbow.controller.api

import com.rainbow.entity.Student
import com.rainbow.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 创建者：陈彩红 on 2017/6/14
 *每日进步一小点.
 */
@RestController
@RequestMapping("/api/v1/student")
class StudentController {

    @Autowired
    lateinit private var studentService: StudentService

    @GetMapping
    fun list() = studentService.list()


    @PostMapping
    fun save(@RequestBody student: Student) = studentService.save(student)
}