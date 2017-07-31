package com.rainbow.entity

import jodd.datetime.JDateTime
import java.io.Serializable
import java.util.*

/**
 * Created by Administrator on 2017/7/30.
 */
class User() : Serializable {

    var uuid: String? = null

    var username: String? = null

    var mobile: String? = null

    var status: Int? = null

    //用户类别 1 管理员 2 普通用户 3 第三方用户
    var type: Int? = null

    var searchKey: String? = null

    var createTime: Date? = null


    constructor(username: String, mobile: String, searchKey: String? = null, status: Int = 1, type: Int = 2) : this() {
        this.username = username
        this.mobile = mobile
        this.uuid = UUID.randomUUID().toString()
        this.status = status
        this.type = type
        this.searchKey = searchKey
        this.createTime = Date()
    }
}