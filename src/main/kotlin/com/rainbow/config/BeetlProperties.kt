package com.rainbow.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

/**
 * Created by rainbow on 2017/6/28.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@ConfigurationProperties(prefix = "beetl")
class BeetlProperties {

    var suffix = ".html"
    var contentType = "text/html;charset=UTF-8"
    var baseRoot = "/templates"
    val properties = Properties()


}