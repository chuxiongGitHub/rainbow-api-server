package com.rainbow.service

import com.rainbow.entity.Teacher
import com.rainbow.mapper.TeacherMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service
import java.sql.SQLException

/**
 * Created by Administrator on 2017/7/9.
 */
@Service
class TeacherService {


    @Autowired
    lateinit private var teacherMapper: TeacherMapper

    fun list() = teacherMapper.list()

}