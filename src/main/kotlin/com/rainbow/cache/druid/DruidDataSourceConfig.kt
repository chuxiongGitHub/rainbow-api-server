package com.rainbow.cache.druid

import com.alibaba.druid.pool.DruidDataSource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.sql.SQLException
import javax.sql.DataSource

/**
 * Created by rainbow on 2017/7/3.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource::class)
@ConditionalOnProperty(name = arrayOf("spring.datasource.type"), havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
@ServletComponentScan("com.rainbow.filter")
class DruidDataSourceConfig {

    private val logger by lazy { LoggerFactory.getLogger(DruidDataSourceConfig::class.java) }


    @Autowired
    lateinit private var druidDataSourceProperty: DruidDataSourceProperty

    @Bean
    @Primary
    fun dataSource(): DataSource {


        logger.info("开始初始化druid数据源")

        val datasource = DruidDataSource()

        datasource.url = druidDataSourceProperty.url
        datasource.password = druidDataSourceProperty.password
        datasource.driverClassName = druidDataSourceProperty.driverClassName
        datasource.username = druidDataSourceProperty.username
        datasource.initialSize = druidDataSourceProperty.initialSize
        datasource.minIdle = druidDataSourceProperty.minIdle
        datasource.maxActive = druidDataSourceProperty.maxActive
        datasource.maxWait = druidDataSourceProperty.maxWait
        datasource.timeBetweenEvictionRunsMillis = druidDataSourceProperty.timeBetweenEvictionRunsMillis
        datasource.minEvictableIdleTimeMillis = druidDataSourceProperty.minEvictableIdleTimeMillis
        datasource.validationQuery = druidDataSourceProperty.validationQuery
        datasource.isTestWhileIdle = druidDataSourceProperty.testWhileIdle
        datasource.isTestOnBorrow = druidDataSourceProperty.testOnBorrow
        datasource.isTestOnReturn = druidDataSourceProperty.testOnReturn
        datasource.isPoolPreparedStatements = druidDataSourceProperty.poolPreparedStatements
        datasource.maxPoolPreparedStatementPerConnectionSize = druidDataSourceProperty.maxPoolPreparedStatementPerConnectionSize
        datasource.isUseGlobalDataSourceStat = druidDataSourceProperty.useGlobalDataSourceStat

        try {
            datasource.setFilters(druidDataSourceProperty.filters)
        } catch (e: SQLException) {
            logger.error("druid configuration initialization filter", e)
        }
        datasource.connectProperties = druidDataSourceProperty.connectionProperties


        return datasource

    }
}