package com.rainbow.cache.druid.filter

import com.alibaba.druid.support.http.StatViewServlet
import javax.servlet.annotation.WebInitParam
import javax.servlet.annotation.WebServlet

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = arrayOf("/druid/*"),
        initParams = arrayOf(
                WebInitParam(name = "allow", value = ""),
                WebInitParam(name = "deny", value = "192.168.1.73"),
                WebInitParam(name = "loginUsername", value = "admin"),
                WebInitParam(name = "loginPassword", value = "123"),
                WebInitParam(name = "restEnable", value = "false"))
)
class DruidStatViewServlet : StatViewServlet() {
}
