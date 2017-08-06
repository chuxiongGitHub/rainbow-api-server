package com.rainbow.config

import com.rainbow.interceptors.ApiInterceptor
import com.rainbow.interceptors.AuthInterceptors
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
class WebConfig : WebMvcConfigurerAdapter() {

    @Autowired
    private lateinit var apiInterceptor: ApiInterceptor

    @Autowired
    lateinit private var authInterceptor: AuthInterceptors

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/auth/**", "/api/**")
                .addPathPatterns("/app/**")
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/api/v1/**")
    }
}