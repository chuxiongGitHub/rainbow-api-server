package com.rainbow.service

import com.google.gson.Gson
import com.rainbow.commons.ApiUtils
import com.rainbow.commons.exception.ApiException
import com.rainbow.entity.Student
import com.rainbow.entity.User
import com.rainbow.entity.UserInfo
import com.rainbow.feign.UserClient
import com.rainbow.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
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


    @Autowired
    private lateinit var client: UserClient

    @Autowired
    private lateinit var redisTemplate: StringRedisTemplate

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


    //用户登录
    fun login(params: Map<String, String>): User? {
        val userInfo = getUserFromSession(client.login(params)["session"].toString()) ?: throw ApiException("无效的session")

        val user = userMapper.findByUUID(userInfo.uuid!!) ?: throw ApiException("请联系管理员开通访问权限")
        return user
    }


    //获取登录用户信息
    fun info(uuid: String): User? = userMapper.findByUUID(uuid)

    private fun getUserFromSession(session: String): UserInfo? {
        val keys = redisTemplate.keys(("cch:session:*:*:$session")).toTypedArray()
        if (keys.size != 1) return null
        val info = redisTemplate.opsForValue().get(keys[0]) ?: return null
        return utils.mapper.readValue(info, UserInfo::class.java)
    }
}