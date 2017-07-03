package com.rainbow.cache.druid

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.*

/**
 * Created by rainbow on 2017/7/3.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid")
open class DruidDataSourceProperty {

    var url:String?=null

    var username:String?=null

    var password:String?=null

    var driverClassName:String?=null

    var initialSize=0

    var minIdle=5

    var maxActive=20

    var maxWait:Long=60000

    var timeBetweenEvictionRunsMillis:Long=1000*60

    var minEvictableIdleTimeMillis:Long=100*60*30

    var validationQuery:String?=null

    var testWhileIdle=false

    var testOnBorrow=true

    var testOnReturn=false

    var maxPoolPreparedStatementPerConnectionSize=-1

    var useGlobalDataSourceStat=false

    var connectionProperties:Properties?=null

    var poolPreparedStatements=false

    var filters:String?=null


}