/**
 *作者 陈彩红 创建时间： 2017/6/14.
 */
package com.rainbow.controller.api

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
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
    fun list(
            @RequestParam(required = false) page: Int?,
            @RequestParam(defaultValue = "5") size: Int,
            @RequestParam(defaultValue = "sno asc") sort: String
    ): Any {
        if (page != null) {
            PageHelper.startPage<Student>(page, size, sort)
        }
        val list = studentService.list()


        return if (page == null) mapOf("list" to list,"total" to list.size) else mapOf("list" to PageInfo(list),"total" to list.size)
    }


    @PostMapping("/create")
    fun save(@RequestBody student: Student) = studentService.save(student)

    @DeleteMapping("/delete/{sno}")
    fun delBySno(@PathVariable("sno") sno: String) = studentService.delBySno(sno)

    @PostMapping("/update")
    fun update(@RequestBody student: Student) = studentService.updateBySno(student)
}