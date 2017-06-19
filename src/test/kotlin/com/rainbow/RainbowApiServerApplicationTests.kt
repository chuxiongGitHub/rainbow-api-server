package com.rainbow

import org.junit.Test
import org.junit.runner.RunWith


import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class RainbowApiServerApplicationTests {



    @Test
    fun contextLoads() {
        print("计算值是：" + 8 % 6)
    }

}
