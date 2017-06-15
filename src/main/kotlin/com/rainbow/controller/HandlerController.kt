package com.rainbow.controller

import com.rainbow.commons.exception.ApiException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Created by rainbow on 2017/6/15.
 *一事专注，便是动人；一生坚守，便是深邃！
 */
@ControllerAdvice
class HandlerController {

    @ExceptionHandler
    fun exceptionHandler(ex: Exception): ResponseEntity<Any> {
        when (ex) {
            is ApiException -> {
                if (ex.code == 401) {
                    return ResponseEntity(null, HttpStatus.UNAUTHORIZED)
                } else {
                    return ResponseEntity(mapOf("success" to false, "message" to ex.message), HttpStatus.BAD_REQUEST)
                }
            }
            else -> {
                return ResponseEntity(mapOf("success" to false, "message" to ex.message), HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
    }
}