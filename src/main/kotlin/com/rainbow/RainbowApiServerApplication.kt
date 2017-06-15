package com.rainbow

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.feign.EnableFeignClients

@SpringBootApplication
@MapperScan("com.rainbow.mapper")
@EnableFeignClients
class RainbowApiServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(RainbowApiServerApplication::class.java, *args)
}
