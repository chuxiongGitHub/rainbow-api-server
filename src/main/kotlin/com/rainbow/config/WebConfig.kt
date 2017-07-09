package com.rainbow.config

import com.rainbow.interceptors.ApiInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by rainbow on 2017/6/28.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@Configuration
class WebConfig :WebMvcConfigurerAdapter(){

    @Bean
    open fun apiInterceptor()=ApiInterceptor()

    override fun addInterceptors(registry: InterceptorRegistry) {
       registry.addInterceptor(apiInterceptor())
               .addPathPatterns("/api/v1/**")
    }
}