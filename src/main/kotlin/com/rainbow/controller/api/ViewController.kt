package com.rainbow.controller.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Created by Administrator on 2017/6/27.
 */
@Controller
class ViewController {


    @GetMapping("/")
    fun default()=ModelAndView("index")
}