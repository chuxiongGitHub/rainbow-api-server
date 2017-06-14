package com.rainbow

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RainbowApiServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(RainbowApiServerApplication::class.java, *args)
}
