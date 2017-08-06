package com.rainbow.config

import org.beetl.core.resource.FileResourceLoader
import org.beetl.ext.spring.BeetlGroupUtilConfiguration
import org.beetl.ext.spring.BeetlSpringViewResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by rainbow on 2017/6/28.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@Configuration
@EnableConfigurationProperties(BeetlProperties::class)
class BeetlConfig {

    @Autowired
    lateinit private var properties: BeetlProperties


    @Bean(initMethod = "init")
    fun getBeetlGroupUtilConfiguration(): BeetlGroupUtilConfiguration {
        val config = BeetlGroupUtilConfiguration()
        config.setResourceLoader(FileResourceLoader(properties.baseRoot, Charsets.UTF_8.name()))
        config.setConfigProperties(properties.properties)

        return config
    }

    @Bean
    fun getBeetlSpringViewResolver(): BeetlSpringViewResolver {

        val beetlSpringViewResolver = BeetlSpringViewResolver()
        beetlSpringViewResolver.setSuffix(properties.suffix)
        beetlSpringViewResolver.setContentType(properties.contentType)
        beetlSpringViewResolver.order = 0
        beetlSpringViewResolver.config = getBeetlGroupUtilConfiguration()

        return beetlSpringViewResolver
    }

}