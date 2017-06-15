/**
 *作者 陈彩红 创建时间： 2017/6/14.
 */
package com.rainbow.service

import com.rainbow.mapper.StudentMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 创建者：陈彩红 on 2017/6/14
 *每日进步一小点.
 */
@Service
class StudentService {

    @Autowired
    lateinit private var studentMapper:StudentMapper

    fun list()=studentMapper.list()
}