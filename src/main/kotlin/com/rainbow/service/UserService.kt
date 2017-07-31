package com.rainbow.service

import com.rainbow.commons.ApiUtils
import com.rainbow.commons.exception.ApiException
import com.rainbow.entity.Student
import com.rainbow.entity.User
import com.rainbow.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Administrator on 2017/7/30.
 */
@Service
class UserService {

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var utils: ApiUtils

    fun save(user: User) {

        requireNotNull(user.mobile, { "手机号码不能为空" })
        requireNotNull(user.username, { "用户名不能为空" })


        if (findByMobile(user.mobile!!) != null) throw ApiException("手机号码为${user.mobile}的用户已经存在")

        val user = User(user.username!!, user.mobile!!, build(user))

        userMapper.save(user)
    }


    fun findByMobile(mobile: String): User? = userMapper.findByMobile(mobile)

    fun list(searchKey: String): List<User>? = userMapper.list(searchKey)

    fun build(t: User): String? {
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

    fun getSearchKeys(t: User) = arrayOf(t.username!!, t.mobile)

}