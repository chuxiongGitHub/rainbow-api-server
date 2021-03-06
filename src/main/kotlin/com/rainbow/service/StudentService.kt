/**
 *作者 陈彩红 创建时间： 2017/6/14.
 */
package com.rainbow.service

import com.rainbow.commons.ApiUtils
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

    @Autowired
    private lateinit var utils: ApiUtils

    fun list(searchKey: String?) = studentMapper.getAllBySearchKey(searchKey)

    fun save(student: Student) {
        if (student.sno.isNullOrBlank()) throw ApiException("sno不能为空")
        if (student.sname.isNullOrBlank()) throw ApiException("sname不能为空")
        if (student.sclass.isNullOrBlank()) throw ApiException("sclass不能为空")
        if (student.sbirthday == null) throw ApiException("sbirthday不能为空")
        if (student.ssex == null) throw ApiException("ssex不能为空")

        student.searchKey = build(student)

        try {
            studentMapper.save(student)
        } catch (e: ApiException) {
            logger.error("学生信息插入失败，原因为", e)
        }
    }

    fun delBySno(sno: String): Any? {
        if (sno.isNullOrBlank()) throw ApiException("sno不能为空")
        try {
            studentMapper.delBySno(sno)

            return mapOf("success" to true, "message" to "学号为${sno}的学生信息删除成功")
        } catch (e: ApiException) {
            logger.error("${e}")
        }
        return mapOf("success" to false, "message" to "学号为${sno}的学生信息删除失败")
    }


    fun updateBySno(student: Student): Any? {
        if (student.sno.isNullOrBlank()) throw ApiException("sno不能为空")

        try {
            studentMapper.update(student)
            return mapOf("success" to true, "message" to "学号为${student.sno}的学生信息修改成功")
        } catch (e: ApiException) {
            logger.error("${e}")
        }
        return mapOf("success" to false, "message" to "学号为${student.sno}的学生信息修改失败")
    }


    fun searchKey(searchKey: String): List<Student>? = studentMapper.findBySearchKey(searchKey)


    fun findBySno(sno: String): Student? = studentMapper.findBySno(sno)

    fun build(t: Student): String? {
        val keys = getSearchKeys(t)
        t.searchKey = if (keys.isNotEmpty()) {
            val first = utils.stringToPinyin(keys[0]!!, "first")
            val full = utils.stringToPinyin(keys[0]!!, "full")
            arrayOf(first.first(), first, full, *keys).distinct().joinToString(",")
        } else {
            ""
        }
        return t.searchKey
    }

    fun getSearchKeys(t: Student) = arrayOf(t.sname!!, t.sno, t.sclass,t.ssex)
}