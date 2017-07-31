package com.rainbow.mapper

import com.rainbow.entity.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * Created by Administrator on 2017/7/30.
 */
@Mapper
interface UserMapper : BaseMapper<User> {


    fun findByMobile(@Param("mobile") mobile: String): User?

    fun list(@Param("searchKey") searchKey: String): List<User>?
}