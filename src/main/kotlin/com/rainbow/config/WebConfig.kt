package com.rainbow.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by Administrator on 2017/6/28.
 */
@Configuration
class WebConfig :WebMvcConfigurerAdapter(){

    override fun addInterceptors(registry: InterceptorRegistry?) {
        super.addInterceptors(registry)
    }
}