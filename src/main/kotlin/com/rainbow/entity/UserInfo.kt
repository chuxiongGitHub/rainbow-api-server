package com.rainbow.entity

import java.io.Serializable

/**
 * Created by Administrator on 2017/8/6.
 */
class UserInfo : Serializable {
    // 用户手机号
    var mobile: String? = null

    // 用户状态: 0 未注册, 1 已注册
    var status: Int? = null

    // 用户名称
    var username: String? = null

    var uuid: String? = null

    var alias: String? = null

    // 对应的客户端状态: 0 未激活, 1 已激活
    var clientStatus: Int? = null
}