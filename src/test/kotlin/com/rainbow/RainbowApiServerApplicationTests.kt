package com.rainbow

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class RainbowApiServerApplicationTests {

    @LocalServerPort
    var port: Int? = null

    @Autowired
    lateinit private var restTemplate: TestRestTemplate

    @Test
    fun contextLoads() {
        print("计算值是：" + 8 % 6)
    }


    @Test
    fun test() {
        var produceId = 1
    }

}
