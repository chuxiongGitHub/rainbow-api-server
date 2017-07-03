/**
 *作者 陈彩红 创建时间： 2017/6/14.
 */
package com.rainbow.service

import com.rainbow.commons.exception.ApiException
import com.rainbow.entity.Student
import com.rainbow.mapper.StudentMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 创建者：陈彩红 on 2017/6/14
 *每日进步一小点.
 */
@Service
@Transactional(readOnly = true)
class StudentService {

    private val logger by lazy { LoggerFactory.getLogger(Student::class.java) }

    @Autowired
    lateinit private var studentMapper: StudentMapper

    fun list() = studentMapper.list()

    fun save(student: Student) {
        if (student.sno.isNullOrBlank()) throw ApiException("sno不能为空")
        if (student.sname.isNullOrBlank()) throw ApiException("sname不能为空")
        if (student.sclass.isNullOrBlank()) throw ApiException("sclass不能为空")
        if (student.sbirthday == null) throw ApiException("sbirthday不能为空")
        if (student.ssex == null) throw ApiException("ssex不能为空")

        try {
            studentMapper.save(student)
        } catch (e: ApiException) {
            logger.error("学生信息插入失败，原因为", e)
        }

    }
}