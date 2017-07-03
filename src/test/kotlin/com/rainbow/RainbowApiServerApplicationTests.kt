package com.rainbow

import com.fasterxml.jackson.databind.JsonDeserializer
import com.google.gson.Gson
import com.rainbow.cache.druid.DruidDataSourceProperty
import com.rainbow.entity.Student
import com.rainbow.mapper.StudentMapper
import org.beetl.ext.fn.Json
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired


import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringRunner
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

@RunWith(SpringRunner::class)
@SpringBootTest
class RainbowApiServerApplicationTests {

    private val logger by lazy { LoggerFactory.getLogger(RainbowApiServerApplication::class.java) }

    @Test
    fun contextLoads() {
        print("计算值是：" + 8 % 6)
    }

    @Autowired
    lateinit var applicationContext: ApplicationContext

    @Autowired
    lateinit var druidDataSourceProperty: DruidDataSourceProperty

    @Test
    fun testDataSource() {
        val gson = Gson()
        try {
            //获取数据源
            val dataSource: DataSource = applicationContext.getBean(DataSource::class.java)
            logger.info(dataSource.javaClass.name)
            logger.info(gson.toJson(druidDataSourceProperty))

        } catch (e: SQLException) {
            logger.error("${e}")
        }
    }

    @Autowired
    lateinit var studentMapper:StudentMapper

    @Test
    fun save(){
        val student=Student()
        student.sno="255"
        student.sname="陈彩红"
        student.ssex="男"
        student.sclass="10"
        student.sbirthday= Date()

        studentMapper.save(student)
    }


    @Test
    fun update(){
        val student=Student()

        student.sno="126"
        student.sname="楚雄州"

        studentMapper.update(student)
    }
}
