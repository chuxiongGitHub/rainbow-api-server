package com.rainbow

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@MapperScan("com.rainbow.mapper")
class RainbowApiServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(RainbowApiServerApplication::class.java, *args)
}
