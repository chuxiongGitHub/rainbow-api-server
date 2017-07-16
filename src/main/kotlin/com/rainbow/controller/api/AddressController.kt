package com.rainbow.controller.api

import com.rainbow.commons.NeedAuth
import com.rainbow.service.AddressService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * Created by Administrator on 2017/7/12.
 */
@RestController
@RequestMapping("/api/v1/gps")
class AddressController {

    @Autowired
    lateinit var addressService: AddressService


    @PostMapping
    fun save(@RequestBody params: Map<String, Any>) = addressService.save(params)

    @NeedAuth
    @GetMapping
    fun list() = addressService.list()

    @DeleteMapping
    fun delByMobile(@RequestParam mobile: String) = addressService.delByMobile(mobile)
}