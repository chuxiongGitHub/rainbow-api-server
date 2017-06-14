/**
 *作者 陈彩红 创建时间： 2017/6/14.
 */
package com.rainbow.mapper

import com.rainbow.entity.Student
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

/**
 * 创建者：陈彩红 on 2017/6/14
 *每日进步一小点.
 */
@Mapper
interface StudentMapper : BaseMapper<Student> {

}