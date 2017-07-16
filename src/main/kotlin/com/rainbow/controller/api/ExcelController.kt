package com.rainbow.controller.api

import com.rainbow.commons.ApiUtils
import com.rainbow.entity.ExcelView
import com.rainbow.entity.Student
import com.rainbow.service.StudentService
import jodd.datetime.JDateTime
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by Administrator on 2017/7/15.
 */
@Controller
@RequestMapping("/api/v1/excel")
class ExcelController {

    @Autowired
    private lateinit var studentService: StudentService

    @Autowired
    private lateinit var apiUtils: ApiUtils

    private val logger by lazy { LoggerFactory.getLogger(ExcelController::class.java) }

    @GetMapping
    fun excel(): ModelAndView {

        val students = studentService.list() as List<*>
        logger.info("123={}", students)

        val model = mapOf(
                "headers" to listOf(
                        "序号",
                        "学号",
                        "姓名",
                        "班级",
                        "出生日期",
                        "备注"
                ),

                "data" to students.mapIndexed { index, it ->
                    val student=it as Student
                    //val student = apiUtils.mapToBean(it as Map<*,*>, Student::class.java)

                    listOf(
                            index + 1,
                            student.sno,
                            student.sname,
                            student.sclass,
                            JDateTime(student.sbirthday).toString("YYYY-MM-DD hh:mm:ss"),
                            ""
                    )
                },
                "columnWidth" to mapOf(
                        1 to 13,
                        2 to 20,
                        4 to 50
                )
        )
        return ModelAndView(ExcelView(), model)
    }
}