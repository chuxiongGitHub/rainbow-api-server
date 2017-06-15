package com.rainbow.cache

import com.alibaba.druid.support.http.WebStatFilter
import javax.servlet.annotation.WebFilter
import javax.servlet.annotation.WebInitParam

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = arrayOf("/*"),
        initParams = arrayOf(
                WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"))
)
class DruidStatFilter : WebStatFilter() {
}